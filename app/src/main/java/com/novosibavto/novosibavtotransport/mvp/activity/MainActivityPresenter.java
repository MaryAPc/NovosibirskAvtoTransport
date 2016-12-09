package com.novosibavto.novosibavtotransport.mvp.activity;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.maps.model.PolylineOptions;
import com.novosibavto.novosibavtotransport.ChosenMarshFragment;
import com.novosibavto.novosibavtotransport.TransportListFragment;
import com.novosibavto.novosibavtotransport.adapters.viewpager.ViewPagerAdapter;
import com.novosibavto.novosibavtotransport.models.Bus;
import com.novosibavto.novosibavtotransport.models.MarshData;
import com.novosibavto.novosibavtotransport.models.MarshTaxi;
import com.novosibavto.novosibavtotransport.models.Route;
import com.novosibavto.novosibavtotransport.models.Tram;
import com.novosibavto.novosibavtotransport.models.Trolley;
import com.novosibavto.novosibavtotransport.network.RetrofitService;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {

	private TransportListFragment mTransportListFragment;
	private ChosenMarshFragment mChosenMarshFragment;
	private Subscription mSubscription;
	private Bus mBus = Bus.getBus();
	private Trolley mTrolley = Trolley.getTrolley();
	private Tram mTram = Tram.getTram();
	private MarshTaxi mMarshTaxi = MarshTaxi.getMarshTaxi();
	private List<MarshData> mChosenList = new ArrayList<>();
	private PolylineOptions mPolylineOptions;


	public void setupViewPager(ViewPager viewPager, FragmentManager fragmentManager) {
		ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);
		mTransportListFragment = TransportListFragment.newInstance();
		adapter.addFragment(mTransportListFragment, "Маршруты");
		mChosenMarshFragment = ChosenMarshFragment.newInstance();
		adapter.addFragment(mChosenMarshFragment, "Выбранное");
		viewPager.setAdapter(adapter);
	}

	public void refreshList(int position) {
		switch (position) {
			case 0:
				mTransportListFragment.refreshTransportList();
				break;
			case 1:
				mChosenMarshFragment.refreshChosenList();
				break;
		}
	}

	public void drawTrassa() {
		mChosenList = getChosenMarsh();
		for (int i = 0; i < mChosenList.size(); i++) {
			mSubscription = requestRoute(mChosenList.get(i).getId())
					.subscribeOn(Schedulers.io())
					.map(Route::getData)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new Subscriber<List<Route.Data>>() {
						@Override
						public void onCompleted() {
							getViewState().hideProgress();
						}

						@Override
						public void onError(Throwable e) {
							getViewState().showError();
							e.printStackTrace();
						}

						@Override
						public void onNext(List<Route.Data> dataRoute) {
							getViewState().drawPolyline(dataRoute);
						}
					});
		}
	}

	private Observable<Route> requestRoute(String marsh) {
		return RetrofitService.getInstance().createApi().getTrassa("[[" + marsh + ",0]]", "0.4", "56b97e45ad1772c63761c307f5e763ec", "json");
	}

	public List<MarshData> getChosenMarsh() {
		List<MarshData> data = new ArrayList<>();
		if (mBus.getData() != null) {
			mChosenList.clear();
			data.addAll(mBus.getData());
			data.addAll(mTrolley.getData());
			data.addAll(mTram.getData());
			data.addAll(mMarshTaxi.getData());
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i).isChecked()) {
					mChosenList.add(data.get(i));
				}
			}
		}
		return mChosenList;
	}
}

package com.novosibavto.novosibavtotransport.mvp.transport_fragment;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.novosibavto.novosibavtotransport.models.Marsh;
import com.novosibavto.novosibavtotransport.models.MarshData;
import com.novosibavto.novosibavtotransport.network.RetrofitService;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class TransportPresenter extends MvpPresenter<TransportListView> {

	private Subscription mSubscription;
	private List<MarshData> mDataList;
	private Marsh mMarsh = Marsh.getMarsh();

	public void loadAllMarsh() {
		mSubscription = requestWeathers()
				.subscribeOn(Schedulers.io())
				.map(Marsh::getData)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<List<MarshData>>() {
					@Override
					public void onCompleted() {
						getViewState().hideProgress();
					}

					@Override
					public void onError(Throwable e) {
						getViewState().showError();
					}

					@Override
					public void onNext(List<MarshData> marshData) {
						mMarsh.setData(marshData);
						getViewState().setMarsh(marshData);
					}
				});
	}

	public void userSelectSpinnerItem(String typePosition) {
		List<MarshData> dataList = mMarsh.getData();
		if (dataList != null) {
			List<MarshData> marshOfType = new ArrayList<>();
			if (typePosition.equals("4")) {
				typePosition = "8";
			}
			for (int i = 0; i < dataList.size(); i++) {
				MarshData marshData = dataList.get(i);
				if (marshData.getTypeTransport().equals(typePosition)) {
					marshOfType.add(marshData);
				}
			}
			getViewState().setMarsh(marshOfType);
		}
	}

	public void userCheckMarsh(int position) {
		mMarsh.getData().get(position).setChecked(false);
		getViewState().setCheckedMarsh(position);
	}

	private Observable<Marsh> requestWeathers() {
		return RetrofitService.getInstance().createApi().getAllMarsh("0.4", "56b97e45ad1772c63761c307f5e763ec", "json");
	}
}

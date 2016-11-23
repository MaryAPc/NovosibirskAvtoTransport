package com.novosibavto.novosibavtotransport.mvp.transport_fragment;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.novosibavto.novosibavtotransport.models.Bus;
import com.novosibavto.novosibavtotransport.models.Marsh;
import com.novosibavto.novosibavtotransport.models.MarshData;
import com.novosibavto.novosibavtotransport.models.MarshTaxi;
import com.novosibavto.novosibavtotransport.models.Tram;
import com.novosibavto.novosibavtotransport.models.Trolley;
import com.novosibavto.novosibavtotransport.network.RetrofitService;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class TransportPresenter extends MvpPresenter<TransportListView> {

	private Subscription mSubscription;
	private List<MarshData> mDataList = new ArrayList<>();
	private List<MarshData> mBusList = new ArrayList<>();
	private List<MarshData> mTrolleyList = new ArrayList<>();
	private List<MarshData> mTramList = new ArrayList<>();
	private List<MarshData> mMarshTaxiList = new ArrayList<>();
	private List<MarshData> mChosenList;
	private Bus mBus = Bus.getBus();
	private Trolley mTrolley = Trolley.getTrolley();
	private Tram mTram = Tram.getTram();
	private MarshTaxi mMarshTaxi = MarshTaxi.getMarshTaxi();

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
						for (int i = 0; i < marshData.size(); i++) {
							if (marshData.get(i).getTypeTransport().equals("1")) {
								mBusList.add(marshData.get(i));
							} else if (marshData.get(i).getTypeTransport().equals("2")) {
								mTrolleyList.add(marshData.get(i));
							} else if (marshData.get(i).getTypeTransport().equals("3")) {
								mTramList.add(marshData.get(i));
							} else if (marshData.get(i).getTypeTransport().equals("8")) {
								mMarshTaxiList.add(marshData.get(i));
							}
						}
						mBus.setData(mBusList);
						mTrolley.setData(mTrolleyList);
						mTram.setData(mTramList);
						mMarshTaxi.setData(mMarshTaxiList);
						getViewState().setMarsh(mBusList); //61
					}
				});
	}

	public void userSelectSpinnerItem(String typePosition) {
		List<MarshData> marshOfType = getDataList(typePosition);
		getViewState().setMarsh(marshOfType);
	}

	public void userCheckMarsh(int position, String positionSpinner) {
		mDataList = getDataList(positionSpinner);
		if (mDataList.get(position).isChecked()) {
			mDataList.get(position).setChecked(false);
		} else {
			mDataList.get(position).setChecked(true);
		}
		getViewState().setCheckedMarsh(position);
	}

	public void getChosenMarsh() {
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
		} else {
			mChosenList = new ArrayList<>();
		}
		getViewState().setMarsh(mChosenList);
	}

	public void deleteChosenMarsh(int position) {
		mChosenList.remove(position);
		getViewState().setMarsh(mChosenList);
	}

	private List<MarshData> getDataList(String position) {
		switch (position) {
			case "0":
				mDataList = mBus.getData();
				break;
			case "1":
				mDataList = mTrolley.getData();
				break;
			case "2":
				mDataList = mTram.getData();
				break;
			case "3":
				mDataList = mMarshTaxi.getData(); //61
				break;
		}
		return mDataList;
	}

	private Observable<Marsh> requestWeathers() {
		return RetrofitService.getInstance().createApi().getAllMarsh("0.4", "56b97e45ad1772c63761c307f5e763ec", "json");
	}
}

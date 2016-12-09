package com.novosibavto.novosibavtotransport.mvp.transport;

import java.util.List;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.novosibavto.novosibavtotransport.models.MarshData;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface TransportListView extends MvpView {

	void showProgress();

	void hideProgress();

	void showError();

	void setMarsh(List<MarshData> marshData);

	void setCheckedMarsh(int position);
}

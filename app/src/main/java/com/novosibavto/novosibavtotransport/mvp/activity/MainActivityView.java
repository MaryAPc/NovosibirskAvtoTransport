package com.novosibavto.novosibavtotransport.mvp.activity;

import java.util.List;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.novosibavto.novosibavtotransport.models.Route;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainActivityView extends MvpView {

	void drawPolyline(List<Route.Data> dataRoute);

	void hideProgress();

	void showError();
}

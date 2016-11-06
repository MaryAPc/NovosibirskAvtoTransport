package com.novosibavto.novosibavtotransport.mvp.activity;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainActivityView extends MvpView {

	void showFragment(Fragment fragment);

}

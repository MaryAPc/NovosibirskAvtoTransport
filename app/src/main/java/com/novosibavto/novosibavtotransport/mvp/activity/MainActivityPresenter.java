package com.novosibavto.novosibavtotransport.mvp.activity;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {

	public void addFirstFragment(Fragment firstFragment) {
		getViewState().showFragment(firstFragment);
	}
}

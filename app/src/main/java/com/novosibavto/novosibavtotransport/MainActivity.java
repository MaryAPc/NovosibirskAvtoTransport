package com.novosibavto.novosibavtotransport;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.novosibavto.novosibavtotransport.mvp.activity.MainActivityPresenter;
import com.novosibavto.novosibavtotransport.mvp.activity.MainActivityView;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView {

    private TransportListFragment mTransportListFragment;
    private FragmentManager mFragmentManager;

    @InjectPresenter
    MainActivityPresenter mActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        mFragmentManager = getSupportFragmentManager();
        mTransportListFragment = TransportListFragment.newInstance();
        mActivityPresenter.addFirstFragment(mTransportListFragment);
    }

    @Override
    public void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_activity_fragment_container, mTransportListFragment);
        fragmentTransaction.commit();
    }
}

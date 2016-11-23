package com.novosibavto.novosibavtotransport;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.novosibavto.novosibavtotransport.mvp.activity.MainActivityPresenter;
import com.novosibavto.novosibavtotransport.mvp.activity.MainActivityView;
import com.novosibavto.novosibavtotransport.network.ChosenMarshFragment;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView, ViewPager.OnPageChangeListener {

    private TransportListFragment mTransportListFragment;
    private FragmentManager mFragmentManager;

    @InjectPresenter
    MainActivityPresenter mActivityPresenter;
    private ChosenMarshFragment mChosenMarshFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_layout_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.main_layout_fragment_container);
        if (viewPager != null) {
            setupViewPager(viewPager);
            viewPager.addOnPageChangeListener(this);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_activity_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void showFragment(Fragment fragment) {
        /*FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_activity_fragment_container, mTransportListFragment);
        fragmentTransaction.commit();*/
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(TransportListFragment.newInstance(), "Маршруты");
        mChosenMarshFragment = ChosenMarshFragment.newInstance();
        adapter.addFragment(mChosenMarshFragment, "Выбранное");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("sdfffff", "onPageScrolled");
    }

    @Override
    public void onPageSelected(int position) {
        mChosenMarshFragment.refreshChosenList();
        Log.d("sdfffff", "onPageSelected");
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("sdfffff", "onPageScrollStateChanged" + state);
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter  {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
    }
}

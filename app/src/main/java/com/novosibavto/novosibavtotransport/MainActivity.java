package com.novosibavto.novosibavtotransport;

import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.novosibavto.novosibavtotransport.models.Route;
import com.novosibavto.novosibavtotransport.mvp.activity.MainActivityPresenter;
import com.novosibavto.novosibavtotransport.mvp.activity.MainActivityView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView, ViewPager.OnPageChangeListener, OnMapReadyCallback {

	@BindView(R.id.main_layout_sliding_layout)
	SlidingUpPanelLayout mUpPanelLayout;

	@InjectPresenter
	MainActivityPresenter mActivityPresenter;

	private GoogleMap mMap;
	private PolylineOptions mPolylineOptions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		ButterKnife.bind(this);
		createMap();

		Toolbar toolbar = (Toolbar) findViewById(R.id.main_layout_toolbar);
		setSupportActionBar(toolbar);

		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		ViewPager viewPager = (ViewPager) findViewById(R.id.main_layout_fragment_container);
		if (viewPager != null) {
			mActivityPresenter.setupViewPager(viewPager, getSupportFragmentManager());
			viewPager.addOnPageChangeListener(this);
		}

		TabLayout tabLayout = (TabLayout) findViewById(R.id.main_activity_tabs);
		tabLayout.setupWithViewPager(viewPager);

		mUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
			@Override
			public void onPanelSlide(View panel, float slideOffset) {
			}

			@Override
			public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
				if ((previousState == SlidingUpPanelLayout.PanelState.DRAGGING) && (newState == SlidingUpPanelLayout.PanelState.EXPANDED)) {
					mMap.clear();
					mActivityPresenter.drawTrassa();
				}
			}
		});
	}

	private void createMap() {
		if (mMap == null) {
			((MapFragment) getFragmentManager().findFragmentById(
					R.id.main_layout_map_container)).getMapAsync(this);
		}
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	}

	@Override
	public void onPageSelected(int position) {
		mActivityPresenter.refreshList(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}

	@Override
	public void drawPolyline(List<Route.Data> dataRoute) {
		if (dataRoute != null) {
			Random random = new Random();
			mPolylineOptions = new PolylineOptions()
					.geodesic(true)
					.color(-random.nextInt(0xFFFFFF))
					.width(5);
			List<Route.Trassa> trassa = dataRoute.get(0).getTrassa();
			for (int i = 0; i < trassa.size(); i = i + 3) {
				mPolylineOptions.add(new LatLng(Double.valueOf(trassa.get(i).getLat()), Double.valueOf(trassa.get(i).getLng())));
				mMap.addPolyline(mPolylineOptions);
			}
		}
	}

	@Override
	public void hideProgress() {

	}

	@Override
	public void showError() {

	}
}

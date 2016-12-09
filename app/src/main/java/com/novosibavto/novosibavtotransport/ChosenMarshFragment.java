package com.novosibavto.novosibavtotransport;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.novosibavto.novosibavtotransport.adapters.recycler.MarshRecyclerAdapter;
import com.novosibavto.novosibavtotransport.models.MarshData;
import com.novosibavto.novosibavtotransport.mvp.transport.TransportListView;
import com.novosibavto.novosibavtotransport.mvp.transport.TransportPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChosenMarshFragment extends MvpAppCompatFragment implements TransportListView {

	@BindView(R.id.fragment_chosen_marsh_recycler_view)
	RecyclerView mChosenMarshList;

	@InjectPresenter
	TransportPresenter mTransportPresenter;

	private MarshRecyclerAdapter mMarshListAdapter;

	public static ChosenMarshFragment newInstance() {
		ChosenMarshFragment fragment = new ChosenMarshFragment();
		Bundle bundle = new Bundle();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMarshListAdapter = new MarshRecyclerAdapter(new ArrayList<>(), (view, position) -> mTransportPresenter.deleteChosenMarsh(position));
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_chosen_marsh, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
		mChosenMarshList.setLayoutManager(new LinearLayoutManager(getActivity()));
		mChosenMarshList.setAdapter(mMarshListAdapter);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mTransportPresenter.getChosenMarsh();
	}

	public void refreshChosenList() {
		mTransportPresenter.getChosenMarsh();
	}

	@Override
	public void showProgress() {

	}

	@Override
	public void hideProgress() {

	}

	@Override
	public void showError() {

	}

	@Override
	public void setMarsh(List<MarshData> marshData) {
		mMarshListAdapter.setCollection(marshData);
	}

	@Override
	public void setCheckedMarsh(int position) {

	}
}

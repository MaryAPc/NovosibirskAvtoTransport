package com.novosibavto.novosibavtotransport;


import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.novosibavto.novosibavtotransport.adapters.recycler.MarshRecyclerAdapter;
import com.novosibavto.novosibavtotransport.models.MarshData;
import com.novosibavto.novosibavtotransport.mvp.transport_fragment.TransportListView;
import com.novosibavto.novosibavtotransport.mvp.transport_fragment.TransportPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TransportListFragment extends MvpAppCompatFragment implements TransportListView{

    @BindView(R.id.fragment_transport_recycler_view_marsh)
    RecyclerView mMarshListView;

    @BindView(R.id.fragment_transport_spinner)
    Spinner mTransportSpinner;

    @BindView(R.id.fragment_transport_list_progress_bar)
    ProgressBar mProgressBar;

    @InjectPresenter
    TransportPresenter mTransportPresenter;

    private MarshRecyclerAdapter mMarshListAdapter;
    private ArrayAdapter<String> mSpinnerAdapter;
    private String mPositionSpinner;

    public static TransportListFragment newInstance() {
        TransportListFragment fragment = new TransportListFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMarshListAdapter = new MarshRecyclerAdapter(new ArrayList<>(), (view, position) -> mTransportPresenter.userCheckMarsh(position, mPositionSpinner));
        mSpinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.transport_types));
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transport_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mMarshListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMarshListView.setAdapter(mMarshListAdapter);
        mTransportSpinner.setAdapter(mSpinnerAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTransportPresenter.loadAllMarsh();
        mTransportSpinner.setOnItemSelectedListener(getSpinnerListener());
    }

    private AdapterView.OnItemSelectedListener getSpinnerListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                mPositionSpinner = String.valueOf(position);
                mTransportPresenter.userSelectSpinnerItem(mPositionSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError() {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.alert_dialog_title_error)
                .setMessage(R.string.alert_dialog_message_check_network)
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public void setMarsh(List<MarshData> marshData) {
        mMarshListAdapter.setCollection(marshData);
        mMarshListView.scrollToPosition(0);
    }

    @Override
    public void setCheckedMarsh(int position) {
        mMarshListAdapter.notifyDataSetChanged();
    }
}
package com.novosibavto.novosibavtotransport;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;


public class TransportListFragment extends Fragment implements LoaderManager.LoaderCallbacks {

    TransportLab mTransportLab;
    ListView mMarshListView;
    Spinner mTransportSpinner;
    Button mMapButton;

    public TransportListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_transport_list, container, false);
        mTransportSpinner = (Spinner)view.findViewById(R.id.transport_spinner);
        mMarshListView = (ListView)view.findViewById(R.id.marsh_list_view);
        mMapButton = (Button)view.findViewById(R.id.go_to_map_button);
        mTransportLab = TransportLab.get(getContext());

        mTransportSpinner.setAdapter(mTransportLab.spinnerAdapter());
        getLoaderManager().initLoader(0, null, this);
        mTransportSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mMarshListView.setAdapter(mTransportLab.listViewAdapter(position));
                getLoaderManager().initLoader(1, null, TransportListFragment.this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });





        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mTransportLab.closeDataBase();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}

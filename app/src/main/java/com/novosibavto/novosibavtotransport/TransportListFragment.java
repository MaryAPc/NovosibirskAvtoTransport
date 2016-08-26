package com.novosibavto.novosibavtotransport;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


public class TransportListFragment extends Fragment implements LoaderManager.LoaderCallbacks {

    TransportLab mTransportLab;
    ListView marshListView;
    Spinner transportSpinner;

    public TransportListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_transport_list, container, false);
        transportSpinner = (Spinner)view.findViewById(R.id.transport_spinner);
        marshListView = (ListView)view.findViewById(R.id.marsh_list_view);
        mTransportLab = TransportLab.get(getContext());

        transportSpinner.setAdapter(mTransportLab.spinnerAdapter());
        getLoaderManager().initLoader(0, null, this);
        transportSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                marshListView.setAdapter(mTransportLab.listViewAdapter(position));
                getLoaderManager().initLoader(1, null, TransportListFragment.this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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

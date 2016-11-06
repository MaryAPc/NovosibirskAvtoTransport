package com.novosibavto.novosibavtotransport;

import android.support.v4.app.Fragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return TransportListFragment.newInstance();
    }
}

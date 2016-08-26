package com.novosibavto.novosibavtotransport;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

//Класс-сингелет
public class TransportLab {

    private static TransportLab sTransportLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private static final String TAG = "TransportLab";

    public static TransportLab get(Context context) {
        if (sTransportLab == null){
            sTransportLab = new TransportLab(context);
        }
        return sTransportLab;
    }

    private TransportLab(Context context) {
        mContext = context.getApplicationContext();
        TransportDatabaseHelper databaseHelper = new TransportDatabaseHelper(mContext, "transport");
        mDatabase = databaseHelper.getReadableDatabase();

        Log.d(TAG, "openDataBase");
    }

    public SimpleCursorAdapter spinnerAdapter() {
        Cursor cursor = mDatabase.query("Transport",
                new String[] {"_id", "Species"},
                null, null, null, null, null);
        SimpleCursorAdapter spinAdapter = new SimpleCursorAdapter(mContext,
                android.R.layout.simple_spinner_item,
                cursor,
                new String[]{"Species"},
                new int[]{android.R.id.text1}, 0);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return spinAdapter;
    }
/*
    public SimpleCursorAdapter listviewAdapter() {
        String sqlQuery = "SELECT Marsh.NumMarsh, Marsh.NameMarsh"
                + "FROM Marsh"
                + "JOIN Transport"
                + "ON Transport._id = Marsh.idSpesies"
                + "WHERE Transport._id = ?";
        Cursor cursor = mDatabase.rawQuery(sqlQuery, new String[]{"2"});
        SimpleCursorAdapter listvAdapter = new SimpleCursorAdapter(mContext,
                android.R.layout.simple_list_item_1,
                cursor,
                new String[]{"NumMarsh", "NameMarsh"},
                new int[]{android.R.id.text1}, 0);
        listvAdapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
        cursor.close();

        return listvAdapter;
    }*/

    public void closeDataBase() {
        mDatabase.close();
    }
}

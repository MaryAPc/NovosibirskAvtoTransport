package com.novosibavto.novosibavtotransport;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TransportDatabaseHelper extends SQLiteOpenHelper{

    private String DB_PATH = "/data/data/com.novosibavto.novosibavtotransport/databases/";
    private String DB_NAME = "";
    private SQLiteDatabase mDatabase;
    private final Context mContext;
    private static final String TAG = "TransportDatabaseHelper";

    public TransportDatabaseHelper(Context context, String name) {
        super(context, name, null, 1);
        this.mContext = context;
        this.DB_NAME = name;
        this.DB_PATH = this.mContext.getDatabasePath(DB_NAME).getAbsolutePath();
        openDataBase();
    }

    public void createDataBase() {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            this.getWritableDatabase();
            try {
                copyDataBase();
            } catch (IOException e){
                throw new Error("Error copying database");
            }
        }
        Log.d(TAG, "createDataBase");
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDb = null;
        String path = DB_PATH;
        try {
            checkDb = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException e) {
            Log.w(TAG, "Could't open database");
        }
        if (checkDb != null) {
            checkDb.close();
        }
        Log.d(TAG, "checkDataBase");
        return checkDb != null;
    }

    private void copyDataBase() throws IOException {
        InputStream inputDbStream = mContext.getResources().openRawResource(R.raw.transport);
        String outFileName = DB_PATH;
        OutputStream outDbStream = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputDbStream.read(buffer)) > 0) {
            outDbStream.write(buffer, 0, bytesRead);
        }
        outDbStream.close();
        inputDbStream.close();
        Log.d(TAG, "copyDataBase");
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        String path = DB_PATH;

        if (mDatabase == null) {
            createDataBase();
            mDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        }
        Log.d(TAG, "openDataBase");
        return mDatabase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

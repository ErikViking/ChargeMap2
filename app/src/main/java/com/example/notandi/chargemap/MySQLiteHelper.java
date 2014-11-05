package com.example.notandi.chargemap;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Notandi on 02.10.2014.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
	
    public static final String DATABASE_NAME = "dataloggerdb";
    public static final String GPS_TABLE = "gpstable";
    //public static final String ID = "id";
    //public static final String NAME = "name";
    //public static final String SCORE = "score";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    static final int DATABASE_VERSION = 3;
    
    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + GPS_TABLE + "(" + LATITUDE
            + " double, " + LONGITUDE
            + " double);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Default", "Database has been started");
        Log.d("Default", getDatabaseCreate());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Default", "Database OnCreate has started");
        Log.d("Default", getDatabaseCreate());
        db.execSQL(getDatabaseCreate());
        Log.d("Default", "Database onCreate has run");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Default", "Database Upgrade has started");
        db.execSQL("drop table if exists " + GPS_TABLE);
        onCreate(db);
        Log.d("Default", "Database Upgrade has ended");
    }

	public static String getDatabaseCreate() {
		return DATABASE_CREATE;
	}
}

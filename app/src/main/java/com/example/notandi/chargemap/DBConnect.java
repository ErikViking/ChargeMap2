package com.example.notandi.chargemap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * Created by Notandi on 02.10.2014.
 */
//DAO object.
public class DBConnect {


    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public DBConnect(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void commitGPS(double lati, double longi) {
        open();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.LATITUDE, lati);
        values.put(MySQLiteHelper.LONGITUDE, longi);
        Log.d("Default", "This is sent: " + lati + ", " + longi);
        database.insert(MySQLiteHelper.GPS_TABLE, null, values);
        close();
    }

    public double[][] getList() {
        open();
        //Lets get number of coordinates
        String cursorString = "SELECT COUNT(*) " + "FROM " + MySQLiteHelper.GPS_TABLE + ";";
        System.out.println(cursorString);
        Cursor cursor = database.rawQuery(cursorString, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        Log.d("Default", "gpsTable has " + count + " rows.");

        double[][] list = new double[count][2];
        //Bleh Virka�i ekki vegna �ess a� �g var me� of marga ,null d�lka. 
        //Cursor cursor2 = database.query(MySQLiteHelper.GPS_TABLE,
        //        new String[]{MySQLiteHelper.LATITUDE.toString(), MySQLiteHelper.LONGITUDE.toString()},
        //        null, null, null, null, null);
        String cursor2String = "SELECT * FROM " + MySQLiteHelper.GPS_TABLE + ";";
        Cursor cursor2 = database.rawQuery(cursor2String, null);
        Log.d("Default", "cursor2String = " + cursor2String);
        int listIndex = 0;
        Log.d("Default", "ListIndex is 0");
        cursor2.moveToFirst();

        if (cursor2.moveToFirst()) {
            do {
                list[listIndex][0] = Double.parseDouble(cursor2.getString(0));
                list[listIndex][1] = Double.parseDouble(cursor2.getString(1));

                //Log.d("Default", "" + "The list is: " + list[listIndex][0] + ", " + list[listIndex][1]);
                listIndex++;
            } while (cursor2.moveToNext());
        }
        Log.d("Default", "ListIndex is: " + listIndex);
        close();
        return list;

    }
}
    
/*
    public List<Track> getAllTracks() {
        List<Track> trackList = new ArrayList<Track>();
        String selectQuery = "SELECT latitude, longitude, time FROM tracks";
        //db = database
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Track track = new Track();
                track.latitude = Double.parseDouble(cursor.getString(0));
                track.longitude = Double.parseDouble(cursor.getString(1));
                track.time = cursor.getString(2);
                trackList.add(track);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return trackList;
    }
*/












/*
public int getStandings(int score) {
    Cursor cursor = database.rawQuery("SELECT COUNT (*) " +
            "FROM " + MySQLiteHelper.GPS_TABLE +
            " WHERE " + MySQLiteHelper.SCORE + " > " + score, null);
    cursor.moveToFirst();
    int count = cursor.getInt(0);
    cursor.close();
    return count;
}
/*
/*

public List<Comment> getAllComments() {
List<Comment> comments = new ArrayList<Comment>();

Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
    allColumns, null, null, null, null, null);

cursor.moveToFirst();
while (!cursor.isAfterLast()) {
  Comment comment = cursorToComment(cursor);
  comments.add(comment);
  cursor.moveToNext();
}
// make sure to close the cursor
cursor.close();
return comments;
}





public Comment createComment(String comment) {
    ContentValues values = new ContentValues();
    values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
    long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
            values);

}
*/
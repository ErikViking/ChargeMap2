package com.example.notandi.chargemap;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by notandi on 08.11.14.
 */
public class DistanceListActivity extends Activity {

    DBConnect db = new DBConnect(this);
    Navigator nav;

    double[][] list;
    LatLng latLng;
    ArrayList<GPSCoordinate> coordinate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private void getList(){
        list = db.getList();
    }

    private void populateArraylist(){
        int listIndex = 0;
        int rows = list.length;
        for (int i = 0; i < list.length; i++) {

        }
    }



}


/*

    public void getList() {
        latlngs = new ArrayList<LatLng>();
        double[][] list = db.getList();
        int listIndex = 0;
        int rows = list.length;
        for (int i = 0; i < list.length; i++) {

        }

    }
 */
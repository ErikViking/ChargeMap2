package com.example.notandi.chargemap;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by notandi on 08.11.14.
 */
public class DistanceListActivity extends Activity {

    DBConnect db = new DBConnect(this);
    Navigator nav;
    TextView textView;
    ArrayList<GPSCoordinate> coordinatesList;
    double[][] list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Default", "OnCreate run");

        LinearLayout linLayout = new LinearLayout(this);
        linLayout.setOrientation(LinearLayout.VERTICAL);
        ViewGroup.LayoutParams linLayoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(linLayout, linLayoutParam);
        Log.d("Default", "Layout set up");

        getList();
        populateArraylist();
        Log.d("Default", "GetList Run");

        textView = new TextView(this);
        textView.setText(String.valueOf(coordinatesList));
        linLayout.addView(textView);
        Log.d("Default", "TextView set up");
    }


    private void getList(){
        list = db.getList();
    }

    private void populateArraylist(){
        int listIndex = 0;

        coordinatesList = new ArrayList();

        GPSCoordinate rGPSCoordinate = new GPSCoordinate(222.22, 222.2223);

        coordinatesList.add(rGPSCoordinate);

        for (int i = 0; i < list.length; i++) {
            Log.d("Default", "The list is: " + list[listIndex][0] + ", " + list[listIndex][1]);



            coordinatesList.add(new GPSCoordinate(list[listIndex][0], list[listIndex][1]));


            listIndex++;
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
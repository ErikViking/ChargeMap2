package com.example.notandi.chargemap;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by notandi on 08.11.14.
 */
public class DistanceListActivity extends Activity implements AdapterView.OnItemClickListener {

    DBConnect db = new DBConnect(this);
    Navigator nav;
    TextView textView;

    double[][] list;
    GPSCoordinate rCoordinate;
    ArrayList<GPSCoordinate> coordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Default", "OnCreate run");

        getList();
        populateArraylist();
        Log.d("Default", "GetList Run");

        Log.d("Default", "ListView set up");



        /*
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.listPanel, R.id.Address, coordinates) {
            @Override
            public View getView(int position, View cachedView, ViewGroup parent) {
                View view = super.getView(position, cachedView, parent);

                TextView gpsPoint = (TextView) view.findViewById(R.id.GPSPoints);


                gpsPoint.setText("GPS point: " + coordinates(position));
                //gpsPoint.setText("GPS point: " + position);
                return view;
            }

            ListView listView = new ListView(this);
            listView.setOnItemClickListener(this);
            listView.setAdapter(adapter);
            Log.d("Default","ListView set up");
            */
        }


    private void getList(){
        list = db.getList();
    }

    private void populateArraylist(){
        int listIndex = 0;
        coordinates = new ArrayList<GPSCoordinate>();

        for (int i = 0; i < list.length; i++) {
            Log.d("Default", "The list is: " + list[listIndex][0] + ", " + list[listIndex][1]);
            rCoordinate = new GPSCoordinate(list[listIndex][0], list[listIndex][1]);
            coordinates.add(rCoordinate);
            listIndex++;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Klik på " + position, Toast.LENGTH_SHORT).show();
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
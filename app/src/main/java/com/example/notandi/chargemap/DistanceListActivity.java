package com.example.notandi.chargemap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by notandi on 08.11.14.
 */
public class DistanceListActivity extends Activity implements OnItemClickListener {

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

        GPSCoordinate workingGPSPoint = coordinates.get(2);
        double workingLat = workingGPSPoint.getLat();
        double workingLon = workingGPSPoint.getLon();
        System.out.println("GPS point is: " + workingLat + " and " + workingLon);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.listpanel, R.id.Address, coordinates) {
            @Override
            public View getView(int position, View cachedView, ViewGroup parent) {
                View view = super.getView(position, cachedView, parent);

                GPSCoordinate workingGPSPoint = coordinates.get(position);
                double workingLat = workingGPSPoint.getLat();
                double workingLon = workingGPSPoint.getLon();

                TextView gpsPoint = (TextView) view.findViewById(R.id.GPSPoints);
                TextView Address = (TextView) view.findViewById(R.id.Address);

                gpsPoint.setText("GPS point: " + workingLat + ", " + workingLon);

                return view;
            }
        };

        ListView listView = new ListView(this);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
        Log.d("Default", "ListView set up");

        listView.setSelector(android.R.drawable.ic_notification_overlay);

        setContentView(listView);
    }

    private void populateArraylist() {
        int listIndex = 0;
        coordinates = new ArrayList<GPSCoordinate>();

        for (int i = 0; i < list.length; i++) {
            //Log.d("Default", "The list is: " + list[listIndex][0] + ", " + list[listIndex][1]);
            rCoordinate = new GPSCoordinate(list[listIndex][0], list[listIndex][1]);
            coordinates.add(rCoordinate);
            listIndex++;
        }
    }

    private void getList() {
        list = db.getList();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Klik på " + position, Toast.LENGTH_SHORT).show();

        GPSCoordinate destinationGPSPoint = coordinates.get(position);

        Intent i = new Intent(this, MapActivity.class);
        i.putExtra("destiNation", (java.io.Serializable) destinationGPSPoint);
        startActivity(i);
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
 */
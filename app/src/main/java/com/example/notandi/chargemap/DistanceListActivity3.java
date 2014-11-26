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

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by notandi on 08.11.14.
 */
public class DistanceListActivity3 extends Activity implements OnItemClickListener {

    Route route;
    private GoogleMap mMap;
    Navigator nav;

    DBConnect db = new DBConnect(this);

    double presentLat, presentLon;
    GPSCoordinate presentGPSPoint;
    double[][] list;
    GPSCoordinate rCoordinate;
    ArrayList<GPSCoordinate> coordinates;
    LatLng presentLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Default", "OnCreate run");

        getList();
        populateArraylist();
        calculateArrayListDistance();
        Log.d("Default", "GetList Run");

        presentGPSPoint = new GPSCoordinate(53.606779, 9.904833);

        presentLatLng = new LatLng(presentGPSPoint.getLat(), presentGPSPoint.getLon());
        presentLat = presentGPSPoint.getLat();
        presentLon = presentGPSPoint.getLon();
        System.out.println("presentGPSPoint point is: " + presentLat + " and " + presentLon);




        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.listpanel3, R.id.Address, coordinates) {
            @Override
            public View getView(int position, View cachedView, ViewGroup parent) {
                View view = super.getView(position, cachedView, parent);

                GPSCoordinate workingGPSPoint = coordinates.get(position);
                LatLng workingLatLng = new LatLng(workingGPSPoint.getLat(), workingGPSPoint.getLon());
                double workingLat = workingGPSPoint.getLat();
                double workingLon = workingGPSPoint.getLon();

                TextView gpsPoint = (TextView) view.findViewById(R.id.GPSPoints);
                TextView address = (TextView) view.findViewById(R.id.Address);


                gpsPoint.setText("GPS point: " + workingLat + ", " + workingLon);

                nav = new Navigator(mMap, presentLatLng, workingLatLng);
                nav.setDrawPolyLine(false);
                nav.findDirections(false);

















                //String distance = route.getTotalDistance2();
                //distance = distance.replaceAll("[^a-zA-Z0-9]", "");
                //address.setText(distance);
                return view;







            }
        };


        //LatLng start = new LatLng(53.606779, 9.904833);
        //LatLng end = new LatLng(56.464416, 10.334164);

        //nav = new Navigator(mMap, start, end);
        //nav.setDrawPolyLine(false);
        //nav.findDirections(false);

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

        Collections.sort(coordinates, new Comparator<GPSCoordinate>() {
            @Override
            public int compare(GPSCoordinate lhs, GPSCoordinate rhs) {
                if (lhs.getDistance() > rhs.getDistance()) return 1;
                return -1;
            }
        });
    }

    private void calculateArrayListDistance(){
        for (int i = 0; i < coordinates.size(); i++) {
            GPSCoordinate workingGPSPoint = coordinates.get(i);
            double workingLat = workingGPSPoint.getLat();
            double workingLon = workingGPSPoint.getLon();

            Log.d("Default", "The list is: " + workingLat + ", " + workingLon);

            double distanceLat = presentLat - workingLat;
            double distanceLng = presentLon - workingLon;
            double distance = distanceLat + distanceLng;

            Log.d("Default", "CalculateArray: " + distanceLat + ", " + distanceLng + ", " + distance);
        }
    }

    private void getList() {
        list = db.getList();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Klik på " + position, Toast.LENGTH_SHORT).show();

        GPSCoordinate destinationGPSPoint = coordinates.get(position);

        Intent i = new Intent(this, MapActivity2.class);
        i.putExtra("drawPoly", false);
        i.putExtra("destination", (Serializable) destinationGPSPoint);
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
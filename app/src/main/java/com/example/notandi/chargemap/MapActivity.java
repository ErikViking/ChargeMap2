package com.example.notandi.chargemap;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import android.content.Intent;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.notandi.chargemap.Directions;
import com.example.notandi.chargemap.Navigator;
import com.example.notandi.chargemap.Navigator.OnPathSetListener;
import com.example.notandi.chargemap.Route;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


public class MapActivity extends FragmentActivity implements LocationListener ,Navigator.OnPathSetListener {
    Navigator nav;
    private GoogleMap mMap;
    private DBConnect db;
    private GPSCoordinate rCoordinate;
    Route route;
    TextView txt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        db = new DBConnect(this);
        setUpMapIfNeeded();
        /*
        //Location
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation("network");
        Log.d("Default", "Location = " + location);
        if (location != null) {
            Log.d("Default", "Location != null and if statement activated");
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 0, this);
        */
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap != null) {
            return;
        }
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        if (mMap == null) {
            return;
        }
        // Initialize map options. For example:
        // mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //double[][] markersList = db.getList();
        //printMarkersToConsole();

        /*
        rCoordinate  = (GPSCoordinate) getIntent().getSerializableExtra("destination");
        LatLng start = new LatLng(53.606779, 9.904833);
        LatLng end = new LatLng(rCoordinate.getLat(), rCoordinate.getLon());
        nav = new Navigator(mMap, start, end);
        nav.findDirections(true);
        */

        LatLng start = new LatLng(53.606779, 9.904833);
        LatLng end = new LatLng(56.464416, 10.334164);
        //LatLng end = new LatLng(rCoordinate.getLat(), rCoordinate.getLon());
        nav = new Navigator(mMap, start, end);
        nav.findDirections(false);  //False just means it doesn't show alternative route (I think TM)
        //nav.findDirections(true);
        nav.setOnPathSetListener(new OnPathSetListener() {
            @Override
            public void onPathSetListener(Directions directions) {
                // TODO Auto-generated method stub

                route = directions.getRoutes().get(0);
                //txt.setText("dist: " + route.getDistance());
                Log.d("Default", "Distance is> " + route.getDistance());
            }
        });
    }

    public void printMarkersToConsole() {
        double[][] list = db.getList();
        Log.d("Default", "printMarkersToConsole() is started");
        int listIndex = 0;
        for (int i = 0; i < list.length; i++) {
            //Log.d("Default", "The list is: " + list[listIndex][0] + ", " + list[listIndex][1]);
            addMarker(list[listIndex][0], list[listIndex][1]);
            listIndex++;
        }
    }

    private void addMarker(double lat, double lon) {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lon))
                .title("Hello world"));
    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPathSetListener(Directions directions) {

    }

	/*
    private void centerMapOnMyLocation() {
		mMap.setMyLocationEnabled(true);
	    location = mMap.getMyLocation();
	    if (location != null) {
	        myLocation = new LatLng(location.getLatitude(),
	                location.getLongitude());
	    }
	    map.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation,
	            Constants.MAP_ZOOM));
	}
	*/


}

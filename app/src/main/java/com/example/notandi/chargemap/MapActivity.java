package com.example.notandi.chargemap;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.notandi.chargemap.Navigator.OnPathSetListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends FragmentActivity implements LocationListener, Navigator.OnPathSetListener, GoogleMap.OnMarkerClickListener {
    Navigator nav;
    Route route;
    TextView txt;
    private GoogleMap mMap;
    private DBConnect db;
    private GPSCoordinate rCoordinate;
    private boolean drawPolyLine;
    LocationManager locationManager;
    Location location;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        db = new DBConnect(this);
        drawPolyLine = true;

        setUpMapIfNeeded();

        //Location
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation("network");
        Log.d("Default", "Location = " + location);
        if (location != null) {
            Log.d("Default", "Location != null and if statement activated");
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(10)                   // Sets the zoom
                    .bearing(0)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 0, this);

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
        printMarkersToConsole();

        rCoordinate  = (GPSCoordinate) getIntent().getSerializableExtra("destination");

        //LatLng intentStart = new LatLng(53.606779, 9.904833);
        //LatLng intentEnd = new LatLng(rCoordinate.getLat(), rCoordinate.getLon());
         //   Boolean dr = getIntent().getBooleanExtra("drawPoly", true);

        //nav = new Navigator(mMap, start, end);
        //nav.findDirections(true);

        //LatLng end = new LatLng(30.0115474, 31.2065308);
        //LatLng start = new LatLng(30.0146564, 31.204359);

        //LatLng end = new LatLng(30.026041, 31.198350);
        //LatLng start = new LatLng(30.022669, 31.206686);

        //30.022669, 31.206686 and 30.026041, 31.198350
        LatLng start = new LatLng(53.606779, 9.904833);
        LatLng end = new LatLng(56.464416, 10.334164);
        //LatLng end = new LatLng(rCoordinate.getLat(), rCoordinate.getLon());
        nav = new Navigator(mMap, start, end);

        nav.setDrawPolyLine(true);
/*
        Log.d("MapActivity", "Boolean dr is> " + dr);

        if(!dr){
            nav.setDrawPolyLine(false);
            Log.d("MapActivity", "If draw set to false > " + dr);
        }
*/
        nav.findDirections(false);  //False just means it doesn't show alternative route (I think TM)
        //nav.findDirections(true);
        nav.setOnPathSetListener(new OnPathSetListener() {
            @Override
            public void onPathSetListener(Directions directions) {
                // TODO Auto-generated method stub

                route = directions.getRoutes().get(0);
                //txt.setText("dist: " + route.getDistance());
                //Log.d("Default", "Distance is> " + route.getDistance());
                //Log.d("Default", "Distance is> " + route.getDistance());
                //System.out.println("Route=> " + route);
                //System.out.println("Distance " + route.getDistance());
                //System.out.println("Startlocation: "+route.getStartLocation());
                //System.out.println("Duration:"+route.getDuration());
                //System.out.println("Path: "+route.getPath().toString());
                //System.out.println("Legs:"+route.getLegs().toString());
                System.out.println("GetDistance2 is here :"+route.getTotalDistance2().toString());
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

    @Override
    public boolean onMarkerClick(Marker marker) {
        //location = locationManager.getLastKnownLocation("network");

        return false;
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

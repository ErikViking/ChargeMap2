package com.example.notandi.chargemap;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Notandi on 06.11.2014.
 */
public class GPSActivity extends Activity implements LocationListener {

    TextView Latitude, Longitude;
    LocationManager locationManager;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linLayout = new LinearLayout(this);
        linLayout.setOrientation(LinearLayout.VERTICAL);
        ViewGroup.LayoutParams linLayoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(linLayout, linLayoutParam);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation("network");

        Latitude = new TextView(this);
        Latitude.setText(String.valueOf(location.getLatitude()));
        linLayout.addView(Latitude);

        Longitude = new TextView(this);
        Longitude.setText(String.valueOf(location.getLongitude()));
        linLayout.addView(Longitude);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        Latitude.setText(String.valueOf(location.getLatitude()));
        Longitude.setText(String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}

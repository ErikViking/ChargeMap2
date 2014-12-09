package com.example.notandi.chargemap;

import android.app.Dialog;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends FragmentActivity implements OnMyLocationChangeListener {
    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
        if (status != ConnectionResult.SUCCESS) {
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
        } else {
            //SupportMapFragment fm=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
            GoogleMap fm = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            //googleMap=fm.getMap();
            googleMap.setMyLocationEnabled(true);
            googleMap.setOnMyLocationChangeListener(this);
        }
    }

    @Override
    public void onMyLocationChange(Location location) {
        // TODO Auto-generated method stub
        TextView tvLocation = (TextView) findViewById(R.id.textView1);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        tvLocation.setText("Latitude:" + latitude + ", Longitude:" + longitude);
    }

}

package com.example.notandi.chargemap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
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

import com.google.android.gms.maps.model.LatLng;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by notandi on 08.11.14.
 */
public class DistanceListActivity4 extends Activity implements OnItemClickListener {
    LatLng start;
    private ArrayList<GPSCoordinate> coordinates;
    //double presentLat = 53.606779;
    //double presentLon = 9.904833;
    private String[] resultater;
    //LatLng start = new LatLng(presentLat, presentLon);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Default", "OnCreate run");
        DBConnect db = new DBConnect(this);
        double[][] list = db.getList();


        // populateArraylist();
        int listIndex = 0;
        coordinates = new ArrayList<GPSCoordinate>();
        resultater = new String[list.length];


        //Find my location
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation("network");
        start = new LatLng(location.getLatitude(), location.getLongitude());


        //Populate list of bespoke Coordinate objects
        for (int i = 0; i < list.length; i++) {
            //Log.d("Default", "The list is: " + list[listIndex][0] + ", " + list[listIndex][1]);
            GPSCoordinate rCoordinate = new GPSCoordinate(list[listIndex][0], list[listIndex][1]);

            double roughDistanceLat = (location.getLatitude() - list[listIndex][0]);
            double roughDistanceLon = (location.getLongitude() - list[listIndex][1]);

            rCoordinate.setDistance(roughDistanceLat + roughDistanceLon);
            //rCoordinate.setDistance(Math.sqrt(rCoordinate.getLat() - location.getLatitude()) * (rCoordinate.getLat() - location.getLatitude()) + (rCoordinate.getLon() - location.getLongitude()) * (rCoordinate.getLon() - location.getLongitude()));
            coordinates.add(rCoordinate);
            listIndex++;
        }


        Collections.sort(coordinates, new Comparator<GPSCoordinate>() {
            @Override
            public int compare(GPSCoordinate lhs, GPSCoordinate rhs) {
                Log.d("Default", "Compare Run");
                if (lhs.getDistance() > rhs.getDistance()) return 1;
                return -1;
            }
        });

        Collections.sort(coordinates, new Comparator<GPSCoordinate>() {
            @Override
            public int compare(GPSCoordinate lhs, GPSCoordinate rhs) {
                Log.d("Default", "Compare Run");
                if (lhs.getDistance() > rhs.getDistance()) return 1;
                return -1;
            }
        });

        /*
      /// calculateArrayListDistance();
      for (int i = 0; i < coordinates.size(); i++) {
          GPSCoordinate workingGPSPoint = coordinates.get(i);
          double workingLat = workingGPSPoint.getLat();
          double workingLon = workingGPSPoint.getLon();

          Log.d("Default", "The list is: " + workingLat + ", " + workingLon);

          double distanceLat = presentLat - workingLat;
          double distanceLng = presentLon - workingLon;
          double distance1 = distanceLat + distanceLng;

          Log.d("Default", "CalculateArray: " + distanceLat + ", " + distanceLng + ", " + distance1);
      }
      ///
        */

        Log.d("Default", "GetList Run");
        //System.out.println("presentGPSPoint point is: " + presentLat + " and " + presentLon);


        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.listpanel3, R.id.Address, coordinates) {
            @Override
            public View getView(int position, View cachedView, ViewGroup parent) {
                View view = super.getView(position, cachedView, parent);
                GPSCoordinate workingGPSPoint = coordinates.get(position);
                TextView gpsPoint = (TextView) view.findViewById(R.id.GPSPoints);
                //gpsPoint.setText("GPS point: " + start);
                TextView address = (TextView) view.findViewById(R.id.Address);
                //TextView distance = (TextView) view.findViewById(R.id.Distance);
                String gammeltResultat = resultater[position];

                if (gammeltResultat != null) {
                    address.setText(gammeltResultat);
                } else {
                    LatLng end = new LatLng(workingGPSPoint.getLat(), workingGPSPoint.getLon());
                    String mode = "driving";
                    boolean alternatives = false;
                    String uri = "http://maps.googleapis.com/maps/api/directions/json?"
                            + "origin=" + start.latitude + "," + start.longitude
                            + "&destination=" + end.latitude + "," + end.longitude
                            + "&sensor=false&units=metric&mode=" + mode + "&alternatives=" + String.valueOf(alternatives);

                    AsTask as = new AsTask();
                    as.position = position;
                    as.uri = uri;
                    as.address = address;

                    as.execute();

                    //distance.setText(streetAddress);
                }

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Klik på " + position, Toast.LENGTH_SHORT).show();
        GPSCoordinate destinationGPSPoint = coordinates.get(position);
        Intent i = new Intent(this, MapActivity2.class);
        i.putExtra("drawPoly", false);
        i.putExtra("destination", (Serializable) destinationGPSPoint);
        startActivity(i);
    }

    // The definition of our task class
    class AsTask extends AsyncTask<String, Integer, String> {
        public TextView address;
        public String uri;
        public int position;
        public TextView distance;

        @Override
        protected String doInBackground(String... params) {
            Log.d("Distance2", "Async task started");
            String url = uri;
            Log.d("Distance2", url);
            JSONObject jsonObject = null;
            String result = "";
            HttpClient client = new DefaultHttpClient();
            Log.d("Distance", "Client made");
            HttpPost request = new HttpPost(url);
            HttpResponse response;
            //String blehAgain;
            try {
                response = client.execute(request);
                result = EntityUtils.toString(response.getEntity());
                jsonObject = new JSONObject(result);
                //JSONArray routes = jsonObject.getJSONArray("routes");
                JSONArray routes = jsonObject.getJSONArray("routes");
                JSONObject obj = routes.getJSONObject(0);
                //JSONArray legs = obj.getJSONArray("legs");
                JSONArray leg = obj.getJSONArray("legs");
                JSONObject obj2 = leg.getJSONObject(0);
                JSONObject text = obj2.getJSONObject("distance");


                //JSONObject strAdrObj = leg.getJSONObject(0);



                //JSONObject streetAddress = obj2.getJSONObject("end_address");

                //String streetAddressString = strAdrObj.getString("end_address");
                //String streetAddressString = text.getString("end_address");
                //Log.d("DistanceList4", streetAddressString);

                String result2 = text.getString("text");
                Log.d("Default", "3333333TotalDistance is >" + result2);
                resultater[position] = result2;
                return result2;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //String bleAgain;
            return "Feil";
        }

        @Override
        protected void onPostExecute(String result) {
            address.setText(result);
            //distance.setText();
            Log.d("Default", "4444444TotalDistance is >" + result);
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
package com.example.notandi.chargemap;

import android.os.AsyncTask;
import android.util.Log;

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

public class Distance2 {
    LatLng start, end;
    String mode;
    Boolean alternatives;
    String uri;
    String result2;
    Double distance;
    JSONObject jsonObject;


    public Distance2() {
        start = new LatLng(53.606779, 9.904833);
        end = new LatLng(56.464416, 10.334164);
        mode = "driving";
        alternatives = false;
        uri = "http://maps.googleapis.com/maps/api/directions/json?"
                + "origin=" + start.latitude + "," + start.longitude
                + "&destination=" + end.latitude + "," + end.longitude
                + "&sensor=false&units=metric&mode=" + mode + "&alternatives=" + String.valueOf(alternatives);
        new AsTask().execute(uri);

    }

    // The definition of our task class
    private class AsTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            Log.d("Distance", "Async task started" );
            String url = params[0];
            Log.d("Distance2", url);
            jsonObject = null;
            String result = "";
            HttpClient client = new DefaultHttpClient();
            Log.d("Distance", "Client made" );
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
                result2 = text.getString("text");
                Log.d("Default", "3333333TotalDistance is >" + result2);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //String bleAgain;
            return result2;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("Default", "4444444TotalDistance is >" + result2);
        }
    }


}
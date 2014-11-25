package com.example.notandi.chargemap;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class Distance {

    String mode;
    Boolean alternatives;
    String uri;
    String result;
    Double distance;


    public Distance() throws IOException {


        LatLng start = new LatLng(53.606779, 9.904833);
        LatLng end = new LatLng(56.464416, 10.334164);

        mode = "driving";
        alternatives = false;

        uri = "http://maps.googleapis.com/maps/api/directions/json?"
                + "origin=" + start.latitude + "," + start.longitude
                + "&destination=" + end.latitude + "," + end.longitude
                + "&sensor=false&units=metric&mode=" + mode + "&alternatives=" + String.valueOf(alternatives);

        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... executeParametre) {

                // declare our return variable.
                String result = "";
                // make a get call to HTTP.
                HttpGet httpGet = new HttpGet(uri);
                // handle the response that we get in return
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                // create an HTTPClient, which will coordiate the get and response.
                HttpClient httpClient = new DefaultHttpClient();
                // send the URI to the get method, and have the response handler parse it and return a
                // result to us.
                try {
                    result = httpClient.execute(httpGet, responseHandler);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("Distance", result);
                // return our return variable.

                return result;

            }


            @Override
            protected void onPostExecute(Object result) {
                JSONObject json = new JSONObject(result);
                //JSONObject json = new JSONObject(result);
                //JSONArray leg = result.getJSONArray("legs");
                Log.d("Distance", "OnPostExecute ended. ");
            }
        }.execute(100); // <1>

    }


}

/*
    public class DistanceFetchTask extends AsyncTask<String, Double>> {
        protected Double doInBackground(String uri) {
            int count = uri.length;
            long totalSize = 0;
            for (int i = 0; i < count; i++) {
                totalSize += Downloader.downloadFile(urls[i]);
                publishProgress((int) ((i / (float) count) * 100));
                // Escape early if cancel() is called
                if (isCancelled()) break;
            }
            return totalSize;
        }

        protected void onProgressUpdate(Integer... progress) {
            setProgressPercent(progress[0]);
        }

        protected void onPostExecute(Long result) {
            showDialog("Downloaded " + result + " bytes");

        }

        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }
    }




}



 /*
    @Override
    protected Object doInBackground(Object[] params) {
        Log.d("Distance", "doInBackground started. ");
// declare our return variable.
        result = "";
// make a get call to HTTP.
        HttpGet httpGet = new HttpGet(uri);
// handle the response that we get in return
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
// create an HTTPClient, which will coordiate the get and response.
        HttpClient httpClient = new DefaultHttpClient();
// send the URI to the get method, and have the response handler parse it and return a
// result to us.
        try {
            result = httpClient.execute(httpGet, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Distance", result);
// return our return variable.
        return result;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.d("Distance", "doInBackground started. ");
    }
}
*/
/*


    public String request(String uri) throws ClientProtocolException, IOException {
// declare our return variable.
        String result = "";
// make a get call to HTTP.
        HttpGet httpGet = new HttpGet(uri);
// handle the response that we get in return
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
// create an HTTPClient, which will coordiate the get and response.
        HttpClient httpClient = new DefaultHttpClient();
// send the URI to the get method, and have the response handler parse it and return a
// result to us.
        result = httpClient.execute(httpGet, responseHandler);
        Log.d("Distance", result);
// return our return variable.
        return result;
    }

 */
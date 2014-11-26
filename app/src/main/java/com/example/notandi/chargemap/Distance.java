package com.example.notandi.chargemap;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Distance {

    String mode;
    Boolean alternatives;
    String uri;
    String result2;
    Double distance;
    JSONObject jsonObject;


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
                Log.d("Distance", "Async task started" );
                jsonObject = null;

                HttpClient client = new DefaultHttpClient();
                Log.d("Distance", "Client made" );
                HttpPost request = new HttpPost(uri);
                HttpResponse response;
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
                    //result = httpClient.execute(httpGet, responseHandler);
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
                    String blehAgain = text.getString("text");
                    Log.d("Default", "3333333TotalDistance is >" + blehAgain);
                    this.result2 = blehAgain.replaceAll("[^a-zA-Z0-9]", "");
                    //JSONObject text = distance.getJSONObject("legs");
                    //String blehAgain = text.getString("text");
                    //Log.d("Default", "3333333TotalDistance is >" + blehAgain);

                    //String resultString = routes.getJSONObject(0).getString("text");
                    //Log.d("Distance", "ResultString is > " + resultString);
                    //Log.d("Distance", result);
                    //Log.d("Distance", "ResultString is > " + resultString);

                    //JSONArray routes = jsonObject.getJSONArray("routes");
                    //JSONObject legJS = leg.getJSONObject(0);
                    //String blech2 = legJS.getString("distance");
                    //Log.d("Distance", "Distance from Distance > " + blech2);

                    //jsonObject = new JSONObject(result);
                    //JSONArray leg = jsonObject.getJSONArray("legs");
                    //JSONObject obj = leg.getJSONObject(0);
                    //JSONObject text = obj.getJSONObject("distance");
                    //String blehAgain = text.getString("text");
                    //Log.d("Distance", "Distance from Distance > " + blehAgain);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Distance", result);
                // return our return variable.

                return result;

            }


            @Override
            protected void onPostExecute(Object result) {
                //JSONObject json = new JSONObject(result);
                //JSONObject json = new JSONObject(result);
                //JSONArray leg = result.getJSONArray("legs");
                Log.d("Distance", "OnPostExecute ended. ");
            }
        }; // <1>

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

/*
package com.example.notandi.chargemap;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Distance {

    String mode;
    Boolean alternatives;
    String uri;
    String result;
    Double distance;
    JSONObject jsonObject;

    public Distance() throws IOException {


        LatLng start = new LatLng(53.606779, 9.904833);
        LatLng end = new LatLng(56.464416, 10.334164);

        mode = "driving";
        alternatives = false;
        //final JSONObject jsonObject;
        uri = "http://maps.googleapis.com/maps/api/directions/json?"
                + "origin=" + start.latitude + "," + start.longitude
                + "&destination=" + end.latitude + "," + end.longitude
                + "&sensor=false&units=metric&mode=" + mode + "&alternatives=" + String.valueOf(alternatives);
        Log.d("Distance", "Async task starting" );
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... executeParametre) {
                Log.d("Distance", "Async task started" );
                jsonObject = null;

                HttpClient client = new DefaultHttpClient();
                Log.d("Distance", "client made" );
                HttpPost request = new HttpPost(uri);
                HttpResponse response;
                try {
                    response = client.execute(request);
                    String result = EntityUtils.toString(response.getEntity());
                    jsonObject = new JSONObject(result);
                    JSONArray leg = jsonObject.getJSONArray("legs");
                    JSONObject obj = leg.getJSONObject(0);
                    JSONObject text = obj.getJSONObject("distance");
                    String blehAgain = text.getString("text");
                    Log.d("Distance", "Distance from Distance > " + blehAgain);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                request.setHeader("Accept", "application/json");
                request.setHeader("Content-type", "application/json");

                //request.setEntity(se);


                // declare our return variable.
                //String result = "";
                // make a get call to HTTP.
                //HttpGet httpGet = new HttpGet(uri);
                // handle the response that we get in return
                //ResponseHandler<String> responseHandler = new BasicResponseHandler();
                // create an HTTPClient, which will coordiate the get and response.
                //HttpClient httpClient = new DefaultHttpClient();
                // send the URI to the get method, and have the response handler parse it and return a
                // result to us.
                //try {
                //    result = httpClient.execute(httpGet, responseHandler);
                //} catch (IOException e) {
                //    e.printStackTrace();
                //}
                //Log.d("Distance", result);
                // return our return variable.
                //return result;



                return jsonObject;
            }

        };




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

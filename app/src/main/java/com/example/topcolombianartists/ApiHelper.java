package com.example.topcolombianartists;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ApiHelper {
    private static final String API_URL = "http://ws.audioscrobbler.com/2.0/";
    private static final String API_KEY = "cf2894b9c73a323e24f5c6a9aab1eb85";

    /**
     * This method get all artist of the top in api service {@link #API_URL} and access whit key {@link #API_KEY}
     * @param currentContext context of activity
     * @param responseListener receive the JSONObject from api service
     * **/
    public void GetArtists(Context currentContext, final Response.Listener<JSONObject> responseListener){
        RequestQueue requestQueue = Volley.newRequestQueue(currentContext);
        String url = API_URL + "?method=geo.gettopartists&country=colombia&api_key="+API_KEY+"&format=json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                //write the error in the log screen
                responseListener, error -> Log.e("error-getArtists", error.toString())
        );
        requestQueue.add(jsonObjectRequest);
    }

    /**
     * This method get all artist of the top in api service {@link #API_URL} and access whit key {@link #API_KEY}
     * @param currentContext context of activity
     * @param responseListener receive the JSONObject from api service
     * @param artistName set the artist name for the consult
     * **/
    public void GetTracks(Context currentContext, final Response.Listener<JSONObject> responseListener, String artistName){
        RequestQueue requestQueue = Volley.newRequestQueue(currentContext);
        String url = API_URL + "?method=artist.gettoptracks&artist="+artistName+"&api_key="+API_KEY+"&format=json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                //write the error in the log screen
                responseListener, error -> Log.e("error-getArtists", error.toString())
        );
        requestQueue.add(jsonObjectRequest);

    }
}

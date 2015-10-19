package com.example.yhuang.scavengerhunt.Database;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.security.auth.callback.Callback;

/**
 * Created by siyer on 10/6/2015.
 */

public class ClueDBConnection {
    RequestQueue searchqueue;
    private final static String url = "http://45.55.65.113/scavengerhunt";

    public ClueDBConnection(Context context) {
        //Request queue for search
        searchqueue = Volley.newRequestQueue(context);
    }

    public void getLocations(final CallbackInterface callback) {
        final ArrayList<Double> latitudeList = new ArrayList<Double>();
        final ArrayList<Double> longitudeList = new ArrayList<Double>();
        final ArrayList<Integer> idList = new ArrayList<Integer>();
        final ArrayList<String> s3vid = new ArrayList<String>();

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                new JSONObject(),
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        String item = "path";
                        try {
                            JSONArray urlJson = response.getJSONArray(item);
                            for(int i = 0; i < urlJson.length(); i++){
                                    latitudeList.add(urlJson.getJSONObject(i).getDouble("latitude"));
                                    longitudeList.add(urlJson.getJSONObject(i).getDouble("longitude"));
                                    idList.add(urlJson.getJSONObject(i).getInt("id"));
                                    s3vid.add(urlJson.getJSONObject(i).getString("s3id"));
                            }
                            Map<Integer, ClueRow.Row> map = ClueRow.Map(idList,latitudeList,longitudeList,s3vid);
                            callback.resultsCallback(map);
                        } catch (org.json.JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.getMessage());
                    }
                }
        );
        searchqueue.add(request);

    }
}
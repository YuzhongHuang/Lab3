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
            final ArrayList<String> locationList = new ArrayList<String>();

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    new JSONObject(),
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response) {
                            String item = "items";
                            try {
                                JSONArray urlJson = response.getJSONArray(item);
                                for(int i = 0; i < response.length(); i++){
                                    locationList.add(urlJson.getJSONObject(i).getString("link"));
                                }
                                callback.resultsCallback(locationList);
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

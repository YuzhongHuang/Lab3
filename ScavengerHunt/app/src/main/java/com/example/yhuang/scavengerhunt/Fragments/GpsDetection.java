package com.example.yhuang.scavengerhunt.Fragments;


import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yhuang.scavengerhunt.Database.ClueRow;
import com.example.yhuang.scavengerhunt.MainActivity;
import com.example.yhuang.scavengerhunt.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GpsDetection extends Service implements LocationListener {

    protected LocationManager locationManager; // Declaring a Location Manager

    private final Context mContext;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 5; // The minimum distance to change Updates in meters is 5 meters
    private static final long MIN_TIME_BW_UPDATES = 30000; // The minimum time between updates in milliseconds is half minute

    boolean isGPSEnabled = false; //flag for GPS enable
    boolean isNetworkEnabled = false; // flag for network status
    boolean canGetLocation = false; // flag for GPS status

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude

    public GpsDetection(Context context) {
        this.mContext = context;
        getLocation();
    }

    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     *
    public void stopUsingGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(GpsDetection.this);
        }
    }*/

    public double latitudeInfo() {
        if (location != null) {
            latitude = location.getLatitude();
        }
        return latitude;
    }

    public double longitudeInfo() {
        if (location != null) {
            longitude = location.getLongitude();
        }
        return longitude;
    }

    public boolean canGetLocation(double lat1, double lon1, double lat2, double lon2) {
        if (calculateDistance(lat1, lon1, lat2, lon2)<40){
            Log.d("CalculateDistance",String.valueOf(calculateDistance(lat1, lon1, lat2, lon2)));
            this.canGetLocation = Boolean.TRUE;
        }
        return this.canGetLocation;
    }

    public double calculateDistance(double lat1, double lon1, double lat2, double lon2){
        //Returns the distance in meters
        System.out.println(lat1);
        System.out.println(lon1);
        System.out.println(lat2);
        System.out.println(lon2);

        double R = 6378.137; // Radius of earth in KM
        /*
        double dLat = (lat2 - lat1) * Math.PI / 180;
        double dLon = (lon2 - lon1) * Math.PI / 180;

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(lat1 * Math.PI / 180)
                * Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;
        */

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double d = R * c;

        double distance = (d * 1000); //In meters
        System.out.println(distance);
        return Math.abs(distance);
    }

    public String calculateDirection(double lat1, double lon1, double lat2, double lon2) {
        //Gives you direction TO point 2

        String direction = "";
        double dLat = (lat2 - lat1) * Math.PI / 180;
        double dLon = (lon2 - lon1) * Math.PI / 180;

        double degrees = Math.atan2(dLat, dLon) * 180/Math.PI;

        // fix negative degrees
        List<String> coordNames = Arrays.asList("North", "North East", "East", "South East", "South", "South West", "West", "North West", "North");

        int coordIndex = (int) Math.round(degrees/45);
        if (coordIndex < 0) {
            coordIndex = coordIndex + 8;
        }

        return coordNames.get(coordIndex); // returns the coordinate value
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Message
        alertDialog.setMessage("Enable GPS?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}
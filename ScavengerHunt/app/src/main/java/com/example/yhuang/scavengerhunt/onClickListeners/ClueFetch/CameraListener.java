package com.example.yhuang.scavengerhunt.onClickListeners.ClueFetch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Toast;

import com.example.yhuang.scavengerhunt.CameraActivity;
import com.example.yhuang.scavengerhunt.Fragments.GpsDetection;
import com.example.yhuang.scavengerhunt.R;

/**
 * Created by yhuang on 10/19/2015.
 */
public class CameraListener implements View.OnClickListener {

    private GpsDetection m_gpsInfo;
    private Activity m_activity;
    private PackageManager m_packageManager;

    public CameraListener (GpsDetection gpsInfo, Activity activity, PackageManager packageManager) {
        m_gpsInfo = gpsInfo;
        m_activity = activity;
        m_packageManager = packageManager;
    }

    @Override public void onClick(View v) {
        //update user's GPS status
        Boolean m_getSpot = m_gpsInfo.canGetLocation();

        //Using PackageManager to check if an Android device has a camera from within a fragment
        if(!m_packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(m_activity, "This device does not have a camera.", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        //Check if the user have reached the spot or not
        if (m_getSpot) {
            //double latitude = gpsInfo.latitudeInfo();
            //double longitude = gpsInfo.longitudeInfo();

            //Toast.makeText(getActivity().getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

            //send intent to CameraActivity
            Intent intent = new Intent(m_activity, CameraActivity.class);
            m_activity.startActivity(intent);
        } else {
            //Shows that user still doesn't get the spot yet
            //and ask them whether they want hint or not
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(m_activity);
            alertDialogBuilder.setMessage(R.string.hint_message);

            alertDialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(m_activity, "hint",
                            Toast.LENGTH_SHORT).show();
                }
            });

            alertDialogBuilder.setNegativeButton(R.string.go_back, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //nothing
                }
            });

            alertDialogBuilder.show();
        }
    }

}

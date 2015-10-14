package com.example.yhuang.scavengerhunt.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import com.example.yhuang.scavengerhunt.CameraActivity;
import com.example.yhuang.scavengerhunt.R;

public class ClueFetch extends Fragment {

    TextView curClue;
    TextView totalClue;
    VideoView clueVideo;
    ImageButton prev;
    ImageButton next;
    ImageButton camera;
    Boolean getSpot;

    public ClueFetch() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_clue_fetch, container, false);

        curClue = (TextView) rootView.findViewById(R.id.currentClue); //current clue index
        totalClue = (TextView) rootView.findViewById(R.id.totalClue); //total clue index
        clueVideo = (VideoView) rootView.findViewById(R.id.videoClue); //video for the current clue
        camera = (ImageButton) rootView.findViewById(R.id.camera); //camera button
        prev = (ImageButton) rootView.findViewById(R.id.prev); //travel to the previous clue
        next = (ImageButton) rootView.findViewById(R.id.next); //skip to the next clue
        getSpot = true; //indicates whether does the user get the spot or not.

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Using PackageManager to check if an Android device has a camera from within a fragment
                Context context = getActivity();
                PackageManager packageManager = context.getPackageManager();

                //Check whether the device has a camera or not
                if(!packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    Toast.makeText(getActivity(), "This device does not have a camera.", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                //Check if the user have reached the spot or not
                if (getSpot) {
                    //send intent to CameraActivity
                    Intent intent = new Intent(getActivity(), CameraActivity.class);
                    startActivity(intent);
                } else {
                    //Shows that user still doesn't get the spot yet
                    //and ask them whether they want hint or not
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setMessage(R.string.hint_message);

                    alertDialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "hint",
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
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage(R.string.clue_switch_message);

                alertDialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //prev()
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
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage(R.string.clue_switch_message);

                alertDialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //next()
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
        });
        return rootView;
    }
}

package com.example.yhuang.scavengerhunt.Fragments;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;
import com.example.yhuang.scavengerhunt.R;
import com.example.yhuang.scavengerhunt.onClickListeners.ClueFetch.CameraListener;
import com.example.yhuang.scavengerhunt.onClickListeners.ClueFetch.NextListener;
import com.example.yhuang.scavengerhunt.onClickListeners.ClueFetch.PreviousListener;
import com.example.yhuang.scavengerhunt.onClickListeners.ClueFetch.VideoListener;

public class ClueFetch extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_clue_fetch, container, false);

        TextView curClue = (TextView) rootView.findViewById(R.id.currentClue); //current clue index
        TextView totalClue = (TextView) rootView.findViewById(R.id.totalClue); //total clue index
        VideoView clueVideo = (VideoView) rootView.findViewById(R.id.videoClue); //video for the current clue
        ImageButton camera = (ImageButton) rootView.findViewById(R.id.camera); //camera button
        ImageButton prev = (ImageButton) rootView.findViewById(R.id.prev); //travel to the previous clue
        ImageButton next = (ImageButton) rootView.findViewById(R.id.next); //skip to the next clue
        ImageButton video = (ImageButton) rootView.findViewById(R.id.video); //play the video

        GpsDetection gpsInfo = new GpsDetection(rootView.getContext()); // get the GPS detection fragment
        Context context = getActivity(); //get the context
        PackageManager packageManager = context.getPackageManager(); // get the Package Manager


        clueVideo.setVisibility(View.INVISIBLE); //hide the video initially

        video.setOnClickListener(new VideoListener(video, clueVideo, getActivity()));
        camera.setOnClickListener(new CameraListener(gpsInfo, getActivity(), packageManager));
        prev.setOnClickListener(new PreviousListener(getActivity()));
        next.setOnClickListener(new NextListener(getActivity()));
        return rootView;
    }
}

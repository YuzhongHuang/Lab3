package com.example.yhuang.scavengerhunt.Fragments;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.VideoView;
import com.example.yhuang.scavengerhunt.R;
import com.example.yhuang.scavengerhunt.onClickListeners.ClueFetch.CameraListener;
import com.example.yhuang.scavengerhunt.onClickListeners.ClueFetch.NextListener;
import com.example.yhuang.scavengerhunt.onClickListeners.ClueFetch.PreviousListener;
import com.example.yhuang.scavengerhunt.onClickListeners.ClueFetch.VideoListener;

import butterknife.Bind;
import butterknife.OnClick;

public class ClueFetch extends Fragment {

//    @Bind(R.id.currentClue) TextView curClue;
//    @Bind(R.id.totalClue) TextView totalClue;
    @Bind(R.id.videoClue) VideoView clueVideo;
    @Bind(R.id.camera) ImageButton camera;
    @Bind(R.id.prev) ImageButton prev;
    @Bind(R.id.next) ImageButton next;
    @Bind(R.id.video) ImageButton video;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_clue_fetch, container, false);

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

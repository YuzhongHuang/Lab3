package com.example.yhuang.scavengerhunt.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.yhuang.scavengerhunt.R;

public class ClueFetch extends Fragment {

    TextView curClue;
    TextView totalClue;
    VideoView clueVideo;
    ImageButton camera;

    public ClueFetch() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_clue_fetch, container, false);

        curClue = (TextView) rootView.findViewById(R.id.currentClue);
        totalClue = (TextView) rootView.findViewById(R.id.totalClue);
        clueVideo = (VideoView) rootView.findViewById(R.id.videoClue);
        camera = (ImageButton) rootView.findViewById(R.id.camera);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }


}

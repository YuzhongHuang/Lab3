package com.example.yhuang.scavengerhunt.onClickListeners.ClueFetch;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.example.yhuang.scavengerhunt.Utils.VideoPlayer;

/**
 * Created by yhuang on 10/19/2015.
 */
public class VideoListener implements View.OnClickListener {

    private ImageButton m_video;
    private VideoView m_clueVideo;
    private Activity m_activity;

    public VideoListener (ImageButton video, VideoView clueVideo, Activity activity) {
        m_video = video;
        m_clueVideo = clueVideo;
        m_activity = activity;
    }

    @Override public void onClick(View v) {
        m_video.setVisibility(View.INVISIBLE);
        m_clueVideo.setVisibility(View.VISIBLE);
        VideoPlayer.playVideo("MVI_3140.3gp", m_clueVideo, m_activity);
    }
}

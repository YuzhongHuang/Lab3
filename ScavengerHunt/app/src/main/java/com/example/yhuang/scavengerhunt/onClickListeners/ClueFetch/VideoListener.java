package com.example.yhuang.scavengerhunt.onClickListeners.ClueFetch;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.example.yhuang.scavengerhunt.MainActivity;
import com.example.yhuang.scavengerhunt.Utils.VideoPlayer;

/**
 * When the video button is clicked,
 * video listener will change the visibility
 * of video button and the video view,
 * and load the media to play video
 */

public class VideoListener implements View.OnClickListener {

    private ImageButton m_video;
    private VideoView m_clueVideo;
    private Activity m_activity;
    private int m_curClueNum;

    public VideoListener (int curClueNum, ImageButton video, VideoView clueVideo, Activity activity) {
        m_video = video;
        m_clueVideo = clueVideo;
        m_activity = activity;
        m_curClueNum = curClueNum;
    }

    @Override public void onClick(View v) {
        m_video.setVisibility(View.INVISIBLE);
        m_clueVideo.setVisibility(View.VISIBLE);
        VideoPlayer.playVideo(MainActivity.locationMap.get(m_curClueNum).s3video, m_clueVideo, m_activity);
    }
}

package com.artist.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.MediaController;
import android.widget.VideoView;

import com.artist.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {
    private VideoView videoView;

    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fragment3, container, false);
        videoView= (VideoView) view.findViewById(R.id.videoView);
        MediaController mc= new MediaController(view.getContext());
        videoView.setVideoPath("android.resource://com.artist/"
                + R.raw.video);
        videoView.setMediaController(mc);
        videoView.requestFocus();
        videoView.start();

        return view;
    }



}

package com.artist.ui;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.artist.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {

    private MediaPlayer mediaPlayer;
    private int playbackPosition=0;
    private Button pauseButton;
    private Button restartButton;
    private TextView music1;
    private TextView music2;


    public Fragment2() {
        // Required empty public constructor;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fragment2, container, false);
        pauseButton= (Button) view.findViewById(R.id.button2);
        restartButton=(Button) view.findViewById(R.id.button3);
        music1= (TextView) view.findViewById(R.id.musicTextView1);
        music2= (TextView) view.findViewById(R.id.musicTextView2);
        music1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(R.raw.music1);
            }
        });
        music2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(R.raw.music2);
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer!=null){
                    playbackPosition= mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                }
            }
        });
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer!=null){
                    mediaPlayer.seekTo(playbackPosition);
                    mediaPlayer.start();
                }
            }
        });

        return view;
    }


    private void playAudio(int music) {
        killMediaPlayer();
        mediaPlayer= MediaPlayer.create(getView().getContext(),music);
        mediaPlayer.start();

    }

    private void killMediaPlayer(){
        if(mediaPlayer!=null) mediaPlayer.release();
    }


}

package com.example.newyearcard;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {
    MediaPlayer mPlayer;

    public AudioPlayer(Context context){
        mPlayer = MediaPlayer.create(context, R.raw.avariya);
    }

    public void stop(){
        if(mPlayer != null){
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void play(){
        if(mPlayer != null){
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stop();
                }
            });
            mPlayer.start();

        }
    }
}

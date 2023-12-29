package com.example.newyearcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    AppCompatButton musicButton;
    ImageView card;
    AudioPlayer player;
    SoundPool soundPool;
    AudioAttributes attributes;
    int soundId1, soundId2, streamId1, streamId2;
    boolean isSoundLoad = false;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = new Random();
        musicButton = findViewById(R.id.music_button);
        card = findViewById(R.id.card);
        player = new AudioPlayer(this);
        //создание объектов для коротких звуков
        attributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
        //загрузка треков и её контроль
        soundId1 = soundPool.load(this, R.raw.bells, 1);
        soundId2 = soundPool.load(this, R.raw.shapm, 1);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                isSoundLoad = true;
            }
        });

        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.play();
            }
        });

        card.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int k = random.nextInt(500);
                if(isSoundLoad){
                    if(k < 250)
                        soundPool.play(soundId1, 1, 1, 1, 3, 1);
                    else
                        soundPool.play(soundId2, 1, 1, 1, 0, 1);
                }
                return false;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        player = new AudioPlayer(this);
    }
}
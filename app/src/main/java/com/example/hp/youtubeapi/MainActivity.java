package com.example.hp.youtubeapi;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity  {

    Button b;
    private YouTubePlayerView youTubePlayer;
    private YouTubePlayer.OnInitializedListener listener;
//    @SuppressLint("InlinedApi")
//    private static final int PORTRAIT_ORIENTATION = Build.VERSION.SDK_INT < 9
//            ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;
//
//    @SuppressLint("InlinedApi")
//    private static final int LANDSCAPE_ORIENTATION = Build.VERSION.SDK_INT < 9
//            ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
//
//    private YouTubePlayer mPlayer = null;
//    private boolean mAutoRotation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mAutoRotation = Settings.System.getInt(getContentResolver(),
//                Settings.System.ACCELEROMETER_ROTATION, 0) == 1;
        final String id = getIntent().getStringExtra("id");
        youTubePlayer = (YouTubePlayerView)findViewById(R.id.youtubeplayer);

        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                //youTubePlayer.setFullscreen(true);
                //mPlayer = youTubePlayer;
                //youTubePlayer.setOnFullscreenListener();

//                if (mAutoRotation) {
//                    youTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION
//                            | YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI
//                            | YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE
//                            | YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
//                } else {
//                    youTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION
//                            | YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI
//                            | YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
//                }
                youTubePlayer.loadVideo(id);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youTubePlayer.initialize("AIzaSyCjcIu3lFJR7_lvdTAB553_ZXFcLg3NmcY",listener);

//        @Override
//        public void onConfigurationChanged(Configuration newConfig) {
//            super.onConfigurationChanged(newConfig);
//
//            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                if (mPlayer != null)
//                    mPlayer.setFullscreen(true);
//            }
//            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//                if (mPlayer != null)
//                    mPlayer.setFullscreen(false);
//            }
//        }
//
//        @Override
//        public void onFullscreen(boolean fullsize) {
//            if (fullsize) {
//                setRequestedOrientation(LANDSCAPE_ORIENTATION);
//            } else {
//                setRequestedOrientation(PORTRAIT_ORIENTATION);
//            }
//        }

//        b = findViewById(R.id.btn);
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
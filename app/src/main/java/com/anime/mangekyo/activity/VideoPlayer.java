package com.anime.mangekyo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.anime.mangekyo.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class VideoPlayer extends Activity {

    StyledPlayerView videoPlayer;
    ExoPlayer player;
    ProgressBar videoLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        videoPlayer = findViewById(R.id.videoPlayer);
        videoLoader = findViewById(R.id.videoLoader);
        player = new ExoPlayer.Builder(this).build();

        Intent intent = getIntent();
        String _360p = intent.getStringExtra("360p");
        String _480p = intent.getStringExtra("480p");
        String _720p = intent.getStringExtra("720p");
        String _1080p = intent.getStringExtra("1080p");
        String _default = intent.getStringExtra("default");
        String[] qualities = {_default, _1080p, _720p, _480p, _360p};
        String url = getUrl(qualities);
        videoPlayer.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(url);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int playbackState) {
                if(playbackState == Player.STATE_BUFFERING){
                    videoLoader.setVisibility(View.VISIBLE);
                } else if (playbackState == Player.STATE_READY) {
                    videoLoader.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onStop() {

        super.onStop();
        if(player != null){
            player.stop();
        }
    }

    public String getUrl(String[] qualities){
        for(String quality: qualities){
            if(quality != null){
                return  quality;
            }
        }

        return "";
    }
}

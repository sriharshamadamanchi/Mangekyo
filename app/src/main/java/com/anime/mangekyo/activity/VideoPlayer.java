package com.anime.mangekyo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.Player;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.ui.PlayerView;

import com.anime.mangekyo.R;

public class VideoPlayer extends Activity {

    PlayerView videoPlayer;
    ExoPlayer player;
    ProgressBar videoLoader;
    TextView episodeTitle;
    ImageButton videoSettings;

    @SuppressLint("UnsafeOptInUsageError")
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
        episodeTitle = findViewById(R.id.episodeTitle);
        videoSettings = findViewById(R.id.videoSettings);
        videoSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), videoSettings);

                popupMenu.getMenuInflater().inflate(R.menu.video_quality, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId() == R.id.videoQuality){

                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        @SuppressLint("UnsafeOptInUsageError") DefaultTrackSelector trackSelector = new DefaultTrackSelector(this);
        trackSelector.setParameters(
                trackSelector.buildUponParameters().setAllowVideoMixedMimeTypeAdaptiveness(true));
        DefaultLoadControl loadControl = new DefaultLoadControl.Builder()
                .setBackBuffer(30 * 60000, true)
                .setBufferDurationsMs(30 * 60000, 30 * 60000, 1500, 2000)
                .build();

        player = new ExoPlayer.Builder(this)
                .setTrackSelector(trackSelector)
                .setLoadControl(loadControl)
                .build();

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String _360p = intent.getStringExtra("360p");
        String _480p = intent.getStringExtra("480p");
        String _720p = intent.getStringExtra("720p");
        String _1080p = intent.getStringExtra("1080p");
        String _default = intent.getStringExtra("default");
        String[] qualities = {_default, _1080p, _720p, _480p, _360p};
        String url = getUrl(qualities);
        videoPlayer.findViewById(androidx.media3.ui.R.id.exo_settings).setVisibility(View.GONE);

        episodeTitle.setText(title);
        videoPlayer.setPlayer(player);
        videoPlayer.setControllerVisibilityListener((PlayerView.ControllerVisibilityListener) visibility -> {
            episodeTitle.setVisibility(visibility);
            // videoSettings.setVisibility(visibility);
        });
        DataSource.Factory dataSourceFactory = new DefaultHttpDataSource.Factory();
        HlsMediaSource hlsMediaSource =
                new HlsMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(MediaItem.fromUri(url));
        player.setMediaSource(hlsMediaSource);
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
    protected void onStart() {
        super.onStart();
        if(player != null){
            player.play();
        }
        if(videoPlayer != null){
            videoPlayer.onResume();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(player != null){
            player.play();
        }
        if(videoPlayer != null){
            videoPlayer.onResume();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(player != null){
            player.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    public String getUrl(String[] qualities){
        for(String quality: qualities){
            if(quality != null){
                return  quality;
            }
        }

        return "";
    }

    private void releasePlayer(){
        if(player != null){
            player.release();
            player = null;
        }
        if(videoPlayer != null){
            videoPlayer.setPlayer(null);
        }
    }
}

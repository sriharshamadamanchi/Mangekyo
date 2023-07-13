package com.anime.mangekyo.fragment;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.ui.PlayerView;

import com.anime.mangekyo.R;

public class VideoPlayerFragment extends Fragment {
    PlayerView videoPlayer;
    ExoPlayer player;
    ProgressBar videoLoader;
    TextView episodeTitle;
    ImageButton videoSettings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requireActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_player, container, false);
    }

    @SuppressLint("UnsafeOptInUsageError")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        videoPlayer = view.findViewById(R.id.videoPlayer);
        videoLoader = view.findViewById(R.id.videoLoader);
        episodeTitle = view.findViewById(R.id.episodeTitle);
        videoSettings = view.findViewById(R.id.videoSettings);
        videoSettings.setOnClickListener(it -> {
            PopupMenu popupMenu = new PopupMenu(requireContext().getApplicationContext(), videoSettings);

            popupMenu.getMenuInflater().inflate(R.menu.video_quality, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getItemId() == R.id.videoQuality) {

                }
                return true;
            });
            popupMenu.show();
        });

        @SuppressLint("UnsafeOptInUsageError") DefaultTrackSelector trackSelector = new DefaultTrackSelector(requireContext());
        trackSelector.setParameters(
                trackSelector.buildUponParameters().setAllowVideoMixedMimeTypeAdaptiveness(true));
        DefaultLoadControl loadControl = new DefaultLoadControl.Builder()
                .setBackBuffer(30 * 60000, true)
                .setBufferDurationsMs(30 * 60000, 30 * 60000, 1500, 2000)
                .build();

        player = new ExoPlayer.Builder(requireContext())
                .setTrackSelector(trackSelector)
                .setLoadControl(loadControl)
                .build();

        VideoPlayerFragmentArgs videoPlayerFragmentArgs = VideoPlayerFragmentArgs.fromBundle(requireArguments());

        String title = videoPlayerFragmentArgs.getTitle();
        String _360p = videoPlayerFragmentArgs.getP360();
        String _480p = videoPlayerFragmentArgs.getP480();
        String _720p = videoPlayerFragmentArgs.getP720();
        String _1080p = videoPlayerFragmentArgs.getP1080();
        String _default = videoPlayerFragmentArgs.getPdefault();
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
                if (playbackState == Player.STATE_BUFFERING) {
                    videoLoader.setVisibility(View.VISIBLE);
                } else if (playbackState == Player.STATE_READY) {
                    videoLoader.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (player != null) {
            player.play();
        }
        if (videoPlayer != null) {
            videoPlayer.onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (player != null) {
            player.play();
        }
        if (videoPlayer != null) {
            videoPlayer.onResume();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (player != null) {
            player.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
        requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    public String getUrl(String[] qualities) {
        for (String quality : qualities) {
            if (quality != null) {
                return quality;
            }
        }

        return "";
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
        if (videoPlayer != null) {
            videoPlayer.setPlayer(null);
        }
    }
}
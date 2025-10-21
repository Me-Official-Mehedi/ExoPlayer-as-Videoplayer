package com.example.exoplayer;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

public class MainActivity extends AppCompatActivity {

    PlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Initialize
        playerView = findViewById(R.id.playerView);
        ExoPlayer exoPlayer = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(exoPlayer);

        String videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";
        MediaItem mediaItem = MediaItem.fromUri(videoUrl);
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.play();

        playerView.setFullscreenButtonClickListener(isFullscreen -> {
            if (isFullscreen) {
                playerView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                float pxHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, getResources().getDisplayMetrics());
                ViewGroup.LayoutParams layoutParams = playerView.getLayoutParams();
                layoutParams.height = (int) pxHeight;
                playerView.setLayoutParams(layoutParams);
            }
        });


    }
}
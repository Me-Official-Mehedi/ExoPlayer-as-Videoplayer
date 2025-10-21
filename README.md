# ExoPlayer-as-VideoPlayer
This project helps to use exoplayer as videoplayer. This is a built in product from Google.

# VideoPlayer---Expplayer
This is the example of use exoplayer as a video player. (Basic to Advance)

# Manifest Permission
```
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
```

# Add this line in <activity tag> android:configChanges="orientation|screenSize|layoutDirection" for the videoView Activity
```
<activity
            android:name=".MainActivity"
            android:configChanges="orientation|screenSize|layoutDirection"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
```

# Add gradle from net https://developer.android.com/media/media3/exoplayer/hello-world#groovy
```
//Video Player Library
    implementation "androidx.media3:media3-exoplayer:1.8.0"
    implementation "androidx.media3:media3-exoplayer-dash:1.8.0"
    implementation "androidx.media3:media3-ui:1.8.0"

```

# activity_main.xml
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.media3.ui.PlayerView
        android:id="@+id/playerView"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:focusable="true"
        android:focusableInTouchMode="true"
        android:keepScreenOn="true"
        android:padding="0dp"
        app:auto_show="true"
        app:resize_mode="fill"
        app:show_buffering="when_playing"
        app:show_shuffle_button="true"
        app:show_vr_button="true"
        app:use_controller="true"
        />

</RelativeLayout>
```

# MainActivity.java
```
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

        //=================================================================================

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
    //=================================================================================

    }
}
```

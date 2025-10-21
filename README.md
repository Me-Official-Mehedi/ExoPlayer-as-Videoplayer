## 🎬 ExoPlayer-as-VideoPlayer

This project demonstrates how to use Google’s ExoPlayer as a fully functional video player in your Android app.
It provides a clean example setup with essential permissions, Gradle dependencies, XML layout, and Java implementation.

## 📑 Table of Contents
📘 Overview
🧾 Manifest Permissions
⚙️ Activity Configuration
📦 Gradle Dependencies
🧩 Layout: activity_main.xml
💻 Code: MainActivity.java
📚 References
👨‍💻 Author
📘 Overview


ExoPlayer is an open-source media player library developed by Google for Android.
It provides advanced features compared to the native MediaPlayer, such as DASH streaming, DRM, and smooth playback customization.

This project helps you integrate ExoPlayer as a video player in your Android app with minimal setup.

## 🧾 Manifest Permissions

Add the following permissions inside your AndroidManifest.xml file:

<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />

## ⚙️ Activity Configuration

In your AndroidManifest.xml, add the following inside your <activity> tag for the VideoPlayer Activity:

<activity
    android:name=".MainActivity"
    android:configChanges="orientation|screenSize|layoutDirection"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>


The android:configChanges flag ensures the player handles orientation and screen size changes smoothly.

## 📦 Gradle Dependencies

Add the following ExoPlayer dependencies to your app-level build.gradle file.
(Official reference: ExoPlayer Hello World)

// Video Player Library
implementation "androidx.media3:media3-exoplayer:1.8.0"
implementation "androidx.media3:media3-exoplayer-dash:1.8.0"
implementation "androidx.media3:media3-ui:1.8.0"

## 🧩 Layout: activity_main.xml

Your main layout includes the PlayerView for displaying video content.
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
        app:auto_show="true"
        app:resize_mode="fill"
        app:show_buffering="when_playing"
        app:show_shuffle_button="true"
        app:show_vr_button="true"
        app:use_controller="true" />
</RelativeLayout>
```

## 💻 Code: MainActivity.java

Below is the full implementation using ExoPlayer to load and play a sample MP4 video.

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

        // Initialize ExoPlayer
        playerView = findViewById(R.id.playerView);
        ExoPlayer exoPlayer = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(exoPlayer);

        String videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";
        MediaItem mediaItem = MediaItem.fromUri(videoUrl);
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.play();

        // Handle fullscreen toggle
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
```
## 📚 References

Official ExoPlayer Documentation
Android Developers Guide

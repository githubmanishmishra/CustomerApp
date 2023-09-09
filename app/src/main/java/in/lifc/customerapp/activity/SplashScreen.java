package in.lifc.customerapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import in.lifc.customerapp.R;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    VideoView videoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        videoView =  findViewById(R.id.videoView);
        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        videoView.setMediaController(mc);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.logo_vid);
        videoView.setVideoURI(video);
        videoView.setOnPreparedListener(PreparedListener);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                startNextActivity();
            }
        });

        videoView.start();
    }

    private void startNextActivity() {
        if (isFinishing())
            return;
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
    MediaPlayer.OnPreparedListener PreparedListener = m -> {
        try {
            if (m.isPlaying()) {
                m.stop();
                m.release();
                m = new MediaPlayer();
            }
            m.setVolume(0f, 0f);
            m.setLooping(false);
            m.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
}

package in.lifc.customerapp.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import in.lifc.customerapp.R;

public class ChooseLanguageActivity extends AppCompatActivity {

    private View mPendulum;
    private Animation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);

        mPendulum = findViewById(R.id.pendulum);
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.swinging);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPendulum.startAnimation(mAnimation);
    }

    @Override
    public void onPause() {
        mPendulum.clearAnimation();
        super.onPause();
    }
}
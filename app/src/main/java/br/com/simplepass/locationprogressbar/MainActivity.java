package br.com.simplepass.locationprogressbar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import br.com.simplepass.locationprogressbarlib.LocationProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.location_progress_bar)
    LocationProgressBar locationProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        startAnimation();
    }

    @OnClick(R.id.btn_start)
    protected void startAnimation(){
        LocationProgressBar.AnimationConfig animationConfig = new LocationProgressBar.AnimationConfig(
                0, //From progress
                100, //To progress
                800, // Duration
                600); //Start delay

        locationProgressBar.configAnimation(animationConfig);
        locationProgressBar.animateProgress();
    }
}

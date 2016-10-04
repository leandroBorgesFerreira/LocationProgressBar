package br.com.simplepass.locationprogressbarlib;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by hinovamobile on 03/10/16.
 */
public class LocationProgressDrawable extends Drawable implements Animatable {

    ProgressBar mProgressBar;

    public LocationProgressDrawable(ProgressBar mProgressBar) {
        this.mProgressBar = mProgressBar;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        Log.d("Progress", "Progress: " + mProgressBar.getProgress());
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}

package br.com.simplepass.locationprogressbarlib;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;

/**
 * Created by hinovamobile on 03/10/16.
 */
public class LocationProgressBar extends ProgressBar{
    private LocationProgressDrawable mLocationProgressDrawable;

    public LocationProgressBar(Context context) {
        super(context);
        init();
    }

    public LocationProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LocationProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(23)
    public LocationProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    private void init(){
        setMax(100);
        setIndeterminate(false);

        mLocationProgressDrawable = new LocationProgressDrawable(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_place),
                this);

        mLocationProgressDrawable.setCallback(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mLocationProgressDrawable.draw(canvas);
    }

    public void animateProgress(int from, int to){
        setProgress(from);

        ValueAnimator valueAnimator = ValueAnimator.ofInt(from, to);
        valueAnimator.setDuration(500);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                setProgress(value);
                invalidate();
            }
        });

        valueAnimator.start();
        mLocationProgressDrawable.start(from, to, 500);

    }

}

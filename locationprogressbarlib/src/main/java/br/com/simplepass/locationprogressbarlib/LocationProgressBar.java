package br.com.simplepass.locationprogressbarlib;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
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

    public LocationProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        /*mProgressDrawable = new LocationProgressDrawable(this);
        mProgressDrawable.setCallback(this);*/

        setMax(100);
        setIndeterminate(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mLocationProgressDrawable != null) {
            mLocationProgressDrawable.draw(canvas);
        }
    }

    public void animateProgress(int from, int to){
        setProgress(from);

        mLocationProgressDrawable = new LocationProgressDrawable(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher),
                this,
                from,
                to,
                300);

        mLocationProgressDrawable.setCallback(this);

        ValueAnimator valueAnimator = ValueAnimator.ofInt(from, to);
        valueAnimator.setDuration(300);
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
        mLocationProgressDrawable.start();

    }

}

package br.com.simplepass.locationprogressbarlib;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;

/**
 * Created by Leandro Ferreira on 03/10/16.
 *
 * This class is a extention of ProgressBar. It animation and it is make for a progress of location.
 */
public class LocationProgressBar extends ProgressBar{
    private LocationProgressDrawable mLocationProgressDrawable;

    /**
     *
     * @param context
     */
    public LocationProgressBar(Context context) {
        super(context);
        init();
    }

    /**
     *
     * @param context
     * @param attrs
     */
    public LocationProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public LocationProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    @TargetApi(23)
    public LocationProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    /**
     * Commom initiation method of all constructors.
     */
    private void init(){
        setMax(100);
        setIndeterminate(false);

        mLocationProgressDrawable = new LocationProgressDrawable(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_place),
                this);

        mLocationProgressDrawable.setCallback(this);
    }

    /**
     * Method called when the animation is going to be draw in the progress bar and all its dependencies.
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mLocationProgressDrawable.draw(canvas);
    }

    /**
     * Main method. You must call this method to animate the progress of the progress bar.
     *
     * @param from The starting value of the progress bar. From 0-100.
     * @param to The final value of the progress bar, after the animation. From 0-100.
     * @param durationMilis duration of the animation
     */
    public void animateProgress(int from, int to, int durationMilis){
        setProgress(from);

        ValueAnimator valueAnimator = ValueAnimator.ofInt(from, to);
        valueAnimator.setDuration(durationMilis);
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
        mLocationProgressDrawable.startConfigAndStart(from, to, durationMilis);

    }

    /**
     * Main method. You must call this method to animate the progress of the progress bar.
     *
     * @param from The starting value of the progress bar. From 0-100.
     * @param to The final value of the progress bar, after the animation. From 0-100.
     */
    public void animateProgress(int from, int to){
        animateProgress(from, to, 500);
    }

}

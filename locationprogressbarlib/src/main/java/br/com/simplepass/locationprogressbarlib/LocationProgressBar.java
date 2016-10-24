package br.com.simplepass.locationprogressbarlib;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
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
    private ValueAnimator mValueAnimator;
    private AnimationConfig mAnimationConfig;

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
    }



    /**
     * Method to config the animation
     *
     * @param animationConfig Data holder of all the animation params
     */
    public void configAnimation(AnimationConfig animationConfig){
        if(animationConfig == null || animationConfig.durationMilis == 0){
            throw new IllegalStateException("Wrong AnimationConfig");
        }

        mAnimationConfig = animationConfig;

        setProgress(animationConfig.from);

        mValueAnimator = ValueAnimator.ofInt(animationConfig.from, animationConfig.to);
        mValueAnimator.setDuration(animationConfig.durationMilis);
        mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                setProgress(value);
                invalidate();
            }
        });

        if(animationConfig.startDelay != 0){
            mValueAnimator.setStartDelay(animationConfig.startDelay);
        } else{
            mValueAnimator.setStartDelay(AnimationConfig.DEFAULT_START_DELAY_MILLIS);
        }
    }

    /**
     * Method called when the animation is going to be draw in the progress bar and all its dependencies.
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mLocationProgressDrawable == null) {
            mLocationProgressDrawable = new LocationProgressDrawable(
                    BitmapFactory.decodeResource(getResources(), R.drawable.ic_place),
                    this);
            mLocationProgressDrawable.configAnimation(mAnimationConfig);
            mLocationProgressDrawable.setCallback(this);
        }

        mLocationProgressDrawable.draw(canvas);
    }

    /**
     * Main method. You must call this method to animate the progress of the progress bar.
     *
     */
    public void animateProgress(){
        mValueAnimator.start();
    }

    public static class AnimationConfig{
        private int from, to, durationMilis, startDelay;

        public static final int DEFAULT_START_DELAY_MILLIS = 600;

        public AnimationConfig() {}

        public AnimationConfig(int from, int to, int durationMilis, int startDelay) {
            this.from = from;
            this.to = to;
            this.durationMilis = durationMilis;
            this.startDelay = startDelay;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getDurationMilis() {
            return durationMilis;
        }

        public void setDurationMilis(int durationMilis) {
            this.durationMilis = durationMilis;
        }

        public int getStartDelay() {
            return startDelay;
        }

        public void setStartDelay(int startDelay) {
            this.startDelay = startDelay;
        }
    }

}

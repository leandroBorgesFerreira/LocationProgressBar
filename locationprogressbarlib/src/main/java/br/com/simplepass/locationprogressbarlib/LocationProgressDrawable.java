package br.com.simplepass.locationprogressbarlib;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;

/**
 * Created by Leandro Ferreira on 03/10/16.
 *
 * Drawable responsible for the animations of the Custom View
 */
public class LocationProgressDrawable extends Drawable implements Animatable {

    private Paint mPaintPoint;
    private Bitmap mPointImage;
    private ProgressBar mProgressBar;
    private float mProgressCorrectionFactor;
    private ValueAnimator mValueAnimator;

    /**
     * @param bitmap The mapPoint image, passed as a Bitmap.
     * @param progressBar The progress bar to be animated
     */
    public LocationProgressDrawable(Bitmap bitmap, ProgressBar progressBar) {
        mPaintPoint = new Paint();
        mPaintPoint.setAntiAlias(true);
        mPaintPoint.setStyle(Paint.Style.FILL);
        mPaintPoint.setColor(Color.BLACK);
        mPaintPoint.setTextSize(50);


        mProgressBar = progressBar;

        mPointImage = Bitmap.createScaledBitmap(bitmap, 120, 120, false);
        //mPointImage = bitmap;
    }


    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
    }

    /**
     * Method call when the animation is going to be drawn. It update de mapPoint position and the
     * text of the view
     *
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        float width = (mProgressBar.getProgress() * mProgressCorrectionFactor) - mPointImage.getWidth()/2;

        canvas.drawBitmap(mPointImage,
                width + mProgressBar.getPaddingLeft(),
                //-mPointImage.getHeight()/2 -15,
                mProgressBar.getHeight()/2 - mPointImage.getHeight(),
                //0,
                mPaintPoint);
        canvas.drawText(String.format(mProgressBar.getContext().getString(R.string.format_percetagem), String.valueOf(mProgressBar.getProgress())),
                width + mProgressBar.getPaddingLeft() + mPointImage.getWidth()/4,
                mProgressBar.getHeight()/2 - mPointImage.getHeight(),
                mPaintPoint);
    }


    @Override
    public void setAlpha(int i) {
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    /**
     * Starts the animation
     */
    @Override
    public void start() {
        if(mValueAnimator == null) {
            throw new IllegalStateException("You must first call configAnimation(int from, int to, int duration)!");
        }

        mValueAnimator.start();
    }

    /**
     * Stops the animation
     */
    @Override
    public void stop() {
        mValueAnimator.cancel();
    }

    @Override
    public boolean isRunning() {
        return mValueAnimator.isRunning();
    }

    /**
     * Method to config the animation
     *
     * @param animationConfig Data holder of all the animation params
     */
    public void configAnimation(LocationProgressBar.AnimationConfig animationConfig){
        mValueAnimator = ValueAnimator.ofInt(animationConfig.getFrom(), animationConfig.getTo());
        mValueAnimator.setDuration(animationConfig.getDurationMilis());
        mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        if(animationConfig.getStartDelay() != 0){
            mValueAnimator.setStartDelay(animationConfig.getStartDelay());
        } else{
            mValueAnimator.setStartDelay(LocationProgressBar.AnimationConfig.DEFAULT_START_DELAY_MILLIS);
        }

        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                invalidateSelf();
            }
        });

        calculateCorrectionFactor();
    }

    public void calculateCorrectionFactor(){
        mProgressCorrectionFactor = (float) (mProgressBar.getWidth() -
                mProgressBar.getPaddingLeft() -
                mProgressBar.getPaddingRight()) /
                mProgressBar.getMax();
    }
}




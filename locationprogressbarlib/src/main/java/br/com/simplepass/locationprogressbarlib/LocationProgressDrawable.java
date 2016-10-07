package br.com.simplepass.locationprogressbarlib;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by hinovamobile on 03/10/16.
 */
public class LocationProgressDrawable extends Drawable implements Animatable {

    private Paint mPaintPoint;
    private Bitmap mPointImage;
    private ProgressBar mProgressBar;
    private float mProgressCorrectionFactor;
    ValueAnimator mValueAnimator;


    public LocationProgressDrawable(Bitmap bitmap, ProgressBar progressBar) {
        mPaintPoint = new Paint();
        mPaintPoint.setAntiAlias(true);
        mPaintPoint.setStyle(Paint.Style.FILL);
        mPaintPoint.setColor(Color.BLACK);


        mProgressBar = progressBar;

        mPointImage = bitmap;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);

    }

    @Override
    public void draw(Canvas canvas) {
        Log.d("Draw", "Está desenhando. Progresso:  " + mProgressBar.getProgress());
        canvas.drawBitmap(mPointImage, mProgressBar.getProgress() * mProgressCorrectionFactor, 0, mPaintPoint);
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

    @Override
    public void start() {
        mValueAnimator.start();
    }

    @Override
    public void stop() {
        mValueAnimator.cancel();
    }

    @Override
    public boolean isRunning() {
        return mValueAnimator.isRunning();
    }

    public void start(int from, int to, int duration){
        mValueAnimator = ValueAnimator.ofInt(from, to);
        mValueAnimator.setDuration(duration);

        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                invalidateSelf();
            }
        });

        mProgressCorrectionFactor = mProgressBar.getWidth()/mProgressBar.getMax();

        start();
    }
}




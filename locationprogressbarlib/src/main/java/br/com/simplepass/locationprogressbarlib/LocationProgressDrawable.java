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
import android.view.animation.AccelerateDecelerateInterpolator;
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

        mPointImage = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
        //mPointImage = bitmap;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);

    }

    @Override
    public void draw(Canvas canvas) {
        Log.d("Draw", "Está desenhando. Progresso:  " + mProgressBar.getProgress());
        float width = (mProgressBar.getProgress() * mProgressCorrectionFactor) - mPointImage.getWidth()/2;

        Log.d("Draw", "Está desenhando. Width:  " + width);
        Log.d("Draw", "Está desenhando. Width sem correcao:  " + (mProgressBar.getProgress() * mProgressCorrectionFactor));


        canvas.drawBitmap(mPointImage,
                width,
                //-mPointImage.getHeight()/2 -15,
                mProgressBar.getHeight()/2 - mPointImage.getHeight(),
                //0,
                mPaintPoint);

        Log.d("Draw", "Está desenhando. Height PB:  " + mProgressBar.getHeight());
        Log.d("Draw", "Está desenhando. Height IMAGE:  " + mPointImage.getHeight());
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
        mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                invalidateSelf();
            }
        });

        mProgressCorrectionFactor = (float) mProgressBar.getWidth()/mProgressBar.getMax();

        start();
    }
}




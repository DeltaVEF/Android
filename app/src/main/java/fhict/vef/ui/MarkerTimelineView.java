package fhict.vef.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import fhict.vef.R;

public class MarkerTimelineView extends View {
    private final String TAG = "MarkerTimeline";

    //DIMENSIONS
    private final int MINIMUM_HEIGHT;
    private final int MARKER_WIDTH, MARKER_HEIGHT;
    private final Path MARKER_PATH;

    //PAINTS
    private final Paint PERSONAL_MARKER_PAINT;

    private int secondsPerHalfWidth = 0;
    private int currentTimeInSeconds = 0;

    public MarkerTimelineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarkerTimelineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        ////////////////////////////////////////
        // RETRIEVE ALL CUSTOM ATTRIBUTES
        final TypedArray a = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.MarkerTimelineView, 0, 0);
        try {
            secondsPerHalfWidth = a
                    .getInteger(R.styleable.MarkerTimelineView_secondsPerHalfWidth, 0);
            Log.d(TAG, String.format("Seconds per Half Width: %d", secondsPerHalfWidth));
        } finally {
            a.recycle();
        }

        ////////////////////////////////////////////
        // INITIALIZE ALL THE DIMENSIONS AND PAINTS
        MINIMUM_HEIGHT = UITools.convertDpToPx(context, 64);
        MARKER_WIDTH = UITools.convertDpToPx(context, 24);
        MARKER_HEIGHT = UITools.convertDpToPx(context, 32);

        PERSONAL_MARKER_PAINT = new Paint();
        PERSONAL_MARKER_PAINT.setColor(Color.WHITE);
        PERSONAL_MARKER_PAINT.setStyle(Paint.Style.FILL);

        ////////////////////////////////////////////
        // INITIALIZE PATHS
        MARKER_PATH = new Path();
        MARKER_PATH.moveTo(MARKER_WIDTH / 2, 0);             //TOP CORNER
        MARKER_PATH.lineTo(MARKER_WIDTH, MARKER_WIDTH / 2);   //RIGHT CORNER
        MARKER_PATH.lineTo(MARKER_WIDTH / 2, MARKER_HEIGHT);  //BOTTOM CORNER
        MARKER_PATH.lineTo(0, MARKER_WIDTH / 2);             //LEFT CORNER
        MARKER_PATH.close();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int width = MeasureSpec.getSize(widthMeasureSpec),
                height = resolveSize(MINIMUM_HEIGHT, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.v(TAG, "onDraw");
        canvas.drawColor(Color.MAGENTA);

        canvas.save();
        canvas.translate(getWidth() / 2 - MARKER_WIDTH / 2, getHeight() / 2 - MARKER_HEIGHT / 2);
        canvas.drawPath(MARKER_PATH, PERSONAL_MARKER_PAINT);
        canvas.restore();
    }

    public int getSecondsPerHalfWidth() {
        return secondsPerHalfWidth;
    }

    public void setSecondsPerHalfWidth(int seconds) {
        secondsPerHalfWidth = seconds > 0 ? seconds : 0;
        invalidate();
    }

    public int getCurrentTimeInSeconds() {
        return currentTimeInSeconds;
    }

    public void setCurrentTimeInSeconds(int seconds) {
        currentTimeInSeconds = seconds > 0 ? seconds : 0;
        invalidate();
    }
}
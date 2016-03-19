package fhict.vef.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import fhict.vef.R;

public class MarkerTimelineView extends View {
    private final int MINIMUM_HEIGHT;
    private final int MARKER_WIDTH, MARKER_HEIGHT;
    private final Path MARKER_PATH;

    private final ArrayList<Marker> markers = new ArrayList<>();

    private int secondsPerHalfWidth = 0;
    private int currentTimeInSeconds = 0;

    public MarkerTimelineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarkerTimelineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        ////////////////////////////////////
        // RETRIEVE ALL CUSTOM ATTRIBUTES
        final TypedArray a = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.MarkerTimelineView, 0, 0);
        try {
            secondsPerHalfWidth = a
                    .getInteger(R.styleable.MarkerTimelineView_secondsPerHalfWidth, 0);
        } finally {
            a.recycle();
        }

        ////////////////////////////////////
        // INITIALIZE ALL THE DIMENSIONS
        MINIMUM_HEIGHT = UITools.convertDpToPx(context, 64);
        MARKER_WIDTH = UITools.convertDpToPx(context, 24);
        MARKER_HEIGHT = UITools.convertDpToPx(context, 32);

        ////////////////////////////////////
        // INITIALIZE PATHS
        MARKER_PATH = new Path();
        MARKER_PATH.moveTo(MARKER_WIDTH / 2, 0);                //TOP CORNER
        MARKER_PATH.lineTo(MARKER_WIDTH, MARKER_WIDTH / 2);     //RIGHT CORNER
        MARKER_PATH.lineTo(MARKER_WIDTH / 2, MARKER_HEIGHT);    //BOTTOM CORNER
        MARKER_PATH.lineTo(0, MARKER_WIDTH / 2);                //LEFT CORNER
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
        canvas.drawColor(Color.MAGENTA);
        for (Marker marker : markers) {
            marker.draw(canvas);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (h != oldh) {
            for (Marker marker : markers) {
                marker.onParentChangedSize(w, h);
            }
        }
    }

    public void addMarker() {
        markers.add(new Marker(Color.WHITE));
    }

    public void setCurrentTimeInSeconds(int seconds) {
        currentTimeInSeconds = seconds > 0 ? seconds : 0;
        updateMarkerPositions();
        invalidate();
    }

    private void updateMarkerPositions() {
        for (Marker marker : markers) {
            marker.updateHorizontalPosition();
        }
    }

    public class Marker {
        private final int markTimeInSeconds;
        private final Path path = new Path(MARKER_PATH);
        private final Paint paint = new Paint();
        private final Point position = new Point();

        private int horizontalMidPosition = 0;
        private int stepPixelsPerSecond = 0;
        private boolean shouldDraw = true;

        public Marker(int color) {
            this.markTimeInSeconds = currentTimeInSeconds;
            init(color);
        }

        private void init(int color) {
            onParentChangedSize(getWidth(), getHeight());
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(color);
        }

        public void updateHorizontalPosition() {
            position.x = horizontalMidPosition -
                    stepPixelsPerSecond * (currentTimeInSeconds - markTimeInSeconds);

            if (position.x < -MARKER_WIDTH) {
                shouldDraw = false;
            }
        }

        public void onParentChangedSize(int newWidth, int newHeight) {
            horizontalMidPosition = newWidth / 2 - MARKER_WIDTH / 2;
            position.y = newHeight / 2 - MARKER_HEIGHT / 2;
            stepPixelsPerSecond = newWidth / 2 / secondsPerHalfWidth;
        }

        public void draw(Canvas canvas) {
            if (shouldDraw) {
                canvas.save();
                canvas.translate(position.x, position.y);
                canvas.drawPath(path, paint);
                canvas.restore();
            }
        }
    }
}
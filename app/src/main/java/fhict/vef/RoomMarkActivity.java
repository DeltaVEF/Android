package fhict.vef;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;

import fhict.vef.ui.MarkerTimelineView;

public class RoomMarkActivity extends BaseRoomActivity {
    //IT ISN'T EXACTLY A SECOND AS WE HAVE TO COMPENSATE FOR RUNNING THE TIMER LOGIC
    private final int TIMER_TICK_PERIOD = 999;  //IN MILLISECONDS

    private MarkerTimelineView timeline;
    private Handler timelineTimerHandler = new Handler();
    private int tickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_mark);

        final View decorView = getWindow().getDecorView();
        if (decorView != null) {
            final int background = ((ColorDrawable)decorView.getBackground()).getColor(),
                    target = Color.parseColor("#3F51B5");//TODO RECEIVE AND SET PERSONALIZED COLOR
            final ObjectAnimator backgroundAnimator = ObjectAnimator.ofObject(decorView,
                    "backgroundColor", new ArgbEvaluator(), background, target);
            backgroundAnimator.setDuration(1337);
            backgroundAnimator.start();
        }

        timeline = (MarkerTimelineView)findViewById(R.id.room_mark_timeline);
        final FloatingActionButton addMarker =
                (FloatingActionButton)findViewById(R.id.room_mark_add_marker);
        if (timeline != null) {
            timelineTimerHandler.postDelayed(timerTick, TIMER_TICK_PERIOD);
            if (addMarker != null) {
                addMarker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timeline.addMarker();
                    }
                });
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timelineTimerHandler.removeCallbacks(timerTick);
    }

    private final Runnable timerTick = new Runnable() {
        @Override
        public void run() {
            if (timeline != null) {
                timelineTimerHandler.postDelayed(this, TIMER_TICK_PERIOD);
                timeline.setCurrentTimeInSeconds(++tickCount);
            } else {
                timelineTimerHandler.removeCallbacks(this);
            }
        }
    };
}
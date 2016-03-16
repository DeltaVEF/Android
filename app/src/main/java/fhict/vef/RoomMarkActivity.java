package fhict.vef;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import fhict.vef.ui.MarkerTimelineView;

public class RoomMarkActivity extends BaseRoomActivity {
    private MarkerTimelineView timeline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_mark);

        final View decorView = getWindow().getDecorView();
        if (decorView != null) {
            final int background = ((ColorDrawable)decorView.getBackground()).getColor(),
                    target = Color.parseColor("#FF1744");

            final ObjectAnimator backgroundAnimator = ObjectAnimator.ofObject(decorView,
                    "backgroundColor", new ArgbEvaluator(), background, target);
            backgroundAnimator.setDuration(1337);
            backgroundAnimator.start();
        }

        timeline = (MarkerTimelineView)findViewById(R.id.room_mark_timeline);
        if (timeline != null) {
            
        }
    }
}
package fhict.vef;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

public class RoomReviewActivity extends BaseRoomActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_review);

        final VideoView video = (VideoView)findViewById(R.id.room_review_video);
        if (video != null) {

        }
    }
}
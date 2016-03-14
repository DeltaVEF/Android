package fhict.vef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

public class RoomSelectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_selection);

        final AppCompatButton goToButton = (AppCompatButton)findViewById(R.id.room_selection_go);
        if (goToButton != null) {
            goToButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO CHECK IF ROOM EXISTS
                    final boolean doesRoomExist = true;
                    //TODO IF ROOM IS DONE RECORDING, GO TO REVIEW SCREEN
                    final boolean isDoneRecording = false;
                    if (doesRoomExist) {
                        if (isDoneRecording) {
                            startActivity(new Intent(RoomSelectionActivity.this,
                                    RoomReviewActivity.class));
                        } else {
                            startActivity(new Intent(RoomSelectionActivity.this,
                                    RoomMarkActivity.class));
                        }
                        overridePendingTransition(0, 0);
                    } else {
                        Toast.makeText(
                                RoomSelectionActivity.this,
                                R.string.could_not_find_room,
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
            });
        }
    }
}
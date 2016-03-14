package fhict.vef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //CHECK IF USER IS ALREADY LOGGED IN
        //IF SO, CONTINUE TO ROOM SELECTION
        //IF NOT, SHOW THE FONTYS LOGIN SCREEN
        //TODO GET LOGIN STATUS FROM PREFERENCES
        final boolean isLoggedIn = true;
        if (isLoggedIn) {
            startActivity(new Intent(this, RoomSelectionActivity.class));
        } else {
            startActivity(new Intent(this, FontysLoginActivity.class));
        }
        overridePendingTransition(0, 0);
        finish();
    }
}
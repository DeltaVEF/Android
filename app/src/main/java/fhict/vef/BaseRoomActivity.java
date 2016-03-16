package fhict.vef;

import android.support.v7.app.AppCompatActivity;

public class BaseRoomActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        finishFancy();
    }

    protected void finishFancy() {
        finish();
        overridePendingTransition(
                R.anim.trans_in_from_right,
                R.anim.trans_out_to_left
        );
    }
}
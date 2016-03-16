package fhict.vef.ui;

import android.content.Context;

public class UITools {
    public static int convertDpToPx(Context context, int dp) {
        return (int)(context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }
}
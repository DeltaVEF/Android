package fhict.vef.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class WidescreenVideoView extends VideoView {
    public WidescreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = MeasureSpec.getSize(widthMeasureSpec),
                height = (int)(width / 16f * 9f);

        super.onMeasure(widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }
}
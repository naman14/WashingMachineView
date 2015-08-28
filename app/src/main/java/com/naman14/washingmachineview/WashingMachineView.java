package com.naman14.washingmachineview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.naman14.washingmachineview.waterwave.WaterWave;

/**
 * Created by naman on 28/08/15.
 */
public class WashingMachineView extends LinearLayout {

    public WashingMachineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);

        addView(new TopView(context, attrs));
        addView(new MachineView(context, attrs));
        addView(new BottomView(context, attrs));


    }

    public class TopView extends View {

        Paint paint = new Paint();

        public TopView(Context context, AttributeSet attrs) {
            super(context, attrs);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 220);
            params.setMargins(0, 0, 0, 10);
            setLayoutParams(params);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Rect rectBackground = new Rect(0, 0, getWidth(), 220);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            canvas.drawRect(rectBackground, paint);

            Rect rectWhite = new Rect(50, getHeight() / 2 - 10, 180, getHeight() / 2 + 10);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            canvas.drawRect(rectWhite, paint);

            canvas.drawCircle(getWidth() - 300, getHeight() / 2, 25, paint);
            canvas.drawCircle(getWidth() - 200, getHeight() / 2, 25, paint);
            canvas.drawCircle(getWidth() - 100, getHeight() / 2, 20, paint);

        }
    }

    public class BottomView extends View {

        Paint paint = new Paint();

        public BottomView(Context context, AttributeSet attrs) {
            super(context, attrs);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100);
            params.setMargins(0, 15, 0, 0);
            setLayoutParams(params);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Rect rect = new Rect(0, 0, getWidth(), 100);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            canvas.drawRect(rect, paint);

        }
    }

    public class MachineView extends FrameLayout {


        public MachineView(Context context, AttributeSet attrs) {
            super(context, attrs);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 800);
            setLayoutParams(params);

            addView(new WaterWave(context, attrs));
            addView(new OverlayView(context, attrs));
        }


    }
}

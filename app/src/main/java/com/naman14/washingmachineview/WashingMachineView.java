package com.naman14.washingmachineview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.naman14.washingmachineview.waterwave.WaterWave;

/**
 * Created by naman on 28/08/15.
 */
public class WashingMachineView extends LinearLayout {

    private int mTopviewHeight;
    private int mMiddleviewHeight;
    private int mBottomviewHeight;
    private int mMachineColor;

    public WashingMachineView(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MachineView);
        mTopviewHeight=attributes.getDimensionPixelSize(R.styleable.MachineView_top_view_height,getDimensionInPixel(50));
        mBottomviewHeight=attributes.getDimensionPixelSize(R.styleable.MachineView_bottom_view_height,getDimensionInPixel(20));
        mMiddleviewHeight=attributes.getDimensionPixelSize(R.styleable.MachineView_middle_view_height,getDimensionInPixel(240));
        mMachineColor=attributes.getColor(R.styleable.MachineView_machineColor,Color.BLACK);
        attributes.recycle();

        setOrientation(VERTICAL);

        addView(new TopView(context, attrs));
        addView(new MachineView(context, attrs));
        addView(new BottomView(context, attrs));


    }

    public class TopView extends View {

        Paint paint = new Paint();

        public TopView(Context context, AttributeSet attrs) {
            super(context, attrs);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mTopviewHeight);
            params.setMargins(0, 0, 0, 10);
            setLayoutParams(params);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Rect rectBackground = new Rect(0, 0, getWidth(), getHeight());
            paint.setColor(mMachineColor);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            canvas.drawRect(rectBackground, paint);

            Rect rectWhite = new Rect(getDimensionInPixel(20), getHeight() / 2 - getDimensionInPixel(4), getDimensionInPixel(60), getHeight() / 2 + getDimensionInPixel(4));
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            canvas.drawRect(rectWhite, paint);

            canvas.drawCircle(getWidth() - getDimensionInPixel(80), getHeight() / 2, getDimensionInPixel(6), paint);
            canvas.drawCircle(getWidth() - getDimensionInPixel(55), getHeight() / 2, getDimensionInPixel(6), paint);
            canvas.drawCircle(getWidth() - getDimensionInPixel(30), getHeight() / 2, getDimensionInPixel(4), paint);

        }
    }

    public class BottomView extends View {

        Paint paint = new Paint();

        public BottomView(Context context, AttributeSet attrs) {
            super(context, attrs);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mBottomviewHeight);
            params.setMargins(0, 15, 0, 0);
            setLayoutParams(params);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Rect rect = new Rect(0, 0, getWidth(), getHeight());
            paint.setColor(mMachineColor);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            canvas.drawRect(rect, paint);

        }
    }

    public class MachineView extends FrameLayout {


        public MachineView(Context context, AttributeSet attrs) {
            super(context, attrs);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mMiddleviewHeight);
            setLayoutParams(params);

            addView(new WaterWave(context, attrs));
            addView(new OverlayView(context, attrs));
        }


    }

    private int getDimensionInPixel(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}

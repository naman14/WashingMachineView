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

    //linear layout consisting of three views-topview,machineview and bottomview

    private int mTopviewHeight;
    private int mMiddleviewHeight;
    private int mBottomviewHeight;
    private int mMachineColor;

    public WashingMachineView(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MachineView);
        mTopviewHeight = attributes.getDimensionPixelSize(R.styleable.MachineView_top_view_height, getDimensionInPixel(50));
        mBottomviewHeight = attributes.getDimensionPixelSize(R.styleable.MachineView_bottom_view_height, getDimensionInPixel(20));
        mMiddleviewHeight = attributes.getDimensionPixelSize(R.styleable.MachineView_middle_view_height, getDimensionInPixel(240));
        mMachineColor = attributes.getColor(R.styleable.MachineView_machineColor, Color.BLACK);
        attributes.recycle();

        setOrientation(VERTICAL);

        //add the views
        addView(new TopView(context, attrs));
        addView(new MachineView(context, attrs));
        addView(new BottomView(context, attrs));


    }

    //get all dimensions in dp so that views behaves properly on different screen resolutions
    private int getDimensionInPixel(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    public class TopView extends View {

        Paint paint;
        Rect rectBackground;
        Rect rectWhite;

        public TopView(Context context, AttributeSet attrs) {
            super(context, attrs);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mTopviewHeight);
            params.setMargins(0, 0, 0, 10);
            setLayoutParams(params);

            paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);

            rectBackground = new Rect();
            rectWhite = new Rect();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            //background of topview
            rectBackground.set(0, 0, getWidth(), getHeight());
            paint.setColor(mMachineColor);
            canvas.drawRect(rectBackground, paint);

            //the rectangular strip on topview
            rectWhite.set(getDimensionInPixel(20), getHeight() / 2 - getDimensionInPixel(4), getDimensionInPixel(60), getHeight() / 2 + getDimensionInPixel(4));
            paint.setColor(Color.WHITE);
            canvas.drawRect(rectWhite, paint);

            //three dots at the end of topview
            canvas.drawCircle(getWidth() - getDimensionInPixel(80), getHeight() / 2, getDimensionInPixel(6), paint);
            canvas.drawCircle(getWidth() - getDimensionInPixel(55), getHeight() / 2, getDimensionInPixel(6), paint);
            canvas.drawCircle(getWidth() - getDimensionInPixel(30), getHeight() / 2, getDimensionInPixel(4), paint);

        }
    }

    //machineview(middle view) extends FrameLayout and has two views-WaterWave and Overlay.
    //Overlay consists of a transparent hole from which water waves are visible

    public class BottomView extends View {

        Paint paint;
        Rect rect;

        public BottomView(Context context, AttributeSet attrs) {
            super(context, attrs);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mBottomviewHeight);
            params.setMargins(0, 15, 0, 0);
            setLayoutParams(params);

            paint = new Paint();
            paint.setColor(mMachineColor);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);

            rect = new Rect();

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            rect.set(0, 0, getWidth(), getHeight());
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
}

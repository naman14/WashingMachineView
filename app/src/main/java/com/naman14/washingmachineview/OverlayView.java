package com.naman14.washingmachineview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by naman on 28/08/15.
 */
public class OverlayView extends View {

    Bitmap bitmapx;
    private Canvas temp;
    private Paint paint;
    private Paint p = new Paint();
    private Paint transparentPaint;

    private int holeradius;
    private int mMachineColor;

    public OverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);

        final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MachineView);
        holeradius=attributes.getDimensionPixelSize(R.styleable.MachineView_holeRadius,getDimensionInPixel(60));
        mMachineColor=attributes.getColor(R.styleable.MachineView_machineColor,Color.BLACK);
        attributes.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        bitmapx = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        temp = new Canvas(bitmapx);
        paint = new Paint();
        paint.setColor(mMachineColor);
        transparentPaint = new Paint();
        transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
        transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        temp.drawRect(0, 0, getWidth(), getHeight(), paint);
        temp.drawCircle(getWidth() / 2, getHeight() / 2, holeradius, transparentPaint);
        canvas.drawBitmap(bitmapx, 0, 0, p);

        paint.setColor(Color.WHITE);
        canvas.drawCircle(70, 70, 15, paint);

        Paint ringPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setColor(Color.WHITE);
        ringPaint.setStrokeWidth(getDimensionInPixel(10));
        float radius = holeradius+getDimensionInPixel(15);
        canvas.drawCircle((float) 0.5 * getWidth(), (float) 0.5 * getHeight(), radius, ringPaint);

    }

    private int getDimensionInPixel(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}

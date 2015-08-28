package com.naman14.washingmachineview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
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

    public OverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 800);
        setLayoutParams(params);


        bitmapx = Bitmap.createBitmap(1080, 1920, Bitmap.Config.ARGB_8888);
        temp = new Canvas(bitmapx);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        transparentPaint = new Paint();
        transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
        transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        temp.drawRect(0, 0, temp.getWidth(), temp.getHeight(), paint);
        temp.drawCircle(getWidth() / 2, getHeight() / 2, 250, transparentPaint);
        canvas.drawBitmap(bitmapx, 0, 0, p);

        paint.setColor(Color.WHITE);
        canvas.drawCircle(70, 70, 15, paint);

        Paint ringPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setColor(Color.WHITE);
        ringPaint.setStrokeWidth(45);
        float radius = 320;
        canvas.drawCircle((float) 0.5 * getWidth(), (float) 0.5 * getHeight(), radius, ringPaint);

    }

}

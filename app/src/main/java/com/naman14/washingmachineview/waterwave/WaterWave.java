package com.naman14.washingmachineview.waterwave;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.naman14.washingmachineview.R;


public class WaterWave extends LinearLayout {
    protected static final int LARGE = 1;
    protected static final int MIDDLE = 2;
    protected static final int LITTLE = 3;

    private int mAboveWaveColor;
    private int mBlowWaveColor;
    private int mProgress;
    private int mWaveHeight;
    private int mWaveMultiple;
    private int mWaveHz;

    private int mWaveToTop;

    private Wave mWave;
    private SolidBelowWave mSolidBelowWave;

    private final int DEFAULT_ABOVE_WAVE_COLOR = Color.WHITE;
    private final int DEFAULT_BLOW_WAVE_COLOR = Color.WHITE;
    private final int DEFAULT_PROGRESS = 80;


    public WaterWave(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setLayoutParams(params);

        final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.WaterWave);
        mAboveWaveColor = attributes.getColor(R.styleable.WaterWave_above_wave_color, DEFAULT_ABOVE_WAVE_COLOR);
        mBlowWaveColor = attributes.getColor(R.styleable.WaterWave_blow_wave_color, DEFAULT_BLOW_WAVE_COLOR);
        mProgress = attributes.getInt(R.styleable.WaterWave_progress, DEFAULT_PROGRESS);
        mWaveHeight = attributes.getInt(R.styleable.WaterWave_wave_height, MIDDLE);
        mWaveMultiple = attributes.getInt(R.styleable.WaterWave_wave_length, LARGE);
        mWaveHz = attributes.getInt(R.styleable.WaterWave_wave_hz, MIDDLE);
        attributes.recycle();

        mWave = new Wave(context, null);
        mWave.initializeWaveSize(mWaveMultiple, mWaveHeight, mWaveHz);
        mWave.setAboveWaveColor(mAboveWaveColor);
        mWave.setBlowWaveColor(mBlowWaveColor);
        mWave.initializePainters();

        mSolidBelowWave = new SolidBelowWave(context, null);
        mSolidBelowWave.setAboveWavePaint(mWave.getAboveWavePaint());
        mSolidBelowWave.setBlowWavePaint(mWave.getBlowWavePaint());

        addView(mWave);
        addView(mSolidBelowWave);

        setProgress(mProgress);

    }

    public void setProgress(int progress) {
        this.mProgress = progress > 100 ? 100 : progress;
        computeWaveToTop();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            computeWaveToTop();
        }
    }

    private void computeWaveToTop() {
        mWaveToTop = (int) (getHeight() * (1f - mProgress / 100f));
        ViewGroup.LayoutParams params = mWave.getLayoutParams();
        if (params != null) {
            ((LayoutParams) params).topMargin = mWaveToTop;
        }
        mWave.setLayoutParams(params);
    }

    @Override
    public Parcelable onSaveInstanceState() {

        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.progress = mProgress;
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setProgress(ss.progress);
    }

    private static class SavedState extends BaseSavedState {
        int progress;


        SavedState(Parcelable superState) {
            super(superState);
        }


        private SavedState(Parcel in) {
            super(in);
            progress = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(progress);
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }




}

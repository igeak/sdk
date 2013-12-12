package com.igeak.dial.kit;

import android.os.Handler;
import android.os.SystemClock;
import android.text.format.Time;

public class DialTicker {

    private Time mCalendar;
    private TimeChangeListener mTimeChangeListener;
    public DialTicker(TimeChangeListener listener){
        mCalendar = new Time();
        mTimeChangeListener = listener;
    }
    public void start(){
        mTicker.run();
    }
    public void stop(){
        mHandler.removeCallbacks(mTicker);
    }
    private final Runnable mTicker = new Runnable() {
        public void run() {
            onTimeChanged();
            long now = SystemClock.uptimeMillis();
            long next = now + (1000 - now % 1000);
            mHandler.postAtTime(mTicker, next);
        }
    };
    private void onTimeChanged() {
        mCalendar.setToNow();

        int hour = mCalendar.hour;
        int minute = mCalendar.minute;
        int second = mCalendar.second;
        // int second = 0;

        float resSeconds = second % 60.0f;
        float resMinutes = minute + resSeconds / 60.0f;
        float resHour = hour + resMinutes / 60.0f;
        
        if(mTimeChangeListener != null){
            mTimeChangeListener.onChange(resHour, resMinutes, resSeconds);
        }
    }
    private final Handler mHandler = new Handler();
    
    public interface TimeChangeListener{
        void onChange(float hour, float minute, float second);
    }
}

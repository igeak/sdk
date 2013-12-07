package com.plugintest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class ClockPlugin {
    
    public View createClockView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main, null);
        return view;
    }

}

package com.plugintest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.igeak.dial.kit.DialKit;
import com.igeak.dial.kit.DialTicker;
import com.igeak.dial.kit.DialTicker.TimeChangeListener;

public class ClockPlugin {
    
    DialTicker mDialTicker;
    TextView hour1 ;
    TextView second1 ;
    TextView minute1 ;
    
    public View createClockView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main, null);
        TextView aqiView = (TextView) view.findViewById(R.id.aqi);
        TextView weatherView = (TextView) view.findViewById(R.id.weather);
        TextView weatherDegreeView = (TextView) view.findViewById(R.id.weather_degree);
        TextView weatherCityView = (TextView) view.findViewById(R.id.weather_city);
        TextView windLevelView = (TextView) view.findViewById(R.id.wind_level);
        TextView windDirectionView = (TextView) view.findViewById(R.id.wind_direction);
        
        // 
        int aqi = DialKit.getAqi(context);
        aqiView.setText(Integer.toString(aqi));
        
        weatherView.setText(DialKit.getWeather(context));
        weatherDegreeView.setText(DialKit.getWeatherDegree(context));
        
        weatherCityView.setText(DialKit.getWeatherCity(context));
        
        windLevelView.setText(DialKit.getWindLevel(context));
        windDirectionView.setText(DialKit.getWindDirection(context));
        //weatherCityView.setText("WeatherCity");
        ///
        
        hour1 = (TextView) view.findViewById(R.id.hour);
        second1 = (TextView) view.findViewById(R.id.second);
        minute1 = (TextView) view.findViewById(R.id.minute);
        mDialTicker = new DialTicker(new TimeChangeListener(){

            @Override
            public void onChange(float hour, float minute, float second) {
               //
                hour1.setText(Float.toString(hour));
                minute1.setText(Float.toString(minute));
                second1.setText(Float.toString(second));
            }
            
        });
        mDialTicker.start();
        return view;
    }

}

package com.plugintest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.igeak.dial.kit.DialKit;
import com.igeak.dial.kit.DialKit.AQIChangeObserver;
import com.igeak.dial.kit.DialKit.WeatherChangeObserver;
import com.igeak.dial.kit.DialKit.WeatherDegreeChangeObserver;

public class ClockPlugin {
    
    TextView hour1 ;
    TextView second1 ;
    TextView minute1 ;
    TextView aqiView;
    TextView weatherView;
    TextView weatherDegreeView ;
    Context mContext;
    public View createClockView(Context context) {
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main, null);
        aqiView = (TextView) view.findViewById(R.id.aqi);
        weatherView = (TextView) view.findViewById(R.id.weather);
        weatherDegreeView = (TextView) view.findViewById(R.id.weather_degree);
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
        
	// listen for aqi change 
        DialKit.registerAQIChangeObserver(context , 
                new AQIChangeObserver(context){

                    @Override
                    public void onNewData(int newAQI) {
                        aqiView.setText(Integer.toString(newAQI));
                    }
            
        });
        
        DialKit.registerWeatherChangeObserver(context , 
                new WeatherChangeObserver(context){

                    @Override
                    public void onNewData(String weather) {
                        weatherView.setText(weather);
                       
                    }
            
        });
        
        DialKit.registerWeatherDegreeChangeObserver(context , 
                new WeatherDegreeChangeObserver(context){

                    @Override
                    public void onNewData(String newDegree) {
                        weatherDegreeView.setText(newDegree);
                    }
            
        });
        return view;
    }

}

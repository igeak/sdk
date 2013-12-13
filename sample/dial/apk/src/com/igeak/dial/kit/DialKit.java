package com.igeak.dial.kit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract.Contacts;

public class DialKit {

    public static int getAqi(final Context context) {
        int aqi = -1;
        Cursor cursor = null;
        try {
            Uri uri = Uri.parse(
                    "content://com.igeak.clockkit.provider/aqi");
            cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                aqi = cursor.getInt(cursor.getColumnIndex("aqi"));
            }
        } catch (Exception e) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return aqi;
    }

    public static String getWeatherCity(final Context context) {
        String  weather = "";
        Cursor cursor = null;
        try {
            Uri uri = Uri.parse(
                    "content://com.igeak.clockkit.provider/weather_city");
            cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                weather = cursor.getString(cursor.getColumnIndex("weather_city"));
            }
        } catch (Exception e) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return weather;
    }
    
    public static String getWeather(final Context context) {
        String  weather = "";
        Cursor cursor = null;
        try {
            Uri uri = Uri.parse(
                    "content://com.igeak.clockkit.provider/weather");
            cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                weather = cursor.getString(cursor.getColumnIndex("weather"));
            }
        } catch (Exception e) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return weather;
    }
    
    public static String getWeatherDegree(final Context context) {
        String  weather = "";
        Cursor cursor = null;
        try {
            Uri uri = Uri.parse(
                    "content://com.igeak.clockkit.provider/weather_degree");
            cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                weather = cursor.getString(cursor.getColumnIndex("weather_degree"));
            }
        } catch (Exception e) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return weather;
    }
    public static String getWindLevel(final Context context) {
        String  weather = "";
        Cursor cursor = null;
        try {
            Uri uri = Uri.parse(
                    "content://com.igeak.clockkit.provider/wind_level");
            cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                weather = cursor.getString(cursor.getColumnIndex("wind_level"));
            }
        } catch (Exception e) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return weather;
    }
    public static String getWindDirection(final Context context) {
        String  weather = "";
        Cursor cursor = null;
        try {
            Uri uri = Uri.parse(
                    "content://com.igeak.clockkit.provider/wind_direction");
            cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                weather = cursor.getString(cursor.getColumnIndex("wind_direction"));
            }
        } catch (Exception e) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return weather;
    }
    public static ContentObserver registerAQIChangeObserver(
            final Context context,
            AQIChangeObserver observer){
        if(observer == null) {
            return null;
        }
        context.getContentResolver().registerContentObserver(
                Uri.parse("content://com.igeak.clockkit.provider/aqi"), 
                false, observer);
        return observer;
    }
    public static ContentObserver registerWeatherChangeObserver(
            final Context context,
            WeatherChangeObserver observer){
        if(observer == null) {
            return null;
        }
        context.getContentResolver().registerContentObserver(
                Uri.parse("content://com.igeak.clockkit.provider/weather"), 
                false, observer);
        return observer;
    }
    public static ContentObserver registerWeatherDegreeChangeObserver(
            final Context context,
            WeatherDegreeChangeObserver observer){
        if(observer == null) {
            return null;
        }
        context.getContentResolver().registerContentObserver(
                Uri.parse("content://com.igeak.clockkit.provider/weather_degree"), 
                false, observer);
        return observer;
    }
    
    public static abstract class AQIChangeObserver extends ContentObserver{
        private Context mContext;
        public AQIChangeObserver(Context context) {
            super(new Handler());
            mContext = context;
       }
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            // !!!
            int newAQI = getAqi(mContext);
            onNewData(newAQI);
        }
        public abstract void onNewData(int newAQI);
    }
    public static abstract class WeatherChangeObserver extends ContentObserver{
        private Context mContext;
        public WeatherChangeObserver(Context context) {
            super(new Handler());
            mContext = context;
       }
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            String newWeather = getWeather(mContext);
            onNewData(newWeather);
        }
        public abstract void onNewData(String newWeather);
    }
    public static abstract class WeatherDegreeChangeObserver extends ContentObserver{
        private Context mContext;
        public WeatherDegreeChangeObserver(Context context) {
            super(new Handler());
            mContext = context;
       }
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            String newWeatherDegree = getWeatherDegree(mContext);
            onNewData(newWeatherDegree);
        }
        public abstract void onNewData(String newWeatherDegree);
    }
}

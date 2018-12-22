package com.example.a19093.project_endofterm;

import android.app.Application;
import android.util.Log;

import com.example.a19093.project_endofterm.Request.HourlyWeatherForecast;
import com.example.a19093.project_endofterm.Request.WeeklyWeatherForecast;

import java.util.List;

public class VariableApp extends Application {
    private static VariableApp instance = null;
    public static VariableApp getInstance(){
        return instance;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }
}

package com.example.a19093.project_endofterm;

import android.app.Application;
import android.util.Log;

import com.example.a19093.project_endofterm.Request.HourlyWeatherForecast;
import com.example.a19093.project_endofterm.Request.WeeklyWeatherForecast;

import java.util.List;

public class VariableApp extends Application {
    private static VariableApp instance = null;
    private WeeklyWeatherForecast weeklyWeatherForecast;
    private HourlyWeatherForecast hourlyWeatherForecast;

    public static VariableApp getInstance(){
        return instance;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        CityOperator cityOperator = new CityOperator(this);
        weeklyWeatherForecast = new WeeklyWeatherForecast();
        hourlyWeatherForecast = new HourlyWeatherForecast();
        instance = this;
    }


    public WeeklyWeatherForecast getWeeklyWeatherForecast() {
        return weeklyWeatherForecast;
    }

    public void setWeeklyWeatherForecast(WeeklyWeatherForecast weeklyWeatherForecast) {
        this.weeklyWeatherForecast = weeklyWeatherForecast;
    }
}

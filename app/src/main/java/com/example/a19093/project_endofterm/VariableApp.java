package com.example.a19093.project_endofterm;

import android.app.Application;

import com.example.a19093.project_endofterm.Request.HourlyWeatherForecast;
import com.example.a19093.project_endofterm.Request.WeeklyWeatherForecast;

public class VariableApp extends Application {
    private static VariableApp instance = null;
    private WeeklyWeatherForecast weeklyWeatherForecast;
    private HourlyWeatherForecast hourlyWeatherForecast;
    private City city;

    public static VariableApp getInstance(){
        return instance;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        city = new City();
        weeklyWeatherForecast = new WeeklyWeatherForecast();
        hourlyWeatherForecast = new HourlyWeatherForecast();
        instance = this;
    }

    public City getCity(){
        return city;
    }

    public WeeklyWeatherForecast getWeeklyWeatherForecast() {
        return weeklyWeatherForecast;
    }

    public void setWeeklyWeatherForecast(WeeklyWeatherForecast weeklyWeatherForecast) {
        this.weeklyWeatherForecast = weeklyWeatherForecast;
    }
}

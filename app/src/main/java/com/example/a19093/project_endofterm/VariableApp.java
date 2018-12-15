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
    private City selectCity;
    private List<City> listCity;

    public static VariableApp getInstance(){
        return instance;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        CityOperator cityOperator = new CityOperator(this);
        selectCity = cityOperator.getIsSelectCity();
        if(selectCity == null){
            City city = new City("泾川 平凉 甘肃");
            city.setIsSelect("是");
            selectCity = city;
        }
        weeklyWeatherForecast = new WeeklyWeatherForecast();
        hourlyWeatherForecast = new HourlyWeatherForecast();
        listCity = cityOperator.getAllCity();
        for(int i = 0; i < listCity.size(); i ++){
            Log.e("begin", listCity.get(i).toString1());
        }
        instance = this;
    }

    public String getSelectName(){
        CityOperator cityOperator = new CityOperator(this);
        return cityOperator.getIsSelectName();
    }

    public City getSelectCity(){
        return this.selectCity;
    }

    public List<City> getListCity(){
        CityOperator cityOperator = new CityOperator(this);
        return cityOperator.getAllCity();
    }

    public WeeklyWeatherForecast getWeeklyWeatherForecast() {
        return weeklyWeatherForecast;
    }

    public void setWeeklyWeatherForecast(WeeklyWeatherForecast weeklyWeatherForecast) {
        this.weeklyWeatherForecast = weeklyWeatherForecast;
    }
}

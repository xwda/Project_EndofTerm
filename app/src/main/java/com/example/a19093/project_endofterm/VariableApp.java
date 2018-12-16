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
        else{
            selectCity.setIsSelect("是");
            Log.e("begin1",selectCity.getName() + "" + selectCity.getIsSelect());
        }
        weeklyWeatherForecast = new WeeklyWeatherForecast();
        hourlyWeatherForecast = new HourlyWeatherForecast();
        listCity = cityOperator.getAllCity();
        instance = this;
    }

    public String getSelectName(){
        //CityOperator cityOperator = new CityOperator(this);
        return this.selectCity.getName();
    }

    public void setSelectCity(City city){
        this.selectCity.setName(city.getName());
        this.selectCity.setIsSelect(city.getIsSelect());
    }

    public void setListCity(){
        CityOperator cityOperator = new CityOperator(this);
        this.listCity =  cityOperator.getAllCity();
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

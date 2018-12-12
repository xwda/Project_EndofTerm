package com.example.a19093.project_endofterm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.a19093.project_endofterm.Request.WeeklyWeatherForecast;
import com.google.gson.Gson;

public class GetDataService extends Service {
    private static GetDataService instance;
    private String string_weather_forcast;
    private WeeklyWeatherForecast weeklyWeatherForecast;

    private GetDataService(){}

    public static GetDataService getIntence(){
        if(instance == null){
            instance = new GetDataService();
        }
        return instance;
    }

    public WeeklyWeatherForecast getWeeklyWeatherForecast(){
        return this.weeklyWeatherForecast;
    }

    /**
     * 绑定服务时才会调用
     * 必须要实现的方法
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
     * 如果服务已在运行，则不会调用此方法。该方法只被调用一次
     */
    @Override
    public void onCreate() {
        new Thread() {
            public void run() {
                try {
                    string_weather_forcast = GetData.getJson("https://free-api.heweather.com/s6/weather/forecast?location=beijing&key=1ad86ef7444245909304101fd5db5407");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        }.start();
        Gson gson = new Gson();
        weeklyWeatherForecast = gson.fromJson(string_weather_forcast,WeeklyWeatherForecast.class);
        System.out.println("onCreate invoke");
        super.onCreate();
    }

    /**
     * 每次通过startService()方法启动Service时都会被回调。
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand invoke");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 服务销毁时的回调
     */
    @Override
    public void onDestroy() {
        System.out.println("onDestroy invoke");
        super.onDestroy();
    }
}
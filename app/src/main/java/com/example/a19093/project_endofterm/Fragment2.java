package com.example.a19093.project_endofterm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.a19093.project_endofterm.Request.HourlyWeatherForecast;
import com.example.a19093.project_endofterm.Request.LifestyleForecast;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Fragment2 extends Fragment {

    private TextView time0,time1,time2,time3,time4,time5,time6,time7;
    private TextView tmp0,tmp1,tmp2,tmp3,tmp4,tmp5,tmp6,tmp7;
    private TextView cond_txt0,cond_txt1,cond_txt2,cond_txt3,cond_txt4,cond_txt5,cond_txt6,cond_txt7;
    private TextView wind_dir0,wind_dir1,wind_dir2,wind_dir3,wind_dir4,wind_dir5,wind_dir6,wind_dir7;
    private TextView wind_sc0,wind_sc1,wind_sc2,wind_sc3,wind_sc4,wind_sc5,wind_sc6,wind_sc7;
    private TextView comf_type,comf_txt;
    private TextView drsg_type,drsg_txt;
    private TextView flu_type,flu_txt;
    private TextView sport_type,sport_txt;
    private TextView trav_type,trav_txt;
    private TextView uv_type,uv_txt;
    private TextView cw_type,cw_txt;
    private TextView air_type,air_txt;
    private TextView tv_update;

    private HourlyWeatherForecast hourlyWeatherForecast;
    private LifestyleForecast lifestyleForecast;
    private volatile String string_hourly_weather_forcast;
    private volatile String string_Lifestyle_forcast;
    String string_city;
    View view;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0x001:
                    parseData();
                    break;
                default:
                    break;
            }
        };
    };


    public Fragment2() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, container, false);
        bindView();
        getData();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            getData();
        }
    }

    void bindView(){
        tv_update = view.findViewById(R.id.tv_update);
        time0 = view.findViewById(R.id.time0);
        time1 = view.findViewById(R.id.time1);
        time2 = view.findViewById(R.id.time2);
        time3 = view.findViewById(R.id.time3);
        time4 = view.findViewById(R.id.time4);
        time5 = view.findViewById(R.id.time5);
        time6 = view.findViewById(R.id.time6);
        time7 = view.findViewById(R.id.time7);

        tmp0 = view.findViewById(R.id.tmp0);
        tmp1 = view.findViewById(R.id.tmp1);
        tmp2 = view.findViewById(R.id.tmp2);
        tmp3 = view.findViewById(R.id.tmp3);
        tmp4 = view.findViewById(R.id.tmp4);
        tmp5 = view.findViewById(R.id.tmp5);
        tmp6 = view.findViewById(R.id.tmp6);
        tmp7 = view.findViewById(R.id.tmp7);

        cond_txt0 = view.findViewById(R.id.cond_txt0);
        cond_txt1 = view.findViewById(R.id.cond_txt1);
        cond_txt2 = view.findViewById(R.id.cond_txt2);
        cond_txt3 = view.findViewById(R.id.cond_txt3);
        cond_txt4 = view.findViewById(R.id.cond_txt4);
        cond_txt5 = view.findViewById(R.id.cond_txt5);
        cond_txt6 = view.findViewById(R.id.cond_txt6);
        cond_txt7 = view.findViewById(R.id.cond_txt7);

        wind_dir0 = view.findViewById(R.id.wind_dir0);
        wind_dir1 = view.findViewById(R.id.wind_dir1);
        wind_dir2 = view.findViewById(R.id.wind_dir2);
        wind_dir3 = view.findViewById(R.id.wind_dir3);
        wind_dir4 = view.findViewById(R.id.wind_dir4);
        wind_dir5 = view.findViewById(R.id.wind_dir5);
        wind_dir6 = view.findViewById(R.id.wind_dir6);
        wind_dir7 = view.findViewById(R.id.wind_dir7);

        wind_sc0 = view.findViewById(R.id.wind_sc0);
        wind_sc1 = view.findViewById(R.id.wind_sc1);
        wind_sc2 = view.findViewById(R.id.wind_sc2);
        wind_sc3 = view.findViewById(R.id.wind_sc3);
        wind_sc4 = view.findViewById(R.id.wind_sc4);
        wind_sc5 = view.findViewById(R.id.wind_sc5);
        wind_sc6 = view.findViewById(R.id.wind_sc6);
        wind_sc7 = view.findViewById(R.id.wind_sc7);

        comf_type = view.findViewById(R.id.comf_type);
        comf_txt = view.findViewById(R.id.comf_txt);
        drsg_type = view.findViewById(R.id.drsg_type);
        drsg_txt = view.findViewById(R.id.drsg_txt);
        flu_type = view.findViewById(R.id.flu_type);
        flu_txt = view.findViewById(R.id.flu_txt);
        sport_type = view.findViewById(R.id.sport_type);
        sport_txt = view.findViewById(R.id.sport_txt);
        trav_type = view.findViewById(R.id.trav_type);
        trav_txt = view.findViewById(R.id.trav_txt);
        uv_type = view.findViewById(R.id.uv_type);
        uv_txt = view.findViewById(R.id.uv_txt);
        cw_type = view.findViewById(R.id.cw_type);
        cw_txt = view.findViewById(R.id.cw_txt);
        air_type = view.findViewById(R.id.air_type);
        air_txt = view.findViewById(R.id.air_txt);
    }

    void draw(){
        if(hourlyWeatherForecast == null || lifestyleForecast == null) return ;
        try{
            SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            SimpleDateFormat sd2 = new SimpleDateFormat("HH:mm");
            Date upd = sd1.parse(hourlyWeatherForecast.getHeWeather6().get(0).getUpdate().getLoc());
            tv_update.setText(sd2.format(upd)+" 发布");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String tm;
        String[] s;
        tm = hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(0).getTime();
        s = tm.split(" ");
        time0.setText(s[1]);
        tm = hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(1).getTime();
        s = tm.split(" ");
        time1.setText(s[1]);
        tm = hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(2).getTime();
        s = tm.split(" ");
        time2.setText(s[1]);
        tm = hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(3).getTime();
        s = tm.split(" ");
        time3.setText(s[1]);
        tm = hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(4).getTime();
        s = tm.split(" ");
        time4.setText(s[1]);
        tm = hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(5).getTime();
        s = tm.split(" ");
        time5.setText(s[1]);
        tm = hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(6).getTime();
        s = tm.split(" ");
        time6.setText(s[1]);
        tm = hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(7).getTime();
        s = tm.split(" ");
        time7.setText(s[1]);

        String degree = "℃";

        tmp0.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(0).getTmp()+degree);
        tmp1.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(1).getTmp()+degree);
        tmp2.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(2).getTmp()+degree);
        tmp3.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(3).getTmp()+degree);
        tmp4.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(4).getTmp()+degree);
        tmp5.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(5).getTmp()+degree);
        tmp6.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(6).getTmp()+degree);
        tmp7.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(7).getTmp()+degree);

        cond_txt0.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(0).getCond_txt());
        cond_txt1.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(1).getCond_txt());
        cond_txt2.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(2).getCond_txt());
        cond_txt3.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(3).getCond_txt());
        cond_txt4.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(4).getCond_txt());
        cond_txt5.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(5).getCond_txt());
        cond_txt6.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(6).getCond_txt());
        cond_txt7.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(7).getCond_txt());

        wind_dir0.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(0).getWind_dir());
        wind_dir1.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(1).getWind_dir());
        wind_dir2.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(2).getWind_dir());
        wind_dir3.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(3).getWind_dir());
        wind_dir4.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(4).getWind_dir());
        wind_dir5.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(5).getWind_dir());
        wind_dir6.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(6).getWind_dir());
        wind_dir7.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(7).getWind_dir());

        wind_sc0.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(0).getWind_sc()+"级");
        wind_sc1.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(1).getWind_sc()+"级");
        wind_sc2.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(2).getWind_sc()+"级");
        wind_sc3.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(3).getWind_sc()+"级");
        wind_sc4.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(4).getWind_sc()+"级");
        wind_sc5.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(5).getWind_sc()+"级");
        wind_sc6.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(6).getWind_sc()+"级");
        wind_sc7.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(7).getWind_sc()+"级");

        comf_type.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(0).getBrf());
        comf_txt.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(0).getTxt());
        drsg_type.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(1).getBrf());
        drsg_txt.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(1).getTxt());
        flu_type.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(2).getBrf());
        flu_txt.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(2).getTxt());
        sport_type.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(3).getBrf());
        sport_txt.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(3).getTxt());
        trav_type.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(4).getBrf());
        trav_txt.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(4).getTxt());
        uv_type.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(5).getBrf());
        uv_txt.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(5).getTxt());
        cw_type.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(6).getBrf());
        cw_txt.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(6).getTxt());
        air_type.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(7).getBrf());
        air_txt.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(7).getTxt());
    }

    void getData(){
        new Thread() {
            public void run() {
                try {
                    CityOperator cityOperator = new CityOperator(getContext());
                    string_city = cityOperator.getIsSelectCity().toString2();
                    string_hourly_weather_forcast = GetData.getJson("https://free-api.heweather.com/s6/weather/hourly?location=" + string_city + "&key=2d7b37b322a04de1ab17fca5f2e0f0ea");
                    string_Lifestyle_forcast = GetData.getJson("https://free-api.heweather.com/s6/weather/lifestyle?location=" + string_city + "&key=2d7b37b322a04de1ab17fca5f2e0f0ea");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0x001);
            };
        }.start();
    }

    void parseData(){
        Gson gson = new Gson();
        hourlyWeatherForecast = gson.fromJson(string_hourly_weather_forcast,HourlyWeatherForecast.class);
        lifestyleForecast = gson.fromJson(string_Lifestyle_forcast,LifestyleForecast.class);
        draw();
    }
}

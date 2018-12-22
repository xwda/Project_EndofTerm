package com.example.a19093.project_endofterm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.a19093.project_endofterm.Request.WeeklyWeatherForecast;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class Fragment1 extends Fragment{
    private View view;
    private TextView day0, day1,day2,day3,day4,day5,day6;
    private TextView date0, date1,date2,date3,date4,date5,date6;
    private ImageView img_d0, img_d1,img_d2,img_d3,img_d4,img_d5,img_d6;
    private TextView cond_txt_d0, cond_txt_d1,cond_txt_d2,cond_txt_d3,cond_txt_d4,cond_txt_d5,cond_txt_d6;
    private ImageView img_n0, img_n1,img_n2,img_n3,img_n4,img_n5,img_n6;
    private TextView cond_txt_n0, cond_txt_n1,cond_txt_n2,cond_txt_n3,cond_txt_n4,cond_txt_n5,cond_txt_n6;
    private TextView wind_dir0, wind_dir1,wind_dir2,wind_dir3,wind_dir4,wind_dir5,wind_dir6;
    private TextView tmp_max0, tmp_max1,tmp_max2,tmp_max3,tmp_max4,tmp_max5,tmp_max6;
    private TextView tmp_min0, tmp_min1,tmp_min2,tmp_min3,tmp_min4,tmp_min5,tmp_min6;
    private TextView wind_sc0, wind_sc1,wind_sc2,wind_sc3,wind_sc4,wind_sc5,wind_sc6;
    private WeeklyWeatherForecast weeklyWeatherForecast;
    private volatile String string_weather_forcast;
    private TextView tv_updata;
    private float[] y1;
    private float[] y2;
    private float[] x;
    private List<PointValue> value1;
    private List<PointValue> value2;
    String string_city;

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

    public Fragment1() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);
        bindView();
        getData();
        return view;
    }

    private void bindView(){
        tv_updata = view.findViewById(R.id.tv_update);
        day0 = view.findViewById(R.id.day0);
        day1 = view.findViewById(R.id.day1);
        day2 = view.findViewById(R.id.day2);
        day3 = view.findViewById(R.id.day3);
        day4 = view.findViewById(R.id.day4);
        day5 = view.findViewById(R.id.day5);
        day6 = view.findViewById(R.id.day6);
        date0 = view.findViewById(R.id.date0);
        date1 = view.findViewById(R.id.date1);
        date2 = view.findViewById(R.id.date2);
        date3 = view.findViewById(R.id.date3);
        date4 = view.findViewById(R.id.date4);
        date5 = view.findViewById(R.id.date5);
        date6 = view.findViewById(R.id.date6);
        img_d0 = view.findViewById(R.id.img_d0);
        img_d1 = view.findViewById(R.id.img_d1);
        img_d2 = view.findViewById(R.id.img_d2);
        img_d3 = view.findViewById(R.id.img_d3);
        img_d4 = view.findViewById(R.id.img_d4);
        img_d5 = view.findViewById(R.id.img_d5);
        img_d6 = view.findViewById(R.id.img_d6);
        cond_txt_d0 = view.findViewById(R.id.cond_txt_d0);
        cond_txt_d1 = view.findViewById(R.id.cond_txt_d1);
        cond_txt_d2 = view.findViewById(R.id.cond_txt_d2);
        cond_txt_d3 = view.findViewById(R.id.cond_txt_d3);
        cond_txt_d4 = view.findViewById(R.id.cond_txt_d4);
        cond_txt_d5 = view.findViewById(R.id.cond_txt_d5);
        cond_txt_d6 = view.findViewById(R.id.cond_txt_d6);
        img_n0 = view.findViewById(R.id.img_n0);
        img_n1 = view.findViewById(R.id.img_n1);
        img_n2 = view.findViewById(R.id.img_n2);
        img_n3 = view.findViewById(R.id.img_n3);
        img_n4 = view.findViewById(R.id.img_n4);
        img_n5 = view.findViewById(R.id.img_n5);
        img_n6 = view.findViewById(R.id.img_n6);
        cond_txt_n0 = view.findViewById(R.id.cond_txt_n0);
        cond_txt_n1 = view.findViewById(R.id.cond_txt_n1);
        cond_txt_n2 = view.findViewById(R.id.cond_txt_n2);
        cond_txt_n3 = view.findViewById(R.id.cond_txt_n3);
        cond_txt_n4 = view.findViewById(R.id.cond_txt_n4);
        cond_txt_n5 = view.findViewById(R.id.cond_txt_n5);
        cond_txt_n6 = view.findViewById(R.id.cond_txt_n6);
        wind_dir0 = view.findViewById(R.id.wind_dir0);
        wind_dir1 = view.findViewById(R.id.wind_dir1);
        wind_dir2 = view.findViewById(R.id.wind_dir2);
        wind_dir3 = view.findViewById(R.id.wind_dir3);
        wind_dir4 = view.findViewById(R.id.wind_dir4);
        wind_dir5 = view.findViewById(R.id.wind_dir5);
        wind_dir6 = view.findViewById(R.id.wind_dir6);
        wind_sc0 = view.findViewById(R.id.wind_sc0);
        wind_sc1 = view.findViewById(R.id.wind_sc1);
        wind_sc2 = view.findViewById(R.id.wind_sc2);
        wind_sc3 = view.findViewById(R.id.wind_sc3);
        wind_sc4 = view.findViewById(R.id.wind_sc4);
        wind_sc5 = view.findViewById(R.id.wind_sc5);
        wind_sc6 = view.findViewById(R.id.wind_sc6);
        tmp_max0 = view.findViewById(R.id.tmp_max0);
        tmp_max1 = view.findViewById(R.id.tmp_max1);
        tmp_max2 = view.findViewById(R.id.tmp_max2);
        tmp_max3 = view.findViewById(R.id.tmp_max3);
        tmp_max4 = view.findViewById(R.id.tmp_max4);
        tmp_max5 = view.findViewById(R.id.tmp_max5);
        tmp_max6 = view.findViewById(R.id.tmp_max6);
        tmp_min0 = view.findViewById(R.id.tmp_min0);
        tmp_min1 = view.findViewById(R.id.tmp_min1);
        tmp_min2 = view.findViewById(R.id.tmp_min2);
        tmp_min3 = view.findViewById(R.id.tmp_min3);
        tmp_min4 = view.findViewById(R.id.tmp_min4);
        tmp_min5 = view.findViewById(R.id.tmp_min5);
        tmp_min6 = view.findViewById(R.id.tmp_min6);
    }

    private void draw(){
        try {
            SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            SimpleDateFormat sd2 = new SimpleDateFormat("HH:mm");
            Date upd = sd1.parse(weeklyWeatherForecast.getHeWeather6().get(0).getUpdate().getLoc());
            tv_updata.setText(sd2.format(upd)+" 发布");

            SimpleDateFormat std1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat std2 = new SimpleDateFormat("EEEE");
            SimpleDateFormat std3 = new SimpleDateFormat("MM/dd");
            Date d;
            String string_date0 = weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(0).getDate();
            d = std1.parse(string_date0);
            date0.setText(std3.format(d));
            day0.setText("今天");
            String string_date1 = weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(1).getDate();
            d = std1.parse(string_date1);
            date1.setText(std3.format(d));
            day1.setText("明天");
            String string_date2 = weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(2).getDate();
            d = std1.parse(string_date2);
            date2.setText(std3.format(d));
            day2.setText("后天");
            String string_date3 = weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(3).getDate();
            d = std1.parse(string_date3);
            date3.setText(std3.format(d));
            day3.setText(std2.format(d));
            String string_date4 = weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(4).getDate();
            d = std1.parse(string_date4);
            date4.setText(std3.format(d));
            day4.setText(std2.format(d));
            String string_date5 = weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(5).getDate();
            d = std1.parse(string_date5);
            date5.setText(std3.format(d));
            day5.setText(std2.format(d));
            String string_date6 = weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(6).getDate();
            d = std1.parse(string_date6);
            date6.setText(std3.format(d));
            day6.setText(std2.format(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        setImgView(img_d0,0,0);
        setImgView(img_d1,1,0);
        setImgView(img_d2,2,0);
        setImgView(img_d3,3,0);
        setImgView(img_d4,4,0);
        setImgView(img_d5,5,0);
        setImgView(img_d6,6,0);


        setImgView(img_n0,0,1);
        setImgView(img_n1,1,1);
        setImgView(img_n2,2,1);
        setImgView(img_n3,3,1);
        setImgView(img_n4,4,1);
        setImgView(img_n5,5,1);
        setImgView(img_n6,6,1);


        cond_txt_d0.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(0).getCond_txt_d());
        cond_txt_d1.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(1).getCond_txt_d());
        cond_txt_d2.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(2).getCond_txt_d());
        cond_txt_d3.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(3).getCond_txt_d());
        cond_txt_d4.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(4).getCond_txt_d());
        cond_txt_d5.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(5).getCond_txt_d());
        cond_txt_d6.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(6).getCond_txt_d());

        cond_txt_n0.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(0).getCond_txt_n());
        cond_txt_n1.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(1).getCond_txt_n());
        cond_txt_n2.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(2).getCond_txt_n());
        cond_txt_n3.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(3).getCond_txt_n());
        cond_txt_n4.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(4).getCond_txt_n());
        cond_txt_n5.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(5).getCond_txt_n());
        cond_txt_n6.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(6).getCond_txt_n());

        wind_dir0.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(0).getWind_dir());
        wind_dir1.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(1).getWind_dir());
        wind_dir2.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(2).getWind_dir());
        wind_dir3.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(3).getWind_dir());
        wind_dir4.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(4).getWind_dir());
        wind_dir5.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(5).getWind_dir());
        wind_dir6.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(6).getWind_dir());

        wind_sc0.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(0).getWind_sc()+"级");
        wind_sc1.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(1).getWind_sc()+"级");
        wind_sc2.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(2).getWind_sc()+"级");
        wind_sc3.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(3).getWind_sc()+"级");
        wind_sc4.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(4).getWind_sc()+"级");
        wind_sc5.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(5).getWind_sc()+"级");
        wind_sc6.setText(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(6).getWind_sc()+"级");
        y1 = new float[7];
        y2 = new float[7];
        x = new float [7];
        for(int i = 0; i < 7; i ++) x[i] = (float)i;
        y1[0] = Integer.parseInt(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(0).getTmp_max());
        y1[1] = Integer.parseInt(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(1).getTmp_max());
        y1[2] = Integer.parseInt(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(2).getTmp_max());
        y1[3] = Integer.parseInt(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(3).getTmp_max());
        y1[4] = Integer.parseInt(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(4).getTmp_max());
        y1[5] = Integer.parseInt(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(5).getTmp_max());
        y1[6] = Integer.parseInt(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(6).getTmp_max());

        y2[0] = Integer.parseInt(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(0).getTmp_min());
        y2[1] = Integer.parseInt(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(1).getTmp_min());
        y2[2] = Integer.parseInt(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(2).getTmp_min());
        y2[3] = Integer.parseInt(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(3).getTmp_min());
        y2[4] = Integer.parseInt(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(4).getTmp_min());
        y2[5] = Integer.parseInt(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(5).getTmp_min());
        y2[6] = Integer.parseInt(weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(6).getTmp_min());

        String degree = "℃";
        tmp_max0.setText((int)y1[0]+degree);
        tmp_max1.setText((int)y1[1]+degree);
        tmp_max2.setText((int)y1[2]+degree);
        tmp_max3.setText((int)y1[3]+degree);
        tmp_max4.setText((int)y1[4]+degree);
        tmp_max5.setText((int)y1[5]+degree);
        tmp_max6.setText((int)y1[6]+degree);

        tmp_min0.setText((int)y2[0]+degree);
        tmp_min1.setText((int)y2[1]+degree);
        tmp_min2.setText((int)y2[2]+degree);
        tmp_min3.setText((int)y2[3]+degree);
        tmp_min4.setText((int)y2[4]+degree);
        tmp_min5.setText((int)y2[5]+degree);
        tmp_min6.setText((int)y2[6]+degree);

        value1 = new ArrayList<>();
        value2 = new ArrayList<>();
        for(int i = 0; i < 7; i ++){
            value1.add(new PointValue(x[i],y1[i]));
            value2.add(new PointValue(x[i],y2[i]));
        }
        List<Line> lines = new ArrayList<>();
        LineChartView chart = view.findViewById(R.id.chart);
        Line line1 = new Line(value1).setColor(Color.YELLOW);
        line1.setHasLines(true);
        line1.setHasPoints(true);
        line1.setHasLabels(false);
        Line line2 = new Line(value2).setColor(Color.BLUE);
        line2.setHasLines(true);
        line2.setHasPoints(true);
        line2.setHasLabels(false);
        line1.setPointColor(Color.WHITE);
        line2.setPointColor(Color.WHITE);
        line1.setPointRadius(4);
        line2.setPointRadius(4);
        lines.add(line1);
        lines.add(line2);
        line1.setStrokeWidth(2);
        line2.setStrokeWidth(2);
        line1.setCubic(false);
        line2.setCubic(false);
        LineChartData data = new LineChartData(lines);
        data.setValueLabelBackgroundEnabled(false);
        data.setValueLabelsTextColor(R.color.text_yellow);
        data.setBaseValue(Float.NEGATIVE_INFINITY);
        data.setValueLabelsTextColor(Color.WHITE);
        data.setValueLabelTextSize(15);
        chart.setLineChartData(data);
        chart.setInteractive(false);
    }

    private void getData(){
        new Thread() {
            public void run() {
                try {
                    CityOperator cityOperator = new CityOperator(getContext());
                    string_city = cityOperator.getIsSelectCity().toString2();
                    string_weather_forcast = GetData.getJson("https://free-api.heweather.com/s6/weather/forecast?location="
                            + string_city
                            + "&key=2d7b37b322a04de1ab17fca5f2e0f0ea");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0x001);
            }
        }.start();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            getData();
        }
    }

    private void parseData(){
        Gson gson = new Gson();
        weeklyWeatherForecast = gson.fromJson(string_weather_forcast,WeeklyWeatherForecast.class);
        if(weeklyWeatherForecast != null)
            draw();
    }
    public int getResource(String imageName){
        Context ctx=Objects.requireNonNull(getActivity()).getBaseContext();
        return getResources().getIdentifier(imageName, "drawable", ctx.getPackageName());
    }
    private void setImgView(ImageView ig,int i, int flag){
        if(flag == 0){
            String img1 =  "x" + weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(i).getCond_code_d();
            ig.setImageResource(getResource(img1));
        }
        else{
            String img1 =  "x" + weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(i).getCond_code_n() + "n";
            String img2 =  "x" + weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(i).getCond_code_n();
            if(getResource(img1) == 0){
                ig.setImageResource(getResource(img2));
            }
            else{
                ig.setImageResource(getResource(img1));
            }
        }

    }
}

package com.example.a19093.project_endofterm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a19093.project_endofterm.Request.WeeklyWeatherForecast;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherForecast extends AppCompatActivity implements View.OnClickListener {
    private TextView day0, day1,day2,day3,day4,day5,day6;
    private TextView date0, date1,date2,date3,date4,date5,date6;
    private ImageView img_d0, img_d1,img_d2,img_d3,img_d4,img_d5,img_d6;
    private TextView cond_txt_d0, cond_txt_d1,cond_txt_d2,cond_txt_d3,cond_txt_d4,cond_txt_d5,cond_txt_d6;
    private ImageView img_n0, img_n1,img_n2,img_n3,img_n4,img_n5,img_n6;
    private TextView cond_txt_n0, cond_txt_n1,cond_txt_n2,cond_txt_n3,cond_txt_n4,cond_txt_n5,cond_txt_n6;
    private TextView wind_dir0, wind_dir1,wind_dir2,wind_dir3,wind_dir4,wind_dir5,wind_dir6;
    private TextView wind_sc0, wind_sc1,wind_sc2,wind_sc3,wind_sc4,wind_sc5,wind_sc6;
    private GetDataService getDataService;
    private WeeklyWeatherForecast weeklyWeatherForecast;
    private String string_weather_forcast;
    private TextView button;
    private TextView textView;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0x001:
                    Gson gson = new Gson();
                    weeklyWeatherForecast = gson.fromJson(string_weather_forcast,WeeklyWeatherForecast.class);
                    draw();
//                    textView.setText(weeklyWeatherForecast.getHeWeather6().get(0).getBasic().getCid() + "\n");
//                    textView.append(weeklyWeatherForecast.getHeWeather6().get(0).getBasic().getLocation() + "\n");
//                    textView.append(weeklyWeatherForecast.getHeWeather6().get(0).getBasic().getCnty() + "\n");
//                    textView.append(weeklyWeatherForecast.getHeWeather6().get(0).getBasic().getLat() + "\n");
//                    textView.append(weeklyWeatherForecast.getHeWeather6().get(0).getBasic().getLon() + "\n");
//                    textView.append(weeklyWeatherForecast.getHeWeather6().get(0).getBasic().getTz() + "\n");
//                    Toast.makeText(WeatherForecast.this, "HTML代码加载完毕", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        bindView();
        getAndroiodScreenProperty();
        new Thread() {
            public void run() {
                try {
                    string_weather_forcast = GetData.getJson("https://free-api.heweather.com/s6/weather/forecast?location=beijing&key=2d7b37b322a04de1ab17fca5f2e0f0ea");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0x001);
            };
        }.start();
    }

    public void getAndroiodScreenProperty() {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)

        Log.d("h_bl", "屏幕宽度（像素）：" + width);
        Log.d("h_bl", "屏幕高度（像素）：" + height);
        Log.d("h_bl", "屏幕密度（0.75 / 1.0 / 1.5）：" + density);
        Log.d("h_bl", "屏幕密度dpi（120 / 160 / 240）：" + densityDpi);
        Log.d("h_bl", "屏幕宽度（dp）：" + screenWidth);
        Log.d("h_bl", "屏幕高度（dp）：" + screenHeight);
    }

    void bindView(){
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        button.setOnClickListener(this);
        day0 = findViewById(R.id.day0);
        day1 = findViewById(R.id.day1);
        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);
        day4 = findViewById(R.id.day4);
        day5 = findViewById(R.id.day5);
        day6 = findViewById(R.id.day6);
        date0 = findViewById(R.id.date0);
        date1 = findViewById(R.id.date1);
        date2 = findViewById(R.id.date2);
        date3 = findViewById(R.id.date3);
        date4 = findViewById(R.id.date4);
        date5 = findViewById(R.id.date5);
        date6 = findViewById(R.id.date6);
        img_d0 = findViewById(R.id.img_d0);
        img_d1 = findViewById(R.id.img_d1);
        img_d2 = findViewById(R.id.img_d2);
        img_d3 = findViewById(R.id.img_d3);
        img_d4 = findViewById(R.id.img_d4);
        img_d5 = findViewById(R.id.img_d5);
        img_d6 = findViewById(R.id.img_d6);
        cond_txt_d0 = findViewById(R.id.cond_txt_d0);
        cond_txt_d1 = findViewById(R.id.cond_txt_d1);
        cond_txt_d2 = findViewById(R.id.cond_txt_d2);
        cond_txt_d3 = findViewById(R.id.cond_txt_d3);
        cond_txt_d4 = findViewById(R.id.cond_txt_d4);
        cond_txt_d5 = findViewById(R.id.cond_txt_d5);
        cond_txt_d6 = findViewById(R.id.cond_txt_d6);
        img_n0 = findViewById(R.id.img_n0);
        img_n1 = findViewById(R.id.img_n1);
        img_n2 = findViewById(R.id.img_n2);
        img_n3 = findViewById(R.id.img_n3);
        img_n4 = findViewById(R.id.img_n4);
        img_n5 = findViewById(R.id.img_n5);
        img_n6 = findViewById(R.id.img_n6);
        cond_txt_n0 = findViewById(R.id.cond_txt_n0);
        cond_txt_n1 = findViewById(R.id.cond_txt_n1);
        cond_txt_n2 = findViewById(R.id.cond_txt_n2);
        cond_txt_n3 = findViewById(R.id.cond_txt_n3);
        cond_txt_n4 = findViewById(R.id.cond_txt_n4);
        cond_txt_n5 = findViewById(R.id.cond_txt_n5);
        cond_txt_n6 = findViewById(R.id.cond_txt_n6);
        wind_dir0 = findViewById(R.id.wind_dir0);
        wind_dir1 = findViewById(R.id.wind_dir1);
        wind_dir2 = findViewById(R.id.wind_dir2);
        wind_dir3 = findViewById(R.id.wind_dir3);
        wind_dir4 = findViewById(R.id.wind_dir4);
        wind_dir5 = findViewById(R.id.wind_dir5);
        wind_dir6 = findViewById(R.id.wind_dir6);
        wind_sc0 = findViewById(R.id.wind_sc0);
        wind_sc1 = findViewById(R.id.wind_sc1);
        wind_sc2 = findViewById(R.id.wind_sc2);
        wind_sc3 = findViewById(R.id.wind_sc3);
        wind_sc4 = findViewById(R.id.wind_sc4);
        wind_sc5 = findViewById(R.id.wind_sc5);
        wind_sc6 = findViewById(R.id.wind_sc6);
    }
    void draw(){
//        getDataService = GetDataService.getIntence();
//        weeklyWeatherForecast = getDataService.getWeeklyWeatherForecast();
        //int drawableId = getResources().getIdentifier("@drable/x" + weeklyWeatherForecast.getHeWeather6().get(0).getDaily_forecast().get(0).getCond_code_d(), "drawable", getPackageName());
        //img_d0.setBackgroundDrawable(drawableId);
        SimpleDateFormat std1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat std2 = new SimpleDateFormat("EEEE");
        SimpleDateFormat std3 = new SimpleDateFormat("MM/dd");
        Date d;
        String str;
        try {
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
    }

    public int getResource(String imageName){
        Context ctx=getBaseContext();
        int resId = getResources().getIdentifier(imageName, "drawable", ctx.getPackageName());
        //如果没有在"mipmap"下找到imageName,将会返回0
        return resId;
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button:
                //TODO
                new Thread() {
                    public void run() {
                        try {
                            string_weather_forcast = GetData.getJson("https://free-api.heweather.com/s6/weather/forecast?location=beijing&key=2d7b37b322a04de1ab17fca5f2e0f0ea");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0x001);
                    };
                }.start();
                break;
            default:
                //TODO
        }
    }
}

package com.example.a19093.project_endofterm;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a19093.project_endofterm.Request.WeeklyWeatherForecast;
import com.google.gson.Gson;

public class WeatherForecast extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private Button button;
    private String string_data;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0x001:
                    textView.append(string_data);
                    Gson gson = new Gson();
                    WeeklyWeatherForecast data = gson.fromJson(string_data,WeeklyWeatherForecast.class);
                    System.out.println("nihao___________________" + string_data);
                    textView.setText(data.getHeWeather6().get(0).getStatus());
                    //textView.setText(string_data);
                    Toast.makeText(WeatherForecast.this, "HTML代码加载完毕", Toast.LENGTH_SHORT).show();
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
    }

    void bindView(){
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button:
                //TODO
                new Thread() {
                    public void run() {
                        try {
                            string_data = GetData.getJson("https://free-api.heweather.com/s6/weather/forecast?location=beijing&key=1ad86ef7444245909304101fd5db5407");
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

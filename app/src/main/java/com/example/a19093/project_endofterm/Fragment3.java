package com.example.a19093.project_endofterm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Fragment3 extends Fragment implements View.OnClickListener{
    private TextView textView;
    private TextView list_city;
    private Button button;
    private Button btn_refresh;
    private View view;
    VariableApp variableApp;

    public Fragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment3, container, false);
        variableApp = (VariableApp) getContext().getApplicationContext();
        bindView();
        draw();
        return view;
    }

    private void bindView(){
        textView = view.findViewById(R.id.tv);
        list_city = view.findViewById(R.id.list_city);
        button = view.findViewById(R.id.add_city);
        btn_refresh = view.findViewById(R.id.btn_refresh);
        button.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);
    }

    private void draw(){
        textView.setText("当前位置："+ variableApp.getSelectName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                CityOperator cityOperator = new CityOperator(getContext());
                String string_city = data.getStringExtra("cityName");
                if(cityOperator.getIsExist(string_city) == 1){

                    City toCity = variableApp.getSelectCity();
                    cityOperator.update(toCity);
                    toCity = new City(string_city);
                    toCity.setIsSelect("否");
                    Log.e("nihaoa",toCity.toString3());
                    cityOperator.update(toCity);
                    toCity.setIsSelect("是");
                    variableApp.setSelectCity(toCity);
                    variableApp.setListCity();
                }
                else{
                    Log.e("nihaoo",string_city);
                    City city = new City(string_city);
                    city.setIsSelect("是");
                    cityOperator.add(city);
                    string_city = variableApp.getSelectCity().toString2();
                    City toCity = variableApp.getSelectCity();
                    cityOperator.update(toCity);
                    variableApp.setSelectCity(city);
                    variableApp.setListCity();
                }
                list_city.setText("");
                List<City> cityList = variableApp.getListCity();
                for(int i = 0; i < cityList.size(); i ++){
                    list_city.append(cityList.get(i).getName() + " " + cityList.get(i).getIsSelect() + "\n");
                }
                textView.setText("当前位置："+ variableApp.getSelectName());
                break;
            default:
                break;
        }

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_city:
                Intent intent = new Intent(getActivity(),Add_city.class);
                startActivityForResult(intent,1);
                break;
            case R.id.btn_refresh:
                list_city.setText("");
                List<City> cityList = variableApp.getListCity();
                for(int i = 0; i < cityList.size(); i ++){
                    list_city.append(cityList.get(i).getName() + " " + cityList.get(i).getIsSelect() + "\n");
                    Log.e("refresh",cityList.get(i).getName() + " " + cityList.get(i).getIsSelect());
                }
                textView.setText("当前位置："+ variableApp.getSelectCity().getName());
                Log.e("city_var",variableApp.getSelectCity().getName() + " " + variableApp.getSelectCity().getIsSelect());
                break;
            }
    }

}

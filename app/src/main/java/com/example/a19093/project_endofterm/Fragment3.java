package com.example.a19093.project_endofterm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Fragment3 extends Fragment implements View.OnClickListener{
    private ListView list_city;
    private Button button;
    private View view;
    private CityOperator cityOperator;
    private List<City> cityList;
    CityAdapter adapter;

    public Fragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment3, container, false);
        bindView();
        draw();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                String string_city = data.getStringExtra("cityName");
                boolean flag = false;
                Resources resources = this.getResources();
                String[] country = resources.getStringArray(R.array.city_array);
                for(int i = 1; i < country.length; i ++){
                    if(country[i].equals(string_city)){
                        flag = true;
                    }
                }
                if(!flag) break;
                if(cityOperator.getIsExist(string_city) == 1){
                    City city1 = cityOperator.getIsSelectCity();
                    cityOperator.update(city1);
                    City city2 = new City(string_city,"否");
                    cityOperator.update(city2);
                }
                else{
                    City city1 = cityOperator.getIsSelectCity();
                    cityOperator.update(city1);
                    City city2 = new City(string_city, "是");
                    cityOperator.add(city2);
                }
                draw();
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
        }
    }

    private void bindView(){
        cityOperator = new CityOperator(getContext());
        list_city = view.findViewById(R.id.list_city);
        button = view.findViewById(R.id.add_city);
        button.setOnClickListener(this);
    }
    private void draw(){
        cityList = cityOperator.getItemCity();
        adapter = new CityAdapter(cityList,getContext());
        list_city = view.findViewById(R.id.list_city);
        list_city.setAdapter(adapter);
    }



}

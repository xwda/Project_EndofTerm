package com.example.a19093.project_endofterm;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.Arrays;

public class Add_city extends AppCompatActivity implements View.OnClickListener{
    private Button btn_add;
    private Button btn_back;
    private AutoCompleteTextView autoCompleteTextView;
    VariableApp variableApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_city);
        this.autoCompleteTextView = (AutoCompleteTextView) this.findViewById(R.id.autoCompleteTextView);
        //1.得到资源对象
        Resources resources = this.getResources();
        //2.读取指定资源的数组
        String[] country = resources.getStringArray(R.array.city_array);
        System.out.println("country="+ Arrays.toString(country));
        //3.实例化数组适配器对象
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,//系统提供好的布局文件,即TextView控件
                country//数据源
        );
        //4.设置当前控件的适配器对象adapter
        this.autoCompleteTextView.setAdapter(adapter);
        bindView();
    }

    private void bindView(){
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                Intent intent = new Intent();
                String string_city = autoCompleteTextView.getText().toString();
                Log.e("huode", string_city);
                CityOperator cityOperator = new CityOperator(this);
                City city = new City(string_city);
                cityOperator.add(city);
                intent.putExtra("cityName",string_city);
                variableApp = (VariableApp) getApplicationContext();
                setResult(1,intent);
                finish();
                break;
            case R.id.btn_back:
                finish();
                break;
            default:
        }
    }
}

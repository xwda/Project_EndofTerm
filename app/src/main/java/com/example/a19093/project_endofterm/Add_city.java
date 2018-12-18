package com.example.a19093.project_endofterm;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;

public class Add_city extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button btn_add;
    private Button btn_back;
    private AutoCompleteTextView autoCompleteTextView;
    VariableApp variableApp;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_city);
        this.autoCompleteTextView = (AutoCompleteTextView) this.findViewById(R.id.autoCompleteTextView);
        context = getApplicationContext();
        //1.得到资源对象
        Resources resources = this.getResources();
        String[] country = resources.getStringArray(R.array.city_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1,//系统提供好的布局文件,即TextView控件
                country//数据源
        );
        //4.设置当前控件的适配器对象adapter
        this.autoCompleteTextView.setAdapter(adapter);
        this.autoCompleteTextView.setOnClickListener(this);
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
                intent.putExtra("cityName",string_city);
                setResult(1,intent);
                finish();
                break;
            case R.id.btn_back:
                finish();
                break;
            default:
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(context,"View postion is", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        String string_city = autoCompleteTextView.getText().toString();
        intent.putExtra("cityName",string_city);
        setResult(1,intent);
        finish();
    }
}

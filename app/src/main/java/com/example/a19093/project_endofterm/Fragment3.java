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

public class Fragment3 extends Fragment implements View.OnClickListener{
    private TextView textView;
    private Button button;
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
        button = view.findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    private void draw(){
        textView.setText("当前位置："+ variableApp.getCity().toString1());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                String city = data.getStringExtra("cityName");
                String[] s = city.split(" ");
                variableApp.getCity().init(s[0],s[1],s[2]);
                draw();
                break;
            default:
                break;
        }

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Intent intent = new Intent(getActivity(),Add_city.class);
                startActivityForResult(intent,1);
                break;
        }
    }

}

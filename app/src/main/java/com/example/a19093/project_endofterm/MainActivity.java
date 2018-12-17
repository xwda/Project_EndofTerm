package com.example.a19093.project_endofterm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.DialogPreference;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {

    //UI Objects
    private TextView txt_topbar;
    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel;
    private RadioButton rb_message;
    private RadioButton rb_better;
    private RadioButton rb_setting;
    private ViewPager vpager;
    private TextView textView;

    private MyFragmentPagerAdapter mAdapter;
    private List<Fragment> fragments;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isNetworkConnected()){
            showDialog();
        }
        Fragment1 f1 = new Fragment1();
        Fragment2 f2 = new Fragment2();
        Fragment3 f3 = new Fragment3();
        fragments = new ArrayList<>();
        bindViews();
        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.addOnPageChangeListener(this);
        rb_channel.setChecked(true);
        vpager.setCurrentItem(0);
        CityOperator cityOperator = new CityOperator(this);
    }

    private void bindViews() {
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_channel = (RadioButton) findViewById(R.id.rb_channel);
        rb_message = (RadioButton) findViewById(R.id.rb_message);
        rb_better = (RadioButton) findViewById(R.id.rb_better);
        rg_tab_bar.setOnCheckedChangeListener(this);



        textView = findViewById(R.id.textView);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_channel:
                vpager.setCurrentItem(PAGE_ONE);
                textView.setText("多天预报");
                break;
            case R.id.rb_message:
                vpager.setCurrentItem(PAGE_TWO);
                textView.setText("今日详情");
                break;
            case R.id.rb_better:
                vpager.setCurrentItem(PAGE_THREE);
                textView.setText("设定位置");
                break;
        }
    }


    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_channel.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_message.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb_better.setChecked(true);
                    break;
            }
        }
    }

    /**
     * 获取当前手机的网络状态
     *
     * @return
     */
    private boolean isNetworkConnected() {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isConnected());

    }

    private void showDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.my_dialog,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Button btn_cancel_high_opion = view.findViewById(R.id.btn_cancel_high_opion);
        Button btn_agree_high_opion = view.findViewById(R.id.btn_agree_high_opion);

        btn_cancel_high_opion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Intent intent = new Intent();
                //  类名一定要包含包名(这种显示意图不是很好，因为不同的系统可能包名，类名都不同，因此最好采用隐式意图进行跳转)
                // intent.setClassName("com.android.phone",
                // "com.android.phone.MiuiMobileNetworkSettings");
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_DATA_ROAMING_SETTINGS);
                //startActivity(intent);
                startActivityForResult(intent, 0);  // 如果在设置完成后需要再次进行操作，可以重写操作代码，在这里不再重写
                finish();
            }
        });



        btn_agree_high_opion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4
        //dialog.getWindow().setLayout((ScreenUtils.getScreenWidth(this)/4*3),LinearLayout.LayoutParams.WRAP_CONTENT);
    }






}

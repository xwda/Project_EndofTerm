package com.example.a19093.project_endofterm;


import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import java.util.List;

public class CityAdapter extends BaseAdapter{
    private Context context;
    private List<City> listCity;
    private CityOperator cityOperator;

    public CityAdapter(List<City> listCity, Context context){
       this.listCity = listCity;
       this.cityOperator = new CityOperator(context);
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){
        ViewHolder viewHolder = null;
        if(context == null){
            context = viewGroup.getContext();
        }
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city_item,null);
            viewHolder = new ViewHolder();
            viewHolder.cityName = view.findViewById(R.id.cityName);
            viewHolder.cityDelete = view.findViewById(R.id.cityDelete);
            viewHolder.citySet = view.findViewById(R.id.citySet);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder)view.getTag();
        viewHolder.cityName.setText(listCity.get(position).getName());
        if(listCity.get(position).getIsSelect().equals("是")){
            viewHolder.citySet.setText("当前位置");
            viewHolder.cityDelete.setText("");
        }
        else  if(listCity.get(position).getIsSelect().equals("否")){
            viewHolder.citySet.setText("设为当前位置");
            viewHolder.citySet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    City city1 = cityOperator.getIsSelectCity();
                    cityOperator.update(city1);
                    City city2 = new City(listCity.get(position).getName(), "否");
                    cityOperator.update(city2);
                    listCity = cityOperator.getItemCity();
                    notifyDataSetChanged();
                }
            });
            viewHolder.cityDelete.setText("点击删除");
            viewHolder.cityDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cityOperator = new CityOperator(context);
                    cityOperator.delete(listCity.get(position).getName());
                    listCity = cityOperator.getItemCity();
                    notifyDataSetChanged();
                }
            });
        }
        return view;
    }

    @Override
    public int getCount(){
        return listCity.size();
    }

    @Override
    public Object getItem(int i){
        return listCity.get(i);
    }

    @Override
    public long getItemId(int i){
        return i;
    }

    private class ViewHolder{
        TextView cityName;
        TextView cityDelete;
        TextView citySet;
    }
}

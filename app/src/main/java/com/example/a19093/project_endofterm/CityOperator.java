package com.example.a19093.project_endofterm;

import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

public class CityOperator {
    private MySQHelper dbHelper;
    private SQLiteDatabase db;

    public CityOperator(Context context) {
        dbHelper = new MySQHelper(context, "CitySQ", null, 1);
        db = dbHelper.getWritableDatabase();
    }

    public void add(City city) {
        try{
            ContentValues cValue = new ContentValues();
            cValue.put("name",city.getName());
            cValue.put("isSelect",city.getIsSelect());
            db.insert("CitySQ",null, cValue);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(City city) {
        try {
            ContentValues cValue = new ContentValues();
            if (city.getIsSelect().equals("否")) {
                cValue.put("isSelect", "是");
            } else {
                cValue.put("isSelect", "否");
            }
            String whereClause = "name=?";
            String[] whereArgs = {city.getName()};
            db.update("CitySQ", cValue, whereClause, whereArgs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(String name) {
        try{
            db.execSQL("delete from CitySQ where name=?", new String[] { name });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<City> getItemCity() {
        ArrayList<City> citys = new ArrayList<City>();
        Cursor c = db.rawQuery("select * from CitySQ order by isSelect desc", null);
        while (c.moveToNext()) {
            City city = new City();
            city.setName(c.getString(0));
            city.setIsSelect(c.getString(1));
            citys.add(city);
        }
        c.close();
        return citys;
    }

    public City getIsSelectCity() {
        Cursor c = db.rawQuery("select * from CitySQ", null);
        City city = null;
        String s1 = "",s2 = "";
        while (c.moveToNext()) {
            s1 = c.getString(0);
            s2 = c.getString(1);
            if(s2.equals("是")){
                break;
            }
        }
        if(s2.equals("是")){
            city = new City(s1,"是");
        }
        c.close();
        if(city == null){
            city = new City("泾川 平凉 甘肃");
            city.setIsSelect("是");
            add(city);
        }
        return city;
    }

    public int getIsExist(String name){
        Cursor c = db.rawQuery("select * from CitySQ", null);
        String s1,s2;
        while (c.moveToNext()) {
            City city = new City();
            s1 = c.getString(0);
            s2 = c.getString(1);
            if(s1.equals(name)){
                c.close();
                return 1;
            }
        }
        c.close();
        return 0;
    }
}
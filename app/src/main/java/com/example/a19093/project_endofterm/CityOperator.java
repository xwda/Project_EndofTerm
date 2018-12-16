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
        Log.e("add", city.getName() + city.getIsSelect());
        try{
//            db.execSQL("insert into CitySQ values(?,?)",
//                    new Object[] { city.getName(), city.getIsSelect()});
            ContentValues cValue = new ContentValues();
            cValue.put("name",city.getName());
            cValue.put("isSelect",city.getIsSelect());
            db.insert("CitySQ",null,cValue);
            Log.e("sucess", "niaho");
        }catch (Exception e){
            Log.e("eeeee", e.toString());
        }
    }


    public void update(City city) {
        Log.e("update",city.getName() + " " + city.getIsSelect());
        try {
            if (city.getIsSelect().equals("否")) {
                Log.e("aaa1", city.getName() + " " + city.getIsSelect());
                ContentValues cValue = new ContentValues();
                cValue.put("isSelect", "是");
                String whereClause = "name=?";
                String[] whereArgs = {city.getName()};
                db.update("CitySQ", cValue, whereClause, whereArgs);

            } else {
                Log.e("aaa2", city.getName() + " " + city.getIsSelect());
                ContentValues cValue = new ContentValues();
                cValue.put("isSelect", "否");
                String whereClause = "name=?";
                String[] whereArgs = {city.getName()};
                db.update("CitySQ", cValue, whereClause, whereArgs);
            }
        }catch (Exception e){
            Log.e("eeeee", e.toString());
        }

        List<City> mycity = getAllCity();
        for(int i = 0; i < mycity.size(); i ++){
            Log.e("dddd", mycity.get(i).getName() + " " + mycity.get(i).getIsSelect());
        }


    }

    public void delete(String name) {
        db.execSQL("delete from CitySQ where name=?", new String[] { name });
    }

    public List<City> getAllCity() {
        ArrayList<City> citys = new ArrayList<City>();
        Cursor c = db.rawQuery("select * from CitySQ", null);
        while (c.moveToNext()) {
            City city = new City();
            city.setName(c.getString(0));
            city.setIsSelect(c.getString(1));
            citys.add(city);
        }
        c.close();
        return citys;

    }

    public boolean CheckIsDataAlreadyInDBorNot(String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String Query = "Select * from CitySQ where name =?";
        Cursor cursor = db.rawQuery(Query, new String[] { name });
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public String getIsSelectName() {
        Cursor c = db.rawQuery("select * from CitySQ", null);
        String s1 = "",s2 = "";
        while (c.moveToNext()) {
            s1 = c.getString(0);
            s2 = c.getString(1);
            if(s2.equals("是")){
                break;
            }
        }
        c.close();
        return s1;
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
            city = new City(s1);
        }
        c.close();
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
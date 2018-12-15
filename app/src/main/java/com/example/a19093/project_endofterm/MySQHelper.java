package com.example.a19093.project_endofterm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQHelper extends SQLiteOpenHelper {
    public static final String CREATE_USERDATA = "create table if not exists CitySQ(name varchar(80),  isSelect varchar(8), primary key(name) )";
    public static final String INSERT_INIT = "insert";
    private Context mContext;
    public MySQHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERDATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}


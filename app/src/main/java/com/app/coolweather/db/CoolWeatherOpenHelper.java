package com.app.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/1/25.
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
    /**
     * province建表语句
     */

    private static final String CREATE_PROVINCE = "create table province("//必须是小写
            + "id integer primary key autoincrement,"
            + "province_name text,"
            + "province_code text)";
    /**
     * city建表语句
     */

    private static final String CREATE_CITY = "create table city("
            + "id integer primary key autoincrement,"
            + "city_name text,"
            + "city_code text"
            + "province_id integer)";
    /**
     * country建表语句
     */
    private static final String CREATE_COUNTY = "create table county("
            + "id integer primary key autoincrement,"
            + "county_name text,"
            + "county_code text,"
            + "city_id integer)";

    /**
     * 构造方法，第一个参数context,第二个参数数据库名，第三个参数允许我们在查询时候返回一个自定义的cursor,一般传入null
     * 第四个参数当前数据库的版本号，可用于对数据库进行升级操作
     */


    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);//创建province表
        db.execSQL(CREATE_CITY);//创建city表
        db.execSQL(CREATE_COUNTY);//创建county表

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        switch (i) {

        }
    }
}

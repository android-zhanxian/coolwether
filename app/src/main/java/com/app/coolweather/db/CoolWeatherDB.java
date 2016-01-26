package com.app.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.coolweather.model.City;
import com.app.coolweather.model.County;
import com.app.coolweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/26.
 * 这个类将会把一些常用的数据库操作封装起来
 */
public class CoolWeatherDB {
    /**
     * 数据库名
     */
    public static final String DB_NAME = "cool_weather";
    /*
    * 数据库版本
    */
    public static final int VERSION = 1;
    public static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;

    /*
    *将构造方法私有化
    **/
    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, "DB_NAME", null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /*
    *获取CoolWeatherDBde实例。
    **/
    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (coolWeatherDB == null) {
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    /**
     * 将province实例存蓄到数据库中。
     */
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("province", null, values);

        }

    }

    /**
     * 从数据库读取全国所有的省份信息。
     */
    public List<Province> loadProvince() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                list.add(province);
            } while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 将City的实例存蓄到数据库。
     */
    public void saveCity(City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            db.insert("city", null, values);
        }
    }

    /**
     * 从数据库中读取某省下的城市信息。
     */
    public List<City> loadCity(Province provinceId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("city", null, "province_id=?", new String[]{String.valueOf(provinceId)}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setProvinceId(provinceId);
                list.add(city);

            } while (cursor.moveToNext());
        }
        return list;
    }


    /**
     * 将County存蓄到数据库中。
     */
    public void saveCounty(County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("country_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("city_id", county.getCityId());
            db.insert("county", null, values);
        }
    }

    /**
     * 从数据下读取某城市下的县信息。
     */
    public List<County> loadCounties(County cityId) {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("county", null, "city_id=?", new String[]{String.valueOf(cityId)}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("county_id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);


            } while (cursor.moveToNext());

        }
        return list;
    }


}
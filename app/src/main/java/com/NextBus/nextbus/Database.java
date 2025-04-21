package com.NextBus.nextbus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static String dbname = "bus_db";
    public static String tbname = "kai_kinnigoli";
    public static String tbname1 = "vam_kaikamba";
    public static String tbname2 = "moo_elinje";
    public Database(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+tbname+"( name TEXT, arrival_time DATE)");
        db.execSQL("CREATE TABLE "+tbname1+"( name TEXT, arrival_time DATE)");
        db.execSQL("CREATE TABLE "+tbname2+"( name TEXT, arrival_time DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+dbname);
        onCreate(db);
    }

    public void insertdb(String tname, String bname, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", bname);
        contentValues.put("arrival_time", time);

        db.insert(tname, null, contentValues);
    }
    public Cursor getData(String tname){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+tname+" ORDER BY arrival_time ASC;", null);
    }

    public void deletebs(String tname, String bname, String time){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+tname+" WHERE name = '"+bname+"' AND arrival_time = '"+time+"';");
    }

}

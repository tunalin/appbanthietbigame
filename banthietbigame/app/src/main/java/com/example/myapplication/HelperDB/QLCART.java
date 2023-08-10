package com.example.myapplication.HelperDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class QLCART extends SQLiteOpenHelper {
    public static String DBNAME = "CART_THIETBIGAME_2";
    public static int VERSION = 1;

    public QLCART(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE CART(MASP INTEGER PRIMARY KEY, TENSP TEXT, GIASP INT , HINHSP TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

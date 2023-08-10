package com.example.myapplication.HelperDB;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Cart.CART;

import java.util.ArrayList;

public class HelperDB {
    SQLiteDatabase db;

    public HelperDB(Context context) {
        QLCART qlsvdbHelper = new QLCART(context);
        db = qlsvdbHelper.getWritableDatabase();
    }
    public long Themsanpham(String masp, String hinhsp, String tensp, Double giasp ) {
        ContentValues values = new ContentValues();
        values.put("MASP", masp);
        values.put("HINHSP", hinhsp);
        values.put("TENSP", tensp);
        values.put("GIASP", giasp);

        return db.insert("CART", null, values);
    }
    @SuppressLint("Range")
    public ArrayList<CART> layALLSP() {
        ArrayList<CART> list = new ArrayList<>();
        Cursor cursor = db.query("CART", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            CART sp = new CART();
            sp.setMasp(cursor.getString(cursor.getColumnIndex("MASP")));
            sp.setHinhsp(cursor.getString(cursor.getColumnIndex("HINHSP")));
            sp.setTensp(cursor.getString(cursor.getColumnIndex("TENSP")));
            sp.setDonggiasp(cursor.getDouble(cursor.getColumnIndex("GIASP")));
            list.add(sp);
        }
        cursor.close();
        return list;
    }
    public void  deleteSP(String id) {
        db.delete("CART", "MASP=?", new String[]{id});
        db.close();
    }
    public void updateSP(String id, int quantity) {
        ContentValues values = new ContentValues();
        values.put("SoLuong", quantity);
        db.update("CART", values, "MASP=?", new String[]{id});
    }
}

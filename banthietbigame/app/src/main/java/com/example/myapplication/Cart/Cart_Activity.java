package com.example.myapplication.Cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.HelperDB.HelperDB;
import com.example.myapplication.R;

import java.util.ArrayList;

public class Cart_Activity extends AppCompatActivity {

    Toolbar tbCart;
    RecyclerView rvCart;
    ImageView imgCartsanpham , btnmin1 , btnmax1, btBin;
    TextView tvTencartsp , tvGiacartsp , total ,tongtien;
    String masp, tensp, hinhsp;

    Double giasp;
    HelperDB helperDB;

    Adapter_Cart adapter_cart;
    ArrayList<CART> dataCart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        anhxa();
        ActionToolbar();

        Intent intent = getIntent();

        masp = intent.getStringExtra("masp");
        hinhsp = intent.getStringExtra("hinhsp");
        tensp = intent.getStringExtra("tensp");
        giasp = intent.getDoubleExtra("giasp", 0.0);


        helperDB = new HelperDB(this);
        long dulieu = helperDB.Themsanpham(masp, hinhsp, tensp, giasp);
        dataCart = helperDB.layALLSP();
        if (!dataCart.isEmpty()) {
            adapter_cart = new Adapter_Cart(this, dataCart);
            rvCart.setAdapter(adapter_cart);
            rvCart.setLayoutManager(new LinearLayoutManager(this));
        }
        double totalPrice = 0;
        for (CART sp : dataCart) {
            sp.setSoluongsp(1);
            totalPrice += sp.getDonggiasp() * sp.getSoluongsp();

        }
        tongtien.setText(String.valueOf(totalPrice));

    }

    void anhxa(){
        tbCart = findViewById(R.id.tbCart);
        rvCart = findViewById(R.id.rvCart);
        imgCartsanpham = findViewById(R.id.imgCartsanpham);
        btnmin1 = findViewById(R.id.btmin1);
        btnmax1 = findViewById(R.id.btmax1);
        btBin = findViewById(R.id.btBin);
        tvTencartsp = findViewById(R.id.tvTencartsp);
        tvGiacartsp = findViewById(R.id.tvGiacartsp);
        total = findViewById(R.id.total);
        tongtien = findViewById(R.id.tongtien);
    }

    private void ActionToolbar() {
        setSupportActionBar(tbCart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
                onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
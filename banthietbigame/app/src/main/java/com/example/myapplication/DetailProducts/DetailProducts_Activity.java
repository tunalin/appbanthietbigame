package com.example.myapplication.DetailProducts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Cart.CART;
import com.example.myapplication.Cart.Cart_Activity;
import com.example.myapplication.R;
import com.example.myapplication.SERVER;
import com.squareup.picasso.Picasso;

public class DetailProducts_Activity extends AppCompatActivity {

    ImageView imgHinhdetail, btmax, btmin;
    TextView tvTendetail, tvGiadetail, dtsoluong, tvChitiet, tvAddcart;
    Toolbar tbDetail;
    Intent intent;
    String masp, tensp, hinhsp, chitietsp;

    Double giasp;
    int numberorder = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_products);

        anhxa();
        ActionToolbar();
        TangGiamSL();


        intent = getIntent();
        tensp = intent.getStringExtra("tensp");
        giasp = Double.valueOf(intent.getStringExtra("giasp"));
        hinhsp = intent.getStringExtra("hinhsp");
        chitietsp = intent.getStringExtra("chitietsp");

        tvTendetail.setText(tensp);
        tvGiadetail.setText(String.valueOf(giasp));
        tvChitiet.setText(chitietsp);
        Picasso.get().load(SERVER.imagepath + hinhsp).into(imgHinhdetail);

        Themcartsp();
    }

    void anhxa() {
        imgHinhdetail = findViewById(R.id.imgHinhdetail);
        tvTendetail = findViewById(R.id.tvTendetail);
        tvGiadetail = findViewById(R.id.tvGiadetail);
        tvChitiet = findViewById(R.id.tvChitiet);
        tbDetail = findViewById(R.id.tbDetail);
        tvAddcart = findViewById(R.id.tvAddcart);
        btmax = findViewById(R.id.btmax);
        btmin = findViewById(R.id.btmin);
        dtsoluong = findViewById(R.id.dtsoluong);
    }

    public void Themcartsp(){
        tvAddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CART cart = new CART(masp, tensp, giasp, hinhsp);
                Intent intent1 = new Intent(DetailProducts_Activity.this, Cart_Activity.class);
                intent1.putExtra("masp", cart.getMasp());
                intent1.putExtra("tensp", cart.getTensp());
                intent1.putExtra("giasp", cart.getDonggiasp());
                intent1.putExtra("hinhsp", cart.getHinhsp());
//                Toast.makeText(DetailProducts_Activity.this, "Sản phẩm đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
            }
        });
    }
    
    public void TangGiamSL(){
        btmax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberorder = numberorder + 1;
                dtsoluong.setText(String.valueOf(numberorder));
            }
        });
        btmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberorder > 1) {
                    numberorder = numberorder - 1;
                }
                dtsoluong.setText(String.valueOf(numberorder));
            }
        });
    }

    private void ActionToolbar() {
        setSupportActionBar(tbDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
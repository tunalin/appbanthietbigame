package com.example.myapplication.ProductsByBrand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.ProductsByBrand.Adapter_ProductsByBrand;
import com.example.myapplication.ProductsByBrand.ProductsByBrand;
import com.example.myapplication.R;
import com.example.myapplication.SERVER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductsByBrand_Activity extends AppCompatActivity {

    RecyclerView rvProducsbybrand;
    Toolbar tbBrand;
    String maloai;
    Intent intent;
    ArrayList<ProductsByBrand> dataproductbybrand = new ArrayList<>();
    Adapter_ProductsByBrand adapter_productsByBrand;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_by_brand);

        rvProducsbybrand = findViewById(R.id.rvProducsbybrand);
        tbBrand = findViewById(R.id.tbBrand);

        ActionToolbar();

        intent = getIntent();
        maloai = intent.getStringExtra("maloai");

        LoadProductsByBrand();
    }

    void LoadProductsByBrand() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        dataproductbybrand.add(new ProductsByBrand(
                                jsonObject.getString("masp"),
                                jsonObject.getString("maloai"),
                                jsonObject.getString("tensp"),
                                jsonObject.getString("chitietsp"),
                                jsonObject.getString("danhgiasp"),
                                jsonObject.getString("dongiasp"),
                                jsonObject.getString("hinhsp")
                        ));
                        adapter_productsByBrand.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.spthoebrandpath + "?maloai='" + maloai + "'", thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

        adapter_productsByBrand = new Adapter_ProductsByBrand(this, dataproductbybrand);
        rvProducsbybrand.setAdapter(adapter_productsByBrand);
        rvProducsbybrand.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void ActionToolbar() {
        setSupportActionBar(tbBrand);
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
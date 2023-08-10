package com.example.myapplication.Search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Notify.donhang;
import com.example.myapplication.Products.Adapter_Products;
import com.example.myapplication.Products.PRODUCTS;
import com.example.myapplication.R;
import com.example.myapplication.SERVER;
import com.example.myapplication.Search.Adapter_Search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Search_Activity extends AppCompatActivity {

    RecyclerView rvSearch;
    EditText edtTimkiem;
    Toolbar toolbarSearch;
    String tim;
    TextView nhapsp;
    ScrollView scrolltimkiem;
    ArrayList<PRODUCTS> dataproducts = new ArrayList<>();
    Adapter_Search adapter_search;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rvSearch = findViewById(R.id.rvSearch);
        edtTimkiem = findViewById(R.id.edtTimkiem);
        toolbarSearch = findViewById(R.id.toolbarSearch);
        scrolltimkiem = findViewById(R.id.scrolltimkiem);
        nhapsp = findViewById(R.id.nhapsp);

        ActionToolbar();

        edtTimkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtTimkiem.getText().length() == 0){
                    dataproducts.clear();
                    nhapsp.setVisibility(View.VISIBLE);
                    scrolltimkiem.setVisibility(View.GONE);
                } else {
                    tim = s.toString();
                    nhapsp.setVisibility(View.GONE);
                    scrolltimkiem.setVisibility(View.VISIBLE);
                    LoadSearchProducts(tim);
                }
                adapter_search.notifyDataSetChanged();
            }
        });

    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarSearch);
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

    void LoadSearchProducts(String tim) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        dataproducts.clear();
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        dataproducts.add(new PRODUCTS(
                                jsonObject.getString("masp"),
                                jsonObject.getString("maloai"),
                                jsonObject.getString("tensp"),
                                jsonObject.getString("chitietsp"),
                                jsonObject.getString("danhgiasp"),
                                jsonObject.getString("dongiasp"),
                                jsonObject.getString("hinhsp")
                        ));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                adapter_search.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.timkiemsppath + "?tensp=" + tim, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

        adapter_search = new Adapter_Search(this, dataproducts);
        rvSearch.setAdapter(adapter_search);
        rvSearch.setLayoutManager(new GridLayoutManager(this, 1));
    }
}
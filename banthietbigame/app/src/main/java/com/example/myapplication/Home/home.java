package com.example.myapplication.Home;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Brand.Adapter_Brand;
import com.example.myapplication.Brand.BRAND;
import com.example.myapplication.Cart.Cart_Activity;
import com.example.myapplication.Drawer_Menu.Caidat_fragment;
import com.example.myapplication.Drawer_Menu.Dieukhoan_fragment;
import com.example.myapplication.Drawer_Menu.Lienhe_fragment;
import com.example.myapplication.Drawer_Menu.Thongtin_fragment;
import com.example.myapplication.Products.Adapter_Products;
import com.example.myapplication.Products.PRODUCTS;
import com.example.myapplication.R;
import com.example.myapplication.SERVER;
import com.example.myapplication.Search.Search_Activity;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class home extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button btnOpenDrawer;
    RecyclerView rvTopsp, rvBrand;
    ViewFlipper vfBanner;
    Toolbar toolbar;
    TextView tvSearch;

    ArrayList<PRODUCTS> dataproducts = new ArrayList<>();
    Adapter_Products adapter_products;

    ArrayList<BRAND> databrands = new ArrayList<>();
    Adapter_Brand adapter_brand;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        vfBanner = view.findViewById(R.id.vfBanner);
        rvTopsp = view.findViewById(R.id.rvTopsp);
        rvBrand = view.findViewById(R.id.rvBrand);
        toolbar = view.findViewById(R.id.androidxtoolbar);
        tvSearch = view.findViewById(R.id.sreachview);
        drawerLayout = view.findViewById(R.id.Drawerlayout);
        btnOpenDrawer = view.findViewById(R.id.btnOpenDrawer);
        navigationView = view.findViewById(R.id.NavigationView);
        navigationView.setNavigationItemSelectedListener(this);

        btnOpenDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Search_Activity.class);

                startActivity(intent);
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.giohang:
                        Intent intent = new Intent(getContext(), Cart_Activity.class);
                        startActivity(intent);
                }
                return false;
            }
        });

        LoadVFBanner();
        LoadProducts();
        LoadBrand();

        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itemThongtin) {
            Fragment fragment = new Thongtin_fragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_frame_layout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.itemCaidat) {
            Fragment fragment = new Caidat_fragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_frame_layout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.itemDieukhoan) {
            Fragment fragment = new Dieukhoan_fragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_frame_layout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.itemLienhe) {
            Fragment fragment = new Lienhe_fragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_frame_layout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        return true;
    }

    void LoadVFBanner() {
        ArrayList<String> banner = new ArrayList<>();

        banner.add(SERVER.slidepath + "hinh1.webp");
        banner.add(SERVER.slidepath + "hinh2.png");
        banner.add(SERVER.slidepath + "hinh3.png");
        banner.add(SERVER.slidepath + "hinh4.webp");

        for (String slide : banner) {
            ImageView hinh = new ImageView(getContext().getApplicationContext());

            Picasso.get().load(slide).into(hinh);
            hinh.setScaleType(ImageView.ScaleType.FIT_XY);
            vfBanner.addView(hinh);
        }

        vfBanner.setAutoStart(true);
        vfBanner.setFlipInterval(3000);

        Animation animation_slide_in = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.slide_out_right);
        vfBanner.setInAnimation(animation_slide_in);
        vfBanner.setOutAnimation(animation_slide_out);
    }

    void LoadBrand() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        databrands.add(new BRAND(
                                jsonObject.getString("maloai"),
                                jsonObject.getString("tenloai"),
                                jsonObject.getString("hinhloai")));
                    } catch (JSONException e) {
                        Toast.makeText(getContext().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                adapter_brand.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.brandpath, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

        adapter_brand = new Adapter_Brand(getContext(), databrands);
        rvBrand.setAdapter(adapter_brand);
        rvBrand.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    void LoadProducts() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

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
                        Toast.makeText(getContext().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                adapter_products.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.sanphampath, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

        adapter_products = new Adapter_Products(getContext(), dataproducts);
        rvTopsp.setAdapter(adapter_products);
        rvTopsp.setLayoutManager(new GridLayoutManager(getContext(), 1));
        rvTopsp.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }
}

package com.example.myapplication.Drawer_Menu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

public class Caidat_fragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button btnBack;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_caidat, container, false);
        drawerLayout = view.findViewById(R.id.Drawerlayout);
        btnBack = view.findViewById(R.id.btnBack);
        navigationView = view.findViewById(R.id.NavigationView);
        navigationView.setNavigationItemSelectedListener(this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
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
}

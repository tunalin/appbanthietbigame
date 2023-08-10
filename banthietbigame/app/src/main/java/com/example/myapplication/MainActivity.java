package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.Account.Profile_fragment;
import com.example.myapplication.Home.home;
import com.example.myapplication.Notify.notify;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    androidx.fragment.app.Fragment fragment;
    FragmentTransaction transaction;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_navigation = findViewById(R.id.bottom_navigation);
        OpenFragment(new home());

        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.home:
                        fragment = new home();
                        break;
                    case R.id.notify:
                        fragment = new notify();
                        break;
                    case R.id.account:
                        fragment = new Profile_fragment();
                        break;
                }
                OpenFragment(fragment);
                return true;
            }
        });
    }

    void OpenFragment(Fragment f) {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction = transaction.replace(R.id.nav_frame_layout, f);
        transaction.commit();
    }

    public String GetUsername() {
        return getIntent().getStringExtra("user");
    }

    public String GetPassword() {
        return getIntent().getStringExtra("pass");
    }
}
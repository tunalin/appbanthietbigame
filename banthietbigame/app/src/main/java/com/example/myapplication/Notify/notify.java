package com.example.myapplication.Notify;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class notify extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    View view;
    ArrayList<Fragment>FragmentArrayList = new ArrayList<>();
    VIEWPAGER_ADAPTER viewpager_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.notify_fragment,container,false);
        tabLayout = view.findViewById(R.id.tab_noti);
        viewPager2 = view.findViewById(R.id.vp2_notify);


        FragmentArrayList.add(new khuyenmai());
        FragmentArrayList.add(new donhang());

        viewpager_adapter = new VIEWPAGER_ADAPTER(getChildFragmentManager(),getLifecycle(),FragmentArrayList);
//        viewpager_adapter = new VIEWPAGER_ADAPTER(getActivity().getSupportFragmentManager().beginTransaction().add(khuyenmai));
        viewPager2.setAdapter(viewpager_adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Home").setIcon(R.drawable.baseline_home_24));
        tabLayout.addTab(tabLayout.newTab().setText("Notification").setIcon(R.drawable.baseline_notifications_active_24));

        //Thao tác vuốt qua trái phải -> tab sẽ thay đổi theo
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
}

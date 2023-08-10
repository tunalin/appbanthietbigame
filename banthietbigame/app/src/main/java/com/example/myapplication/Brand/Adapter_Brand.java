package com.example.myapplication.Brand;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ProductsByBrand.ProductsByBrand_Activity;
import com.example.myapplication.R;
import com.example.myapplication.SERVER;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Brand extends RecyclerView.Adapter<KHUNHNHIN_BRAND>{

    Context context;
    ArrayList<BRAND> data;

    public Adapter_Brand(Context context, ArrayList<BRAND> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public KHUNHNHIN_BRAND onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_brand_item, null);
        return new KHUNHNHIN_BRAND(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNHNHIN_BRAND holder, int position) {
        BRAND brand = data.get(position);

        Picasso.get().load(SERVER.logopath + brand.hinhloai).into(holder.imgHinhbrand);
        holder.tvTenbrand.setText(brand.tenloai);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductsByBrand_Activity.class);
                intent.putExtra("maloai", brand.maloai);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class KHUNHNHIN_BRAND extends RecyclerView.ViewHolder{

    ImageView imgHinhbrand;
    TextView tvTenbrand;

    public KHUNHNHIN_BRAND(@NonNull View itemView) {
        super(itemView);
        imgHinhbrand = itemView.findViewById(R.id.imgHinhbrand);
        tvTenbrand = itemView.findViewById(R.id.tvTenbrand);
    }
}

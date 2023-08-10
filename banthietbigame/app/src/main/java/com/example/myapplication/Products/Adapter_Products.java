package com.example.myapplication.Products;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DetailProducts.DetailProducts_Activity;
import com.example.myapplication.R;
import com.example.myapplication.SERVER;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Products extends RecyclerView.Adapter<KHUNHNHIN_SANPHAM> {
    Context context;
    ArrayList<PRODUCTS> data;

    public Adapter_Products(Context context, ArrayList<PRODUCTS> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public KHUNHNHIN_SANPHAM onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sanpham_item, null);
        return new KHUNHNHIN_SANPHAM(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNHNHIN_SANPHAM holder, int position) {
        PRODUCTS products = data.get(position);

        Picasso.get().load(SERVER.imagepath + products.hinhsp).into(holder.imgHinhsanpham);
        holder.tvTensanpham.setText(products.tensp);
        holder.tvGiasanpham.setText(products.donggiasp);
        holder.tvCHitiet.setText(products.chitietsp);
        holder.cardViewhot.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.animation_view));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProducts_Activity.class);
                intent.putExtra("tensp", products.tensp);
                intent.putExtra("giasp", products.donggiasp);
                intent.putExtra("hinhsp", products.hinhsp);
                intent.putExtra("chitietsp", products.chitietsp);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class KHUNHNHIN_SANPHAM extends RecyclerView.ViewHolder {

    ImageView imgHinhsanpham;
    TextView tvTensanpham, tvGiasanpham, tvCHitiet;

    CardView cardViewhot;

    public KHUNHNHIN_SANPHAM(@NonNull View itemView) {
        super(itemView);

        cardViewhot = itemView.findViewById(R.id.cartviewhot);
        imgHinhsanpham = itemView.findViewById(R.id.imgHinhsp1);
        tvTensanpham = itemView.findViewById(R.id.tvTensp1);
        tvGiasanpham = itemView.findViewById(R.id.tvGiasp1);
        tvCHitiet = itemView.findViewById(R.id.tvChitiet1);
    }
}

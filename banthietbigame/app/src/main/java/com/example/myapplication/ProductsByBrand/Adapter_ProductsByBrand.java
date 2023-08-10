package com.example.myapplication.ProductsByBrand;

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

public class Adapter_ProductsByBrand extends RecyclerView.Adapter<KHUNGNHIN_ProductsByBrand> {
    Context context;
    ArrayList<ProductsByBrand> data;

    public Adapter_ProductsByBrand(Context context, ArrayList<ProductsByBrand> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public KHUNGNHIN_ProductsByBrand onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.productsbybrand, null);
        return new KHUNGNHIN_ProductsByBrand(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHIN_ProductsByBrand holder, int position) {
        ProductsByBrand productsByBrand = data.get(position);

        Picasso.get().load(SERVER.imagepath + productsByBrand.hinhsp).into(holder.imgHinhsp);
        holder.tvTensp.setText(productsByBrand.tensp);
        holder.tvGiasp.setText(productsByBrand.donggiasp);
        holder.tvChitietlist.setText(productsByBrand.chitietsp);
        holder.cardViewloai.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.animation_view));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProducts_Activity.class);
                intent.putExtra("tensp", productsByBrand.tensp);
                intent.putExtra("giasp", productsByBrand.donggiasp);
                intent.putExtra("hinhsp", productsByBrand.hinhsp);
                intent.putExtra("chitietsp", productsByBrand.chitietsp);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class KHUNGNHIN_ProductsByBrand extends RecyclerView.ViewHolder {

    TextView tvTensp, tvGiasp, tvChitietlist;
    ImageView imgHinhsp;

    CardView cardViewloai;

    public KHUNGNHIN_ProductsByBrand(@NonNull View itemView) {
        super(itemView);
        imgHinhsp = itemView.findViewById(R.id.hinhlistsp);
        tvTensp = itemView.findViewById(R.id.tenlistsp);
        tvGiasp = itemView.findViewById(R.id.gialistsp);
        tvChitietlist = itemView.findViewById(R.id.tvChitietlist);
        cardViewloai = itemView.findViewById(R.id.cardviewloai);
    }
}

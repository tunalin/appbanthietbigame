package com.example.myapplication.Search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DetailProducts.DetailProducts_Activity;
import com.example.myapplication.Products.PRODUCTS;
import com.example.myapplication.R;
import com.example.myapplication.SERVER;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Search extends RecyclerView.Adapter<KHUNHNHIN_SEARCH> {

    Context context;
    ArrayList<PRODUCTS> data;

    public Adapter_Search(Context context, ArrayList<PRODUCTS> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public KHUNHNHIN_SEARCH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_search, null);
        return new KHUNHNHIN_SEARCH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNHNHIN_SEARCH holder, int position) {
        PRODUCTS products = data.get(position);

        Picasso.get().load(SERVER.imagepath + products.hinhsp).into(holder.imgHinhsanpham);
        holder.tvTensanpham.setText(products.tensp);

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

class KHUNHNHIN_SEARCH extends RecyclerView.ViewHolder {
    ImageView imgHinhsanpham;
    TextView tvTensanpham, tvGiaspsearch, tvDetailspsearch;

    public KHUNHNHIN_SEARCH(@NonNull View itemView) {
        super(itemView);

        imgHinhsanpham = itemView.findViewById(R.id.imgHinhspSearch);
        tvTensanpham = itemView.findViewById(R.id.tvItemsearch);
        tvGiaspsearch = itemView.findViewById(R.id.tvGiaspsearch);
        tvDetailspsearch = itemView.findViewById(R.id.tvDetailspsearch);
    }
}

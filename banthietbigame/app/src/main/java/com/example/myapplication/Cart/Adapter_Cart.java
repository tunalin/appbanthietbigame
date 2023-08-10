package com.example.myapplication.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.HelperDB.HelperDB;
import com.example.myapplication.R;
import com.example.myapplication.SERVER;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_Cart extends RecyclerView.Adapter<KHUNHNHIN_CART>{

    Context context;
    ArrayList<CART> data;

    public Adapter_Cart(Context context, ArrayList<CART> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public KHUNHNHIN_CART onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, null);
        return new KHUNHNHIN_CART(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNHNHIN_CART holder, int position) {
        CART cart = data.get(position);

        Picasso.get().load(SERVER.imagepath + cart.hinhsp).into(holder.imgCartsanpham);
        holder.tvTencartsp.setText(cart.getTensp());
        holder.tvGiacartsp.setText(String.valueOf(cart.getDonggiasp()));
        holder.dtsoluong1.setText(String.valueOf(cart.getSoluongsp()));
        holder.totaladapter.setText(String.valueOf(Math.round(cart.getDonggiasp()* (cart.getSoluongsp()+1))));


        holder.btnmin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cart.setSoluongsp(cart.getSoluongsp() - 1);
                holder.dtsoluong1.setText(String.valueOf(cart.getSoluongsp()));
                holder.totaladapter.setText(String.valueOf(Math.round(cart.getDonggiasp()* cart.getSoluongsp())));
                if (cart.getSoluongsp() < 1) {
                    HelperDB helperDB = new HelperDB(context);
                    helperDB.deleteSP(cart.getMasp());
                    data.remove(cart);
                    notifyDataSetChanged();
                }
            }
        });

        holder.btnmax1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.setSoluongsp(cart.getSoluongsp() + 1);
                holder.dtsoluong1.setText(String.valueOf(cart.getSoluongsp()));
                holder.totaladapter.setText(String.valueOf(Math.round(cart.getDonggiasp()* cart.getSoluongsp())));
                notifyDataSetChanged();
            }
        });

        holder.btBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelperDB helperDB = new HelperDB(context);
                helperDB.deleteSP(cart.getMasp());
                data.remove(cart);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class KHUNHNHIN_CART extends RecyclerView.ViewHolder{

    ImageView imgCartsanpham , btnmin1 , btnmax1, btBin;
    TextView tvTencartsp , tvGiacartsp, dtsoluong1 ,totaladapter;
    public KHUNHNHIN_CART(@NonNull View itemView) {
        super(itemView);
        imgCartsanpham = itemView.findViewById(R.id.imgCartsanpham);
        btnmin1 = itemView.findViewById(R.id.btmin1);
        btnmax1 = itemView.findViewById(R.id.btmax1);
        btBin = itemView.findViewById(R.id.btBin);
        tvTencartsp = itemView.findViewById(R.id.tvTencartsp);
        tvGiacartsp = itemView.findViewById(R.id.tvGiacartsp);
        dtsoluong1 = itemView.findViewById(R.id.dtsoluong1);
        totaladapter = itemView.findViewById(R.id.total);
    }
}

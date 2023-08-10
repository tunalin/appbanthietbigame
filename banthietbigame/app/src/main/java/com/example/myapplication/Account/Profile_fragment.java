package com.example.myapplication.Account;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.SERVER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Profile_fragment extends Fragment {

    TextView tvUsername, tvNgaysinh, tvEmail, tvPhone;
    Button btnDangxuat;
    String ten, ngaysinh, email, sdt;
    String user, pass;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        tvUsername = view.findViewById(R.id.tvUsername);
        tvNgaysinh = view.findViewById(R.id.tvNgaysinh);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvPhone = view.findViewById(R.id.tvPhone);
        btnDangxuat = view.findViewById(R.id.btnDangxuat);

        DangXuat();

        MainActivity mainActivity = (MainActivity) getActivity();
        user = mainActivity.GetUsername();
        pass = mainActivity.GetPassword();

        GetDataProfile();
        return view;
    }

    void DangXuat(){
        btnDangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Login_Activity.class);
                Toast.makeText(getContext(), "Đă đăng xuất", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ten = tvUsername.getText().toString().trim();
        ngaysinh = tvNgaysinh.getText().toString().trim();
        email = tvEmail.getText().toString().trim();
        sdt = tvPhone.getText().toString().trim();
    }
    void GetDataProfile() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, SERVER.khachhangpath, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        if (user.equals(jsonObject.getString("tenkh"))
                                && pass.equals(jsonObject.getString("pass"))) {
                            tvUsername.setText(user);
                            tvNgaysinh.setText(jsonObject.getString("ngaysinh"));
                            tvEmail.setText(jsonObject.getString("email"));
                            tvPhone.setText(jsonObject.getString("sodt"));
//                            Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
                        } else {
//                            Toast.makeText(getContext(), "Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}

package com.example.myapplication.Account;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.SERVER;

import java.util.HashMap;
import java.util.Map;

public class Register_Activity extends AppCompatActivity {

    EditText edtUser2, edtPass2, edtEmail2, edtNgaysinh2, edtSoDT2;
    Button btnCreate_Account2;
    TextView tvLogin;
    String ten, pass, email, ngaysinh, sodt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        anhxa();
        Login();
        Register();
    }

    void anhxa() {
        edtUser2 = findViewById(R.id.edtUser2);
        edtPass2 = findViewById(R.id.edtPass2);
        edtEmail2 = findViewById(R.id.edtEmail2);
        edtNgaysinh2 = findViewById(R.id.edtNgaysinh2);
        edtSoDT2 = findViewById(R.id.edtSoDT2);
        tvLogin = findViewById(R.id.tvLogin);
        btnCreate_Account2 = findViewById(R.id.btnCreate_Account2);
    }

    void Login(){
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });
    }

    void Register() {
        btnCreate_Account2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten = edtUser2.getText().toString();
                pass = edtPass2.getText().toString();
                email = edtEmail2.getText().toString();
                ngaysinh = edtNgaysinh2.getText().toString();
                sodt = edtSoDT2.getText().toString();

                if (ten.isEmpty() || pass.isEmpty() || email.isEmpty() || ngaysinh.isEmpty() || sodt.isEmpty()) {
                    AlertDialog.Builder thongbao = new AlertDialog.Builder(getApplicationContext());
                    thongbao.setMessage("Bạn chưa nhập đủ thông tin.");
                    thongbao.setCancelable(true);

                    thongbao.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog thongbao1 = thongbao.create();
                    thongbao1.show();
                } else {
                    AddAccount();
                }
            }
        });
    }

    void AddAccount() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.registerppath, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.toString().equals("thanhcong")) {
                    Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                    startActivity(intent);
                    Toast.makeText(Register_Activity.this, "Tạo thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Register_Activity.this, "Tạo thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register_Activity.this, "Lỗi rồi", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("tenKH", edtUser2.getText().toString());
                params.put("passKH", edtPass2.getText().toString());
                params.put("ngaysinhKH", edtNgaysinh2.getText().toString());
                params.put("emailKH", edtEmail2.getText().toString());
                params.put("soDT", edtSoDT2.getText().toString());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
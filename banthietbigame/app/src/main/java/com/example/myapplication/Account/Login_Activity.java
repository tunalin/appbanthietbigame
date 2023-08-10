package com.example.myapplication.Account;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.SERVER;

import java.util.HashMap;
import java.util.Map;

public class Login_Activity extends AppCompatActivity {

    EditText edtUser1, edtPass1;
    Button btnLogin;
    TextView tvSignUp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhxa();
        Signup();
        Login();
    }

    void anhxa() {
        edtUser1 = findViewById(R.id.edtUser1);
        edtPass1 = findViewById(R.id.edtPass1);
        btnLogin = findViewById(R.id.btnLogin1);
        tvSignUp1 = findViewById(R.id.tvSignUp1);
    }

    void Signup(){
        tvSignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, Register_Activity.class);
                startActivity(intent);
            }
        });
    }
    void Login() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.loginpath, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toString().equals("thanhcong")) {
                            if (!edtUser1.getText().toString().isEmpty() && !edtPass1.getText().toString().isEmpty()) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("user", edtUser1.getText().toString());
                                intent.putExtra("pass", edtPass1.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("username", edtUser1.getText().toString().trim());
                        params.put("password", edtPass1.getText().toString().trim());
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }
}
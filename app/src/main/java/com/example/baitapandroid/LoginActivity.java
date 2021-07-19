package com.example.baitapandroid;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import activity.MainActivity;
import ulti.Server;

public class LoginActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btnLogin, btnRegister, btnOk, btnCancel;
    CheckBox loginState;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);


        sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        edtEmail = findViewById(R.id.etEmailLogin);
        edtPassword = findViewById(R.id.etPassLogin);
        loginState = findViewById(R.id.checkBox);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();

//                if(edtUsername.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty())
//                {
//                    final Dialog dialog = new Dialog(LoginActivity.this);
//                    dialog.setContentView(R.layout.dialog_custom);
//                    btnOk = dialog.findViewById(R.id.btnOk);
//                    btnCancel = dialog.findViewById(R.id.btnCancel);
//
//                    btnOk.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//                            startActivityForResult(intent, 100);
//                            dialog.dismiss();
//                        }
//                    });
//                    btnCancel.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            dialog.cancel();
//                        }
//                    });
//                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//                    dialog.show();
//                }
//                else if(edtUsername.getText().toString().length()<5)
//                {
//                    edtUsername.setError("Least 5 string");
//                }
//                else if(edtPassword.getText().toString().length()<6)
//                {
//                    edtPassword.setError("Least 6 numbers");
//                }
//                else
//                {
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    intent.putExtra("Username", edtUsername.getText().toString());
//                    startActivity(intent);
//                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtEmail = edtEmail.getText().toString();
                String txtPassword = edtPassword.getText().toString();
//                if(TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword))
//                {
//                    Toast.makeText(LoginActivity.this, "All fiels required", Toast.LENGTH_SHORT).show();
//
//                }
                if(TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword))
                {
                    final Dialog dialog = new Dialog(LoginActivity.this);
                    dialog.setContentView(R.layout.dialog_custom);
                    btnOk = dialog.findViewById(R.id.btnOk);
                    btnCancel = dialog.findViewById(R.id.btnCancel);

                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                            startActivityForResult(intent, 100);
                            dialog.dismiss();
                        }
                    });
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.show();
                }
                else if(!isValid(txtEmail.toString()))
                {
                    edtEmail.setError("Invalid Email Address");
                }
                else if(txtPassword.toString().length() < 6)
                {
                    edtPassword.setError("Least 6 numbers");
                }
                else
                {
                    login(txtEmail, txtPassword);
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                //startActivityForResult(intent, 100);
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        String loginStatus = sharedPreferences.getString(getResources().getString(R.string.prefLoginState), "");
        if(loginStatus.equals("loggedin"))
        {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

    }

    private void login(String email, String password)
    {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable((false));
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Wait a second!");
        progressDialog.show();
        //String uRl = "http://192.168.1.14:8080/server/login.php";
        String uRl = Server.DuongdanLogin;
        StringRequest request = new StringRequest(Request.Method.POST, uRl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    String result = jsonObject.getString("data");

                    if(response.equals("Login Success"))
                    {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        if(loginState.isChecked())
                        {
                            editor.putString(getResources().getString(R.string.prefLoginState),"loggedin");
                        }
                        else
                        {
                            editor.putString(getResources().getString(R.string.prefLoginState),"loggedout");
                        }

//                        for(int i = 0; i < jsonObject.length(); i++)
//                        {
//                            JSONObject object = jsonObject.getJSONObject(String.valueOf(i));
//                            String name = object.getString("username");
//                            String email = object.getString("email");
//                            String phone = object.getString("mobile");
//                            String gender = object.getString("gender");
//                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                            intent.putExtra("username", name);
//                            intent.putExtra("email", email);
//                            intent.putExtra("mobile", phone);
//                            intent.putExtra("gender", gender);
//                            startActivity(intent);
//                            finish();
//                        }
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //return super.getParams();
                HashMap<String, String> param = new HashMap<>();
                param.put("email", email);
                param.put("psw", password);
                return  param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(3000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(LoginActivity.this).addToRequestQueue(request);


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 100 && resultCode == 101){
//            edtUsername.setText(data.getStringExtra("username"));
//            edtPassword.setText(data.getStringExtra("password"));
//        }
//        if(requestCode == 102 && resultCode == 101){
//            edtUsername.setText(data.getStringExtra("username"));
//            edtPassword.setText(data.getStringExtra("password"));
//        }
    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern =Pattern.compile(emailRegex);
        if(email == null)
            return false;
        return pattern.matcher(email).matches();
    }
}

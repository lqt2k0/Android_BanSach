package com.example.baitapandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class RegisterActivity extends AppCompatActivity {
    EditText edtEmail, edtUsername, edtPassword, edtConfirm, edtMobile;
    Button btnSignIn, btnCancel;
    RadioGroup rdbGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        edtEmail = findViewById(R.id.etEmail);
        edtUsername = findViewById(R.id.etUserCreate);
        edtPassword = findViewById(R.id.etPassCreate);
        edtConfirm = findViewById(R.id.etPassConCreate);
        edtMobile = findViewById(R.id.etMobile);
        btnSignIn = findViewById(R.id.btnSignInCreate);
        rdbGroup = findViewById(R.id.radioGroup);
        btnCancel = findViewById(R.id.btnCancelCreate);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtEmail = edtEmail.getText().toString().trim();
                String txtUsername = edtUsername.getText().toString().trim();
                String txtPassword = edtPassword.getText().toString().trim();
                String txtMobile = edtMobile.getText().toString().trim();
                if(!isValid(edtEmail.getText().toString()))
                {
                    edtEmail.setError("Invalid Email Address");
                    return;
                }
                if(edtUsername.getText().toString().isEmpty())
                {
                    edtUsername.setError("Username cannot be null ");
                    return;
                }
                if(edtPassword.getText().toString().isEmpty())
                {
                    edtPassword.setError("Password required");
                    return;
                }
                if(edtConfirm.getText().toString().isEmpty())
                {
                    edtConfirm.setError("Password required");
                    return;
                }
                if(!edtPassword.getText().toString().equals(edtConfirm.getText().toString()))
                {
//                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                    intent.putExtra("username", edtUsername.getText().toString());
//                    intent.putExtra("password", edtPassword.getText().toString());
//                    setResult(101, intent);
//                    finish();
                    edtPassword.setError("Password and confirm password does not match");
                    edtConfirm.setText("");
                    return;
                }
//                else
//                {
//                    edtPassword.setError("Password and confirm password does not match");
//                    edtConfirm.setText("");
//                    return;
//                }
//
                if(TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty((txtEmail))
                        || TextUtils.isEmpty(txtPassword) || TextUtils.isEmpty(txtMobile))
                {
                    Toast.makeText(RegisterActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int genderID = rdbGroup.getCheckedRadioButtonId();
                    RadioButton selected_Gender = rdbGroup.findViewById(genderID);
                    if(selected_Gender == null)
                    {
                        Toast.makeText(RegisterActivity.this, "Select gender please!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        String selectGender = selected_Gender.getText().toString();
                        registerNewAccount(txtUsername, txtEmail, txtPassword, txtMobile, selectGender);
                    }
                }
            }
        });
    }

    private void registerNewAccount(String username, String email, String password, String mobile, String gender)
    {
        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setCancelable((false));
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Registering New Account");
        progressDialog.show();
        //String uRl = "http://192.168.1.14:8080/server/register.php";
        String uRl = Server.DuongdanRegis;
        StringRequest request = new StringRequest(Request.Method.POST, uRl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Successfully Resgistered"))
                {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //return super.getParams();
                HashMap<String, String> param = new HashMap<>();
                param.put("username", username);
                param.put("email", email);
                param.put("psw", password);
                param.put("mobile", mobile);
                param.put("gender", gender);
                return  param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(3000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(RegisterActivity.this).addToRequestQueue(request);
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

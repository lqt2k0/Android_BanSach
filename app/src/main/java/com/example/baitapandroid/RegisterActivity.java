package com.example.baitapandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText edtEmail, edtUsername, edtPassword, edtConfirm;
    Button btnSignIn, btnCancel;
    RadioGroup rdbGender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        edtEmail = findViewById(R.id.etEmail);
        edtUsername = findViewById(R.id.etUserCreate);
        edtPassword = findViewById(R.id.etPassCreate);
        edtConfirm = findViewById(R.id.etPassConCreate);
        btnSignIn = findViewById(R.id.btnSignInCreate);
        btnCancel = findViewById(R.id.btnCancelCreate);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                if(edtPassword.getText().toString().equals(edtConfirm.getText().toString()))
                {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtra("username", edtUsername.getText().toString());
                    intent.putExtra("password", edtPassword.getText().toString());
                    setResult(101, intent);
                    finish();
                }
                else
                {
                    edtPassword.setError("Password and confirm password does not match");
                    edtConfirm.setText("");
                    return;
                }
            }
        });
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

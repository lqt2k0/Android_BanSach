package com.example.baitapandroid;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;

public class SharedPrefManager {
    private  static SharedPrefManager mInstance;
    private RequestQueue mRequestQueue;
    private Context context;

    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_USERNAME = "HoTenKH";
    private static final String KEY_USER_EMAIL = "EmailKH";
    private static final String KEY_USER_ADDRESS = "DiaChiKH";
    private static final String KEY_USER_GENDER = "GioiTinh";

    public SharedPrefManager(Context context)
    {
        this.context = context;
    }


    public static  synchronized SharedPrefManager getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(String email)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_EMAIL, email);
        editor.apply();
        return true;
    }


    public boolean isLoggedIn()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USER_EMAIL, null) != null)
        {
            return true;
        }
        return false;
    }

    public boolean isLoggedOut()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public String getUsername()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getUseremail()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }

    public String getUseraddress()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_ADDRESS, null);
    }

    public String getUsergender()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_GENDER, null);
    }
}

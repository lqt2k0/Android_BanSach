package com.example.baitapandroid;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private  static  MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private Context context;

    public MySingleton (Context context)
    {
        this.context = context;
        mRequestQueue = getmRequestQueue();
    }

    public  RequestQueue getmRequestQueue()
    {
        if(mRequestQueue == null)
        {
            Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return mRequestQueue;
    }

    public static  synchronized MySingleton getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public <T> void addToRequestQueue (Request <T> request)
    {
        mRequestQueue.add(request);
    }
}

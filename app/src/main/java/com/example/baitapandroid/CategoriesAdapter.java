package com.example.baitapandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoriesAdapter extends BaseAdapter {
    ArrayList<Furniture> data;
    public CategoriesAdapter()
    {

    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.line_db_layout, null);
        ImageView picture = convertView.findViewById(R.id.imageViewDB);
        TextView content = convertView.findViewById(R.id.tvContentDB);
        Furniture furniture = data.get(position);
        picture.setImageResource(furniture.getImage());
        content.setText(furniture.getName());
        return convertView;
    }
    Context context;

    public CategoriesAdapter(ArrayList<Furniture> data, Context context) {
        this.data = data;
        this.context = context;
    }
}

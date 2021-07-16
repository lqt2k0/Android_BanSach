//package com.example.baitapandroid;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class FurnitureAdapter extends BaseAdapter {
//    ArrayList<Furniture> data;
//    @Override
//    public int getCount() {
//        return data.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return data.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return (long)position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if(convertView == null)
//            convertView = LayoutInflater.from(context).inflate(R.layout.line_layout, null);
//        ImageView picture = convertView.findViewById(R.id.imageView);
//        TextView content = convertView.findViewById(R.id.tvContent);
//        TextView detail = convertView.findViewById(R.id.tvDetail);
//        Furniture furniture = data.get(position);
//        picture.setImageResource(furniture.image);
//        content.setText(furniture.name);
//        detail.setText(furniture.description);
//        return convertView;
//    }
//    Context context;
//
//    public FurnitureAdapter(ArrayList<Furniture> data, Context context) {
//        this.data = data;
//        this.context = context;
//    }
//}

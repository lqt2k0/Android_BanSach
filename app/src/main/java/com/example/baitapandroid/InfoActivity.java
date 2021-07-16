//package com.example.baitapandroid;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class InfoActivity extends AppCompatActivity {
//
//    ImageView image;
//    TextView txtDescription;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.info_layout);
//        image = findViewById(R.id.detail_img_News);
//        txtDescription = findViewById(R.id.detail_txtDescription);
//
//        Intent intent = getIntent();
//        Furniture furniture = (Furniture)intent.getExtras().get("news");
//        Furniture furnitureNoti = (Furniture)intent.getExtras().get("news2");
//
//        if(furniture != null){
//            this.setTitle(furniture.getName());
//            image.setImageResource(furniture.getImage());
//            txtDescription.setText(furniture.getDescription());
//        }
//        else
//            this.setTitle("Title Loading Fail");
//        if(furnitureNoti != null){
//            this.setTitle(furnitureNoti.getName());
//            image.setImageResource(furnitureNoti.getImage());
//            txtDescription.setText(furnitureNoti.getDescription());
//        }
//        else
//            this.setTitle("Title Loading Fail");
//    }
//}

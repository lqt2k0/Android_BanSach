//package com.example.baitapandroid;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ListView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import java.util.ArrayList;
//
//public class NotifcationFagment extends Fragment {
//    ListView listView;
//    ArrayList<Furniture> data;
//    FurnitureAdapter furnitureAdapter;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
////        return super.onCreateView(inflater, container, savedInstanceState);
//        return inflater.inflate(R.layout.fragment_notification_layout, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        listView = view.findViewById(R.id.lvProductRecent);
//        Utils utils = new Utils();
//        data = utils.getFurnitureHistory();
//        furnitureAdapter = new FurnitureAdapter(data, getContext());
//        listView.setAdapter(furnitureAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Utils.furnitureHistory.add(data.get(position));
//            }
//        });
//    }
//}

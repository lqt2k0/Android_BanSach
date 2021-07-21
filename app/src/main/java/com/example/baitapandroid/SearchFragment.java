package com.example.baitapandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import activity.BookDetailsActivity;
import model.Sach;

public class SearchFragment extends Fragment {

    ArrayList<Sach> data = new ArrayList<>();



    public SearchFragment(){}
    public SearchFragment(ArrayList<Sach> data){
        this.data = data;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_layout, container, false);

        ListView listView = view.findViewById(R.id.lvSearch);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
                intent.putExtra("news", data.get(position));
                startActivity(intent);
            }
        });
        return view;
    }
}

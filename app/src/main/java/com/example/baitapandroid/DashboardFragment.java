package com.example.baitapandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    ArrayList<Furniture> data = new ArrayList<>();
    CategoriesFragment categoriesFragment;

    public  DashboardFragment()
    {
        this.data = getData();
    }

    public DashboardFragment(ArrayList<Furniture> data){
        this.data = data;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        //return inflater.inflate(R.layout.fragment_dashboard_layout, container, false);
        View view = inflater.inflate(R.layout.fragment_dashboard_layout, container, false);
        GridView gridView = view.findViewById(R.id.gvProduct);
        gridView.setAdapter(new CategoriesAdapter(data, getActivity()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ArrayList<Furniture> temp = new ArrayList<>();
                for(Furniture f: data){
                    if(f.getCategory().equals(data.get(position).getCategory()))
                        temp.add(f);
                }
                categoriesFragment = new CategoriesFragment(temp);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, categoriesFragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        GridView gridView = view.findViewById(R.id.gvProduct);
//        gridView.setAdapter(new CategoriesAdapter(data, getActivity()));
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                ArrayList<Furniture> temp = new ArrayList<>();
//                for(Furniture f: data){
//                    if(f.getCategory().equals(data.get(position).getCategory()))
//                        temp.add(f);
//                }
//                categoriesFragment = new CategoriesFragment(temp);
//                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frameLayout, categoriesFragment);
//                fragmentTransaction.commit();
//            }
//        });
    }

    public ArrayList<Furniture> getData(){
        data.add(new Furniture("Ellen Metal Frame Loveseat","Nothing says “trendsetter", "BedRoom", R.drawable.pic9));
        data.add(new Furniture("Brock Side Table","The Brock is not your average piece of furniture","LivingRoom", R.drawable.pic3));
        data.add(new Furniture("Courtney Metal Frame Loveseat","Imagine your living area with a little more modern-contemporary","MeetingRoom", R.drawable.pic4));
        data.add(new Furniture("Savannah Aluminum", "How do you bring the warmth and luxurious comfort","Accessories", R.drawable.pic10));
        return data;
    }
}

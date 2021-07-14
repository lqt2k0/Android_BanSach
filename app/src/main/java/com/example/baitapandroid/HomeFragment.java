package com.example.baitapandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ListView listView;
    //ArrayList<Furniture> data;
    ArrayList<Furniture> data = new ArrayList<>();
    FurnitureAdapter furnitureAdapter;

    public  HomeFragment()
    {
        //this.data = getData();
    }
    public HomeFragment(ArrayList<Furniture> data)
    {
        //this.data  = new ArrayList<>();
        this.data = data;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_home_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //data = getData();
        furnitureAdapter = new FurnitureAdapter(data, getContext());
        listView = view.findViewById(R.id.lvProduct);
        data.add(new Furniture("Ellen Metal Frame Loveseat", "Nothing says “trendsetter” quite like this metal-framed number. Impress your house guests with the comfortable, deep seating and modern, yet relaxed design of this two-seater. Just as easy to assemble as it is to care for, Ellen comes together in under 30 minutes and features durable cushion covers that can be removed for easy cleaning. Drawing a bit of inspiration from our famous platform beds, this loveseat is uniquely crafted with durable wooden slats under the seat –creating the ideal spot for you and a friend to kick back and relax upon once you’re done setting up your new seating area (without breaking a sweat, of course).", R.drawable.pic1));
        data.add(new Furniture("Brock Side Table", "The Brock is not your average piece of furniture. In fact, it’s designed to surprise you with how quickly and effortlessly it can breathe new life into your home. Crafted with details like a distinct natural wood grain pattern and a clean-cut metal frame, this practical little table features two solid acacia wood surfaces spacious enough for housing all of your essentials like books, decor, or your mid-afternoon snack. Its thick steel frame is accented with brass-finished hardware, completing an impressive, modern-industrial style look. Aclass-act whether in the living room or your home office, this side table is understated luxury at its finest.", R.drawable.pic2));
        data.add(new Furniture("Courtney Metal Frame Loveseat", "Imagine your living area with a little more modern-contemporary flair and more comfort with this striking, metal-framed loveseat. This small sofa is made practical by its removable woven cushion covers, so your snacks and drinks are always welcome. Crafted with both style and swift assembly in mind, this two-seater comes together in less than 30 minutes and gives you and your oh-so-helpful friend a comfy place to stretch out and relax once you’ve put on the finishing touches. In fact, Courtney is highly likely to become the most sought after sitting spot in the house.", R.drawable.pic3));
        data.add(new Furniture("Savannah Aluminum and Acacia Wood Outdoor Sofa", "How do you bring the warmth and luxurious comfort of your living room into your own backyard, without breaking the bank? With the modern black metal frame, richly finished wood accents, and plush beige cushions of our Savannah Outdoor Sofa of course! This deep-seated sofa features a lightweight aluminum frame with real acacia wood accents that’s super easy to maneuver around your backyard when you’re looking to rearrange. It also includes soft fiber and memory foam cushions that can comfortably support hours of relaxing on your porch or deck. Designed with weather-proof cushion covers, weather-resistant finished solid wood, rust-proof aluminum, and an included waterproof cover with locking clips and air vents to prevent moisture buildup, you can rest assured that this investment will stay protected even through the colder months. The Savannah Sofa even comes with all the tools and instructions you need to put it together seamlessly and without breaking a sweat. And to make sure every inch of it is just right, a 1-year warranty is included to protect against manufacturer defects. Whether you use it to enjoy a meal with family or lounge solo with your morning cup of coffee, this expertly crafted three-seater has everything needed to spruce up your outdoor space.", R.drawable.pic4));
        data.add(new Furniture("Benton Mid-Century Sofa", "A wise bedding and furniture website once said, “Great style never goes out of style.” Were we that wise website? Definitely, maybe. Is this the contemporary-yet-comfy sofa that website was referencing? Definitely, definitely.", R.drawable.pic5));
        data.add(new Furniture("Jocelyn Contemporary Sofa", "What’s cooler than a contemporary couch with a winning design? Putting one together in less than 30 minutes! This sleek armless beige-colored couch is made with a durable and easy-to-clean fabric. Beat that!", R.drawable.pic6));
        data.add(new Furniture("Cool Touch Comfort Gel Memory Foam Hybrid Queen Mattress", "If hot sleeping keeps you up at night, your mattress is probably not pulling its weight. Fortunately for you, our queen size Cool Touch Comfort Gel Hybrid Mattress doubles as both a super-effective cooling machine and a supremely comfortable place to lay your head. We pack it with premium ingredients and materials, like our thermoregulating swirl gel memory foam and a cool feeling AirCool cover. Together, they optimize ventilation, encourage airflow and dispel excess body heat so your core temperature stays comfortable all night long. With a luxurious feel that comes from multiple layers of premium, CertiPUR-US certified foams and a microfiber quilted top, you’ll be snuggling up in total relaxation, with your joints and spine optimally supported all night long. And the Cool Touch Comfort Hybrid’s secret to keeping motion isolated and your curves gently supported? Our independent coil system, built with a perimeter of even heavier steel springs that strengthen the mattress’s edges. This prevents sagging and makes sitting on and getting in and out of bed that much easier. All rolled up and compressed into a box that arrives neatly packed at your doorstep, this advanced hybrid definitely sets a new bar for the word “cool”.", R.drawable.pic7));
        data.add(new Furniture("Benton Mid-Century Sofa", "A wise bedding and furniture website once said, “Great style never goes out of style.” Were we that wise website? Definitely, maybe. Is this the contemporary-yet-comfy sofa that website was referencing? Definitely, definitely.", R.drawable.pic8));
        data.add(new Furniture("Cool, Clean and Cozy", "Right beneath its moisture-wicking cover is a layer of copper infused memory foam. And when copper is added to our marshmallowy soft memory foam, you get a dreamy sleep surface that sucks up and shuts down excess heat before it disturbs you. Also, copper is innately antimicrobial. So pests like dust mites and bed bugs will avoid your mattress like it’s hot piping lava.", R.drawable.pic9));
        data.add(new Furniture("Brock Etagere Bookcase with Hanging Storage", "Beautiful and incredibly functional, the Brock Etagere Bookcase checks all the boxes for a practical storage solution, and more. For starters, it’s made with genuine acacia wood and a robust steel frame for ultimate strength and durability. Equipped with a convenient hanging rod and four spacious shelves, the Brock can easily double as a catchall for your mudroom coats and boots, extra closet space in the bedroom, or a place to house your personal library. And did we mention how easy-on-the-eyes this piece is? A unique, rustic wood grain pattern coupled with brass-finished hardware adds class and character that pulls together any room handsomely.", R.drawable.pic10));


        listView.setAdapter(furnitureAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Utils.furnitureHistory.add(data.get(position));
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("news", data.get(position));
                startActivity(intent);
                Toast.makeText(getContext(), "You've clicked Pic: "+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public ArrayList<Furniture> getData(){
//        data = new ArrayList<>();
//        data.add(new Furniture("Ellen Metal Frame Loveseat", "Nothing says “trendsetter” quite like this metal-framed number. Impress your house guests with the comfortable, deep seating and modern, yet relaxed design of this two-seater. Just as easy to assemble as it is to care for, Ellen comes together in under 30 minutes and features durable cushion covers that can be removed for easy cleaning. Drawing a bit of inspiration from our famous platform beds, this loveseat is uniquely crafted with durable wooden slats under the seat –creating the ideal spot for you and a friend to kick back and relax upon once you’re done setting up your new seating area (without breaking a sweat, of course).", R.drawable.pic1));
//        data.add(new Furniture("Brock Side Table", "The Brock is not your average piece of furniture. In fact, it’s designed to surprise you with how quickly and effortlessly it can breathe new life into your home. Crafted with details like a distinct natural wood grain pattern and a clean-cut metal frame, this practical little table features two solid acacia wood surfaces spacious enough for housing all of your essentials like books, decor, or your mid-afternoon snack. Its thick steel frame is accented with brass-finished hardware, completing an impressive, modern-industrial style look. Aclass-act whether in the living room or your home office, this side table is understated luxury at its finest.", R.drawable.pic2));
//        data.add(new Furniture("Courtney Metal Frame Loveseat", "Imagine your living area with a little more modern-contemporary flair and more comfort with this striking, metal-framed loveseat. This small sofa is made practical by its removable woven cushion covers, so your snacks and drinks are always welcome. Crafted with both style and swift assembly in mind, this two-seater comes together in less than 30 minutes and gives you and your oh-so-helpful friend a comfy place to stretch out and relax once you’ve put on the finishing touches. In fact, Courtney is highly likely to become the most sought after sitting spot in the house.", R.drawable.pic3));
//        data.add(new Furniture("Savannah Aluminum and Acacia Wood Outdoor Sofa", "How do you bring the warmth and luxurious comfort of your living room into your own backyard, without breaking the bank? With the modern black metal frame, richly finished wood accents, and plush beige cushions of our Savannah Outdoor Sofa of course! This deep-seated sofa features a lightweight aluminum frame with real acacia wood accents that’s super easy to maneuver around your backyard when you’re looking to rearrange. It also includes soft fiber and memory foam cushions that can comfortably support hours of relaxing on your porch or deck. Designed with weather-proof cushion covers, weather-resistant finished solid wood, rust-proof aluminum, and an included waterproof cover with locking clips and air vents to prevent moisture buildup, you can rest assured that this investment will stay protected even through the colder months. The Savannah Sofa even comes with all the tools and instructions you need to put it together seamlessly and without breaking a sweat. And to make sure every inch of it is just right, a 1-year warranty is included to protect against manufacturer defects. Whether you use it to enjoy a meal with family or lounge solo with your morning cup of coffee, this expertly crafted three-seater has everything needed to spruce up your outdoor space.", R.drawable.pic4));
//        data.add(new Furniture("Benton Mid-Century Sofa", "A wise bedding and furniture website once said, “Great style never goes out of style.” Were we that wise website? Definitely, maybe. Is this the contemporary-yet-comfy sofa that website was referencing? Definitely, definitely.", R.drawable.pic5));
//        data.add(new Furniture("Jocelyn Contemporary Sofa", "What’s cooler than a contemporary couch with a winning design? Putting one together in less than 30 minutes! This sleek armless beige-colored couch is made with a durable and easy-to-clean fabric. Beat that!", R.drawable.pic6));
//        data.add(new Furniture("Cool Touch Comfort Gel Memory Foam Hybrid Queen Mattress", "If hot sleeping keeps you up at night, your mattress is probably not pulling its weight. Fortunately for you, our queen size Cool Touch Comfort Gel Hybrid Mattress doubles as both a super-effective cooling machine and a supremely comfortable place to lay your head. We pack it with premium ingredients and materials, like our thermoregulating swirl gel memory foam and a cool feeling AirCool cover. Together, they optimize ventilation, encourage airflow and dispel excess body heat so your core temperature stays comfortable all night long. With a luxurious feel that comes from multiple layers of premium, CertiPUR-US certified foams and a microfiber quilted top, you’ll be snuggling up in total relaxation, with your joints and spine optimally supported all night long. And the Cool Touch Comfort Hybrid’s secret to keeping motion isolated and your curves gently supported? Our independent coil system, built with a perimeter of even heavier steel springs that strengthen the mattress’s edges. This prevents sagging and makes sitting on and getting in and out of bed that much easier. All rolled up and compressed into a box that arrives neatly packed at your doorstep, this advanced hybrid definitely sets a new bar for the word “cool”.", R.drawable.pic7));
//        data.add(new Furniture("Benton Mid-Century Sofa", "A wise bedding and furniture website once said, “Great style never goes out of style.” Were we that wise website? Definitely, maybe. Is this the contemporary-yet-comfy sofa that website was referencing? Definitely, definitely.", R.drawable.pic8));
//        data.add(new Furniture("Cool, Clean and Cozy", "Right beneath its moisture-wicking cover is a layer of copper infused memory foam. And when copper is added to our marshmallowy soft memory foam, you get a dreamy sleep surface that sucks up and shuts down excess heat before it disturbs you. Also, copper is innately antimicrobial. So pests like dust mites and bed bugs will avoid your mattress like it’s hot piping lava.", R.drawable.pic9));
//        data.add(new Furniture("Brock Etagere Bookcase with Hanging Storage", "Beautiful and incredibly functional, the Brock Etagere Bookcase checks all the boxes for a practical storage solution, and more. For starters, it’s made with genuine acacia wood and a robust steel frame for ultimate strength and durability. Equipped with a convenient hanging rod and four spacious shelves, the Brock can easily double as a catchall for your mudroom coats and boots, extra closet space in the bedroom, or a place to house your personal library. And did we mention how easy-on-the-eyes this piece is? A unique, rustic wood grain pattern coupled with brass-finished hardware adds class and character that pulls together any room handsomely.", R.drawable.pic10));
//
//        return data;
//    }
}

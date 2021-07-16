package activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.baitapandroid.R;
import com.example.baitapandroid.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.theLoaiSachAdapter;
import model.theLoaiSach;
import ulti.CheckConnection;
import ulti.Server;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipperTrangChu;
    RecyclerView recyclerViewTrangChu;
    NavigationView navigationView;
    ListView listViewTrangChu;
    DrawerLayout drawerLayout;
    ArrayList<theLoaiSach> mangtheloaisach;
    theLoaiSachAdapter theloaisachAdapter;
    int id = 0;
    String tenTheLoai = "";
    String hinhAnhTheLoai = "";
//    FrameLayout frameLayout;
//    Fragment current_layout;
//    //EditText editText;
//    SearchView searchView;
//    ArrayList<Furniture> data = new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
        {
            ActionBar();
            ActionViewFlipper();
            getDuLieuTheLoai();
        }
        else
        {
            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
            finish();
        }
//        //Anh xa
//        getData(data);
//        current_layout = new HomeFragment(data);
//        loadFragment(current_layout);
//        frameLayout = findViewById(R.id.frameLayout);
//        bottomNavigationView = findViewById(R.id.menu_bottom);
//        searchView = findViewById(R.id.search_view);
//        //getData(data);
//        searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadFragment(new SearchFragment());
//            }
//        });
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                ArrayList<Furniture> temp = FilterNow(data, newText);
//                loadFragment(new SearchFragment(temp));
//                return true;
            }

    private void getDuLieuTheLoai() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanTheLoaiSP, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null)
                {
                    for (int i = 0; i < response.length(); i++)
                    {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("MaTheLoai");
                            tenTheLoai = jsonObject.getString("TenTheLoai");
                            hinhAnhTheLoai = jsonObject.getString("HinhAnh");
                            mangtheloaisach.add(new theLoaiSach(id, tenTheLoai, hinhAnhTheLoai));
                            theloaisachAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangtheloaisach.add(3, new theLoaiSach(0, "Liên Hệ", "https://img.icons8.com/clouds/2x/phone.png"));
                    mangtheloaisach.add(4, new theLoaiSach(0, "Thông Tin", "https://img.icons8.com/clouds/2x/documents.png"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(), error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://cdn0.fahasa.com/media/magentothem/banner7/BTS-T6.920x420.3.png");
        mangquangcao.add("https://cdn0.fahasa.com/media/magentothem/banner7/monthlysale_resize_920.png");
        mangquangcao.add("https://cdn0.fahasa.com/media/magentothem/banner7/TrangManga920x420.png");
        mangquangcao.add("https://cdn0.fahasa.com/media/magentothem/banner7/920x420_phienchodocu.png");

        for (int i = 0; i < mangquangcao.size(); i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperTrangChu.addView(imageView);
        }
        viewFlipperTrangChu.setFlipInterval(5000);
        viewFlipperTrangChu.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipperTrangChu.setInAnimation(animation_slide_in);
        viewFlipperTrangChu.setInAnimation(animation_slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void Anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        viewFlipperTrangChu = (ViewFlipper) findViewById(R.id.viewFlipper);
        recyclerViewTrangChu = (RecyclerView) findViewById(R.id.recycleViewTrangChu);
        navigationView = (NavigationView) findViewById(R.id.naviTrangChu);
        listViewTrangChu = (ListView) findViewById(R.id.lstViewTrangChu);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.menu_bottom);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mangtheloaisach = new ArrayList<>();
        mangtheloaisach.add(0, new theLoaiSach(0, "Trang Chủ", "https://img.icons8.com/bubbles/2x/home.png"));
        theloaisachAdapter = new theLoaiSachAdapter(mangtheloaisach, getApplicationContext());
        listViewTrangChu.setAdapter(theloaisachAdapter);
    }


//        });

//        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                hideSoftKeyboard(searchView);
//                loadFragment(current_layout);
//                return true;
//            }
//        });
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                switch (id) {
//                    case R.id.menu_home:
//                        getSupportActionBar().setTitle("Home");
//                        current_layout = new HomeFragment();
//                        break;
//                    case R.id.menu_dashboard:
//                        getSupportActionBar().setTitle("Dashboard");
//                        current_layout = new DashboardFragment();
//                        break;
//                    case R.id.menu_notifications:
//                        getSupportActionBar().setTitle("Notification");
//                        current_layout = new NotifcationFagment();
//                        break;
//                    case R.id.menu_supervisor_account:
//                        getSupportActionBar().setTitle("Profile");
//                        current_layout = new AccountFragment();
//                        break;
//                }
//                loadFragment(current_layout);
//                return true;
//            }
//        });
//    }
//    public void loadFragment(Fragment fragment)
//    {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.frameLayout, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }
//
//    public void getDataDash(ArrayList<Categories> datadash)
//    {
//        data.add(new Furniture("Ellen Metal Frame Loveseat","Nothing says “trendsetter", "BedRoom", R.drawable.pic9));
//        data.add(new Furniture("Brock Side Table","The Brock is not your average piece of furniture","LivingRoom", R.drawable.pic3));
//        data.add(new Furniture("Courtney Metal Frame Loveseat","Imagine your living area with a little more modern-contemporary","MeetingRoom", R.drawable.pic4));
//        data.add(new Furniture("Savannah Aluminum", "How do you bring the warmth and luxurious comfort","Accessories", R.drawable.pic10));
//    }
//
//    public void getData(ArrayList<Furniture> data)
//    {
//        data.add(new Furniture("Ellen Metal Frame Loveseat", "Nothing says “trendsetter” quite like this metal-framed number. Impress your house guests with the comfortable, deep seating and modern, yet relaxed design of this two-seater. Just as easy to assemble as it is to care for, Ellen comes together in under 30 minutes and features durable cushion covers that can be removed for easy cleaning. Drawing a bit of inspiration from our famous platform beds, this loveseat is uniquely crafted with durable wooden slats under the seat –creating the ideal spot for you and a friend to kick back and relax upon once you’re done setting up your new seating area (without breaking a sweat, of course).", "BedRoom", R.drawable.pic1));
//        data.add(new Furniture("Brock Side Table", "The Brock is not your average piece of furniture. In fact, it’s designed to surprise you with how quickly and effortlessly it can breathe new life into your home. Crafted with details like a distinct natural wood grain pattern and a clean-cut metal frame, this practical little table features two solid acacia wood surfaces spacious enough for housing all of your essentials like books, decor, or your mid-afternoon snack. Its thick steel frame is accented with brass-finished hardware, completing an impressive, modern-industrial style look. Aclass-act whether in the living room or your home office, this side table is understated luxury at its finest.","LivingRoom", R.drawable.pic2));
//        data.add(new Furniture("Courtney Metal Frame Loveseat", "Imagine your living area with a little more modern-contemporary flair and more comfort with this striking, metal-framed loveseat. This small sofa is made practical by its removable woven cushion covers, so your snacks and drinks are always welcome. Crafted with both style and swift assembly in mind, this two-seater comes together in less than 30 minutes and gives you and your oh-so-helpful friend a comfy place to stretch out and relax once you’ve put on the finishing touches. In fact, Courtney is highly likely to become the most sought after sitting spot in the house.","MeetingRoom", R.drawable.pic3));
//        data.add(new Furniture("Savannah Aluminum and Acacia Wood Outdoor Sofa", "How do you bring the warmth and luxurious comfort of your living room into your own backyard, without breaking the bank? With the modern black metal frame, richly finished wood accents, and plush beige cushions of our Savannah Outdoor Sofa of course! This deep-seated sofa features a lightweight aluminum frame with real acacia wood accents that’s super easy to maneuver around your backyard when you’re looking to rearrange. It also includes soft fiber and memory foam cushions that can comfortably support hours of relaxing on your porch or deck. Designed with weather-proof cushion covers, weather-resistant finished solid wood, rust-proof aluminum, and an included waterproof cover with locking clips and air vents to prevent moisture buildup, you can rest assured that this investment will stay protected even through the colder months. The Savannah Sofa even comes with all the tools and instructions you need to put it together seamlessly and without breaking a sweat. And to make sure every inch of it is just right, a 1-year warranty is included to protect against manufacturer defects. Whether you use it to enjoy a meal with family or lounge solo with your morning cup of coffee, this expertly crafted three-seater has everything needed to spruce up your outdoor space.","Accessories", R.drawable.pic4));
//        data.add(new Furniture("Benton Mid-Century Sofa", "A wise bedding and furniture website once said, “Great style never goes out of style.” Were we that wise website? Definitely, maybe. Is this the contemporary-yet-comfy sofa that website was referencing? Definitely, definitely.", "BedRoom", R.drawable.pic5));
//        data.add(new Furniture("Jocelyn Contemporary Sofa", "What’s cooler than a contemporary couch with a winning design? Putting one together in less than 30 minutes! This sleek armless beige-colored couch is made with a durable and easy-to-clean fabric. Beat that!", "BedRoom", R.drawable.pic6));
//        data.add(new Furniture("Cool Touch Comfort Gel Memory Foam Hybrid Queen Mattress", "If hot sleeping keeps you up at night, your mattress is probably not pulling its weight. Fortunately for you, our queen size Cool Touch Comfort Gel Hybrid Mattress doubles as both a super-effective cooling machine and a supremely comfortable place to lay your head. We pack it with premium ingredients and materials, like our thermoregulating swirl gel memory foam and a cool feeling AirCool cover. Together, they optimize ventilation, encourage airflow and dispel excess body heat so your core temperature stays comfortable all night long. With a luxurious feel that comes from multiple layers of premium, CertiPUR-US certified foams and a microfiber quilted top, you’ll be snuggling up in total relaxation, with your joints and spine optimally supported all night long. And the Cool Touch Comfort Hybrid’s secret to keeping motion isolated and your curves gently supported? Our independent coil system, built with a perimeter of even heavier steel springs that strengthen the mattress’s edges. This prevents sagging and makes sitting on and getting in and out of bed that much easier. All rolled up and compressed into a box that arrives neatly packed at your doorstep, this advanced hybrid definitely sets a new bar for the word “cool”.","LivingRoom", R.drawable.pic7));
//        data.add(new Furniture("Benton Mid-Century Sofa", "A wise bedding and furniture website once said, “Great style never goes out of style.” Were we that wise website? Definitely, maybe. Is this the contemporary-yet-comfy sofa that website was referencing? Definitely, definitely.", "BedRoom", R.drawable.pic8));
//        data.add(new Furniture("Cool, Clean and Cozy", "Right beneath its moisture-wicking cover is a layer of copper infused memory foam. And when copper is added to our marshmallowy soft memory foam, you get a dreamy sleep surface that sucks up and shuts down excess heat before it disturbs you. Also, copper is innately antimicrobial. So pests like dust mites and bed bugs will avoid your mattress like it’s hot piping lava.","MeetingRoom", R.drawable.pic9));
//        data.add(new Furniture("Brock Etagere Bookcase with Hanging Storage", "Beautiful and incredibly functional, the Brock Etagere Bookcase checks all the boxes for a practical storage solution, and more. For starters, it’s made with genuine acacia wood and a robust steel frame for ultimate strength and durability. Equipped with a convenient hanging rod and four spacious shelves, the Brock can easily double as a catchall for your mudroom coats and boots, extra closet space in the bedroom, or a place to house your personal library. And did we mention how easy-on-the-eyes this piece is? A unique, rustic wood grain pattern coupled with brass-finished hardware adds class and character that pulls together any room handsomely.","MeetingRoom", R.drawable.pic10));
//    }
//
//    ArrayList<Furniture> FilterNow(ArrayList<Furniture> data, String key){
//        if(key.isEmpty())
//            return data;
//        ArrayList<Furniture> temp = new ArrayList<>();
//        for(Furniture furniture: data){
//            if(furniture.getName().toLowerCase().trim().contains(key.toLowerCase().trim()))
//                temp.add(furniture);
//        }
//        return temp;
//    }
//
//    public void hideSoftKeyboard(View view) {
//        InputMethodManager imm
//                = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }
}
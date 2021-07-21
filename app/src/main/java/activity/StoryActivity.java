package activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.baitapandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapter.StoryAdapter;
import model.Sach;
import ulti.CheckConnection;
import ulti.Server;

public class StoryActivity extends AppCompatActivity {

    Toolbar toolbarStory;
    ListView lvStory;
    StoryAdapter StoryAdapter;
    ArrayList<Sach> mangSachStory;
    int idMaTheLoaiSachStory = 0;
    int page = 1;
    View footerView;
    boolean isLoading = false;
    boolean limitData = false;
    StoryActivity.mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_activity);

        if(CheckConnection.haveNetworkConnection(StoryActivity.this))
        {
            AnhXa();
            GetIdTheLoaiSach();
            ActionToolbar();
            GetData(page);
            //LoadMoreData();
            ClickItem();
        }
        else
        {
            CheckConnection.ShowToast_Short(StoryActivity.this, "Check your connection!");
            finish();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top_nav_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_giohang:
                Intent intent = new Intent(StoryActivity.this, CartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void ClickItem()
    {
        lvStory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StoryActivity.this, BookDetailsActivity.class);
                intent.putExtra("thongtinsach", mangSachStory.get(position));
                startActivity(intent);
            }
        });
    }

    private void LoadMoreData() {
        lvStory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StoryActivity.this, BookDetailsActivity.class);
                intent.putExtra("thongtinsach", mangSachStory.get(position));
                startActivity(intent);
            }
        });
        lvStory.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && isLoading == false && limitData == false )
                {
                    isLoading = true;
                    StoryActivity.ThreadData threadData = new StoryActivity.ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(StoryActivity.this);
        String duongdan = Server.DuongdanSachStory+String.valueOf(Page);
        //String duongdan = "http://192.168.1.5:8080/server/getAllSachTheoTheLoai.php?page="+Page;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int idStory = 0;
                String tenStory = "";
                String hinhStory = "";
                //String tenTGStory = "";
                String motaStory = "";
                int giaStory = 0;
                int idTGStory = 0;
                int idtheloaiStory = 0;
                if(response != null && response.length() != 2)
                {
                    lvStory.removeFooterView(footerView);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            idStory = jsonObject.getInt("MaSach");
                            tenStory = jsonObject.getString("TenSach");
                            hinhStory = jsonObject.getString("HinhAnh");
                            motaStory = jsonObject.getString("MoTa");
                            idtheloaiStory = jsonObject.getInt("MaTheLoai");
                            idTGStory = jsonObject.getInt("MaTacGia");
                            giaStory = jsonObject.getInt("GiaBan");
                            mangSachStory.add(new Sach(idStory, tenStory, hinhStory, motaStory, idtheloaiStory, idTGStory, giaStory));
                            StoryAdapter.notifyDataSetChanged();
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    limitData = true;
                    lvStory.removeFooterView(footerView);
                    CheckConnection.ShowToast_Short(StoryActivity.this, "Bạn đã đi đến cuối trang");
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idTheLoai", String.valueOf(idMaTheLoaiSachStory));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }



    private void ActionToolbar() {
        setSupportActionBar(toolbarStory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarStory.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIdTheLoaiSach() {
        idMaTheLoaiSachStory = getIntent().getIntExtra("MaTheLoai", -1);
//        Log.d("giatritheloaisachStory", idMaTheLoaiSachStory+"");
    }

    private void AnhXa() {
        toolbarStory = (Toolbar) findViewById(R.id.toolbarSachStory);
        lvStory = (ListView) findViewById(R.id.lvSachStory);
        mangSachStory = new ArrayList<>();
        StoryAdapter = new StoryAdapter( StoryActivity.this, mangSachStory);
        lvStory.setAdapter(StoryAdapter);
        LayoutInflater inflater =(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerView = inflater.inflate(R.layout.progressbar, null);
        mHandler = new StoryActivity.mHandler();
    }

    public class mHandler extends Handler
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case 0:
                    lvStory.addFooterView(footerView);
                    break;
                case 1:
                    page++;
                    GetData(page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public class ThreadData extends Thread
    {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }
}
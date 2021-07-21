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

import adapter.LifeSkillAdapter;
import model.Sach;
import ulti.CheckConnection;
import ulti.Server;

public class LifeSkillsActivity extends AppCompatActivity {

    Toolbar toolbarLifeSkill;
    ListView lvLifeSkill;
    LifeSkillAdapter LifeSkillAdapter;
    ArrayList<Sach> mangSachLifeSkill;
    int idMaTheLoaiSachLifeSkill = 0;
    int page = 1;
    View footerView;
    boolean isLoading = false;
    boolean limitData = false;
    LifeSkillsActivity.mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_skills);

        if(CheckConnection.haveNetworkConnection(LifeSkillsActivity.this))
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
            CheckConnection.ShowToast_Short(LifeSkillsActivity.this, "Check your connection!");
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
                Intent intent = new Intent(LifeSkillsActivity.this, CartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void ClickItem()
    {
        lvLifeSkill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LifeSkillsActivity.this, BookDetailsActivity.class);
                intent.putExtra("thongtinsach", mangSachLifeSkill.get(position));
                startActivity(intent);
            }
        });
    }

    private void LoadMoreData() {
        lvLifeSkill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LifeSkillsActivity.this, BookDetailsActivity.class);
                intent.putExtra("thongtinsach", mangSachLifeSkill.get(position));
                startActivity(intent);
            }
        });
        lvLifeSkill.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && isLoading == false && limitData == false )
                {
                    isLoading = true;
                    LifeSkillsActivity.ThreadData threadData = new LifeSkillsActivity.ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(LifeSkillsActivity.this);
        String duongdan = Server.DuongdanSachLS+String.valueOf(Page);
        //String duongdan = "http://192.168.1.5:8080/server/getAllSachTheoTheLoai.php?page="+Page;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int idLifeSkill = 0;
                String tenLifeSkill = "";
                String hinhLifeSkill = "";
                //String tenTGLifeSkill = "";
                String motaLifeSkill = "";
                int giaLifeSkill = 0;
                int idTGLifeSkill = 0;
                int idtheloaiLifeSkill = 0;
                if(response != null && response.length() != 2)
                {
                    lvLifeSkill.removeFooterView(footerView);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            idLifeSkill = jsonObject.getInt("MaSach");
                            tenLifeSkill = jsonObject.getString("TenSach");
                            hinhLifeSkill = jsonObject.getString("HinhAnh");
                            motaLifeSkill = jsonObject.getString("MoTa");
                            idtheloaiLifeSkill = jsonObject.getInt("MaTheLoai");
                            idTGLifeSkill = jsonObject.getInt("MaTacGia");
                            giaLifeSkill = jsonObject.getInt("GiaBan");
                            mangSachLifeSkill.add(new Sach(idLifeSkill, tenLifeSkill, hinhLifeSkill, motaLifeSkill, idtheloaiLifeSkill, idTGLifeSkill, giaLifeSkill));
                            LifeSkillAdapter.notifyDataSetChanged();
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    limitData = true;
                    lvLifeSkill.removeFooterView(footerView);
                    CheckConnection.ShowToast_Short(LifeSkillsActivity.this, "Bạn đã đi đến cuối trang");
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
                param.put("idTheLoai", String.valueOf(idMaTheLoaiSachLifeSkill));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }



    private void ActionToolbar() {
        setSupportActionBar(toolbarLifeSkill);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLifeSkill.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIdTheLoaiSach() {
        idMaTheLoaiSachLifeSkill = getIntent().getIntExtra("MaTheLoai", -1);
//        Log.d("giatritheloaisachLifeskill", idMaTheLoaiSachLifeSkill+"");
    }

    private void AnhXa() {
        toolbarLifeSkill = (Toolbar) findViewById(R.id.toolbarLifeSkills);
        lvLifeSkill = (ListView) findViewById(R.id.lvSachLifeSkills);
        mangSachLifeSkill = new ArrayList<>();
        LifeSkillAdapter = new LifeSkillAdapter( LifeSkillsActivity.this, mangSachLifeSkill);
        lvLifeSkill.setAdapter(LifeSkillAdapter);
        LayoutInflater inflater =(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerView = inflater.inflate(R.layout.progressbar, null);
        mHandler = new LifeSkillsActivity.mHandler();
    }

    public class mHandler extends Handler
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case 0:
                    lvLifeSkill.addFooterView(footerView);
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
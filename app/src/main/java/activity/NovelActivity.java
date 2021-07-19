package activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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

import adapter.NovelAdapter;
import model.Sach;
import ulti.CheckConnection;

public class NovelActivity extends AppCompatActivity {

    Toolbar toolbarNovel;
    ListView lvNovel;
    NovelAdapter novelAdapter;
    ArrayList<Sach> mangSachNovel;
    int idMaTheLoaiSachNovel = 0;
    int page = 1;
    View footerView;
    boolean isLoading = false;
    boolean limitData = false;
    mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel);
        AnhXa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
        {
            GetIdTheLoaiSach();
            ActionToolbar();
            GetData(page);
            //LoadMoreData();
            ClickItem();
        }
        else
        {
            CheckConnection.ShowToast_Short(getApplicationContext(), "Check your connection!");
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
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void ClickItem()
    {
        lvNovel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), BookDetailsActivity.class);
                intent.putExtra("thongtinsach", mangSachNovel.get(position));
                startActivity(intent);
            }
        });
    }

    private void LoadMoreData() {
        lvNovel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), BookDetailsActivity.class);
                intent.putExtra("thongtinsach", mangSachNovel.get(position));
                startActivity(intent);
            }
        });
        lvNovel.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if((firstVisibleItem + visibleItemCount) == totalItemCount && totalItemCount != 0 && isLoading == false && limitData == false )
                {
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //String duongdan = Server.DuongdanSachNovel+String.valueOf(Page);
        String duongdan = "http://192.168.1.14:8080/server/getAllSachTheoTheLoai.php?page="+Page;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String tenNovel = "";
                String hinhNovel = "";
                //String tenTGNovel = "";
                String motaNovel = "";
                int giaNovel = 0;
                int idTG = 0;
                int idtheloai = 0;
                if(response != null && response.length() >= 2)
                {
                    lvNovel.removeFooterView(footerView);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("MaSach");
                            tenNovel = jsonObject.getString("TenSach");
                            hinhNovel = jsonObject.getString("HinhAnh");
                            motaNovel = jsonObject.getString("MoTa");
                            idtheloai = jsonObject.getInt("MaTheLoai");
                            idTG = jsonObject.getInt("MaTacGia");
                            giaNovel = jsonObject.getInt("GiaBan");
                            mangSachNovel.add(new Sach(id, tenNovel, hinhNovel, motaNovel, idtheloai, idTG, giaNovel));
                            novelAdapter.notifyDataSetChanged();
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    limitData = true;
                    lvNovel.removeFooterView(footerView);
                    CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn đã đi đến cuối trang");
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
                param.put("idTheLoai", String.valueOf(idMaTheLoaiSachNovel));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }



    private void ActionToolbar() {
        setSupportActionBar(toolbarNovel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarNovel.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIdTheLoaiSach() {
        idMaTheLoaiSachNovel = getIntent().getIntExtra("MaTheLoai", -1);
        Log.d("giatritheloaisach", idMaTheLoaiSachNovel+"");
    }

    private void AnhXa() {
        toolbarNovel = (Toolbar) findViewById(R.id.toolbarSachNovel);
        lvNovel = (ListView) findViewById(R.id.lvSachNovel);
        mangSachNovel = new ArrayList<>();
        novelAdapter = new NovelAdapter( getApplicationContext(), mangSachNovel);
        lvNovel.setAdapter(novelAdapter);
        LayoutInflater inflater =(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerView = inflater.inflate(R.layout.progressbar, null);
        mHandler = new mHandler();
    }

    public class mHandler extends Handler
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case 0:
                    lvNovel.addFooterView(footerView);
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
package activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.baitapandroid.R;

import java.text.DecimalFormat;

import adapter.CartAdapter;

public class CartActivity extends AppCompatActivity {

    ListView lvGioHang;
    TextView txtThongBao;
    static TextView txtTongTien;
    Button btnThanhToan, btnTiepTuc;
    Toolbar toolbarGioHang;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        AnhXa();
        ActionToolbar();
        CheckData();
        EventUltil();
        CatchOnItemListView();
    }

    private void CatchOnItemListView() {
        lvGioHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Delete book");
                builder.setMessage("Do you want to delete this book?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.mangGioHang.size() <= 0)
                        {
                            txtThongBao.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            MainActivity.mangGioHang.remove(position);
                            cartAdapter.notifyDataSetChanged();
                            EventUltil();
                            if(MainActivity.mangGioHang.size() <= 0)
                            {
                                txtThongBao.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                txtThongBao.setVisibility(View.INVISIBLE);
                                cartAdapter.notifyDataSetChanged();
                                EventUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cartAdapter.notifyDataSetChanged();
                        EventUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EventUltil() {
        long tongtien = 0;
        for(int i = 0; i < MainActivity.mangGioHang.size(); i++)
        {
            tongtien += MainActivity.mangGioHang.get(i).getGiaSach();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTongTien.setText(decimalFormat.format(tongtien) + " VNĐ");
    }

    private void CheckData() {
        if(MainActivity.mangGioHang.size() <= 0)
        {
            cartAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.VISIBLE);
            lvGioHang.setVisibility(View.INVISIBLE);
        }
        else
        {
            cartAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.INVISIBLE);
            lvGioHang.setVisibility(View.VISIBLE);
        }
    }


    private void AnhXa() {
        lvGioHang = (ListView) findViewById(R.id.lvGioHang);
        txtThongBao = (TextView) findViewById(R.id.tvThongBao);
        txtTongTien = (TextView) findViewById(R.id.tvGiaTri);
        btnThanhToan = (Button) findViewById(R.id.btnPayment);
        btnTiepTuc = (Button) findViewById(R.id.btnContinue);
        toolbarGioHang = (Toolbar) findViewById(R.id.toolbarCart);
        cartAdapter = new CartAdapter(getApplicationContext(), MainActivity.mangGioHang);
        lvGioHang.setAdapter(cartAdapter);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarGioHang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarGioHang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
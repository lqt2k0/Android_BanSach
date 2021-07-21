package activity;

import android.content.DialogInterface;
import android.content.Intent;
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
import ulti.CheckConnection;

public class CartActivity extends AppCompatActivity {

    ListView lvGioHang_Cart;
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
        EventButton();
    }

    private void EventButton() {
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.mangGioHang.size() > 0)
                {
                    Intent intent = new Intent(CartActivity.this, ConfirmActivity.class);
                    startActivity(intent);
                }
                else
                {
                    CheckConnection.ShowToast_Short(CartActivity.this, "Empty cart!");
                }
            }
        });
    }

    private void CatchOnItemListView() {
        lvGioHang_Cart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this, R.style.MyAlertDialogStyle);
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

//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        cartAdapter.notifyDataSetChanged();
//                        EventUltil();
//                    }
//                });
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
            lvGioHang_Cart.setVisibility(View.INVISIBLE);
        }
        else
        {
            cartAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.INVISIBLE);
            lvGioHang_Cart.setVisibility(View.VISIBLE);
        }
    }


    private void AnhXa() {
        lvGioHang_Cart = (ListView) findViewById(R.id.lvGioHang);
        txtThongBao = (TextView) findViewById(R.id.tvThongBao);
        txtTongTien = (TextView) findViewById(R.id.tvGiaTri);
        btnThanhToan = (Button) findViewById(R.id.btnPayment);
        btnTiepTuc = (Button) findViewById(R.id.btnContinue);
        toolbarGioHang = (Toolbar) findViewById(R.id.toolbarCart);
        cartAdapter = new CartAdapter(CartActivity.this, MainActivity.mangGioHang);
        lvGioHang_Cart.setAdapter(cartAdapter);

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
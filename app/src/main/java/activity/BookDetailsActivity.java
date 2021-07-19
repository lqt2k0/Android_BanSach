package activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.baitapandroid.R;

import java.text.DecimalFormat;

import model.GioHang;
import model.Sach;

public class BookDetailsActivity extends AppCompatActivity {

    Toolbar toolBarDetails;
    ImageView imgDetails;
    RatingBar ratingBar;
    TextView txtName, txtPrice, txtDetails;
    Spinner spinner;
    //EditText edtSoluong;
    Button butAdd;
    int id = 0;
    String tenChiTiet = "";
    int giaChiTiet = 0;
    String hinhAnh = "";
    String moTa = "";
    int idLoai = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        AnhXa();
        ActionToolbar();
        GetInformation();
        CatchEvenSpinner();
        EventButton();
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

    private void EventButton() {
        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainActivity.mangGioHang.size() > 0)
                {
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exist = false;
                    for(int i = 0; i < MainActivity.mangGioHang.size(); i++)
                    {
                        if(MainActivity.mangGioHang.get(i).getIdSach() == id)
                        {
                            MainActivity.mangGioHang.get(i).setSoLuong((MainActivity.mangGioHang.get(i).getSoLuong()+sl));
                            if(MainActivity.mangGioHang.get(i).getSoLuong() >= 10)
                            {
                                MainActivity.mangGioHang.get(i).setSoLuong(10);
                            }
                            MainActivity.mangGioHang.get(i).setGiaSach(giaChiTiet * MainActivity.mangGioHang.get(i).getSoLuong());
                            exist = true;
                        }
                    }
                    if(exist == false)
                    {
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long giamoi = soluong * giaChiTiet;
                        MainActivity.mangGioHang.add(new GioHang(id, tenChiTiet, giamoi, hinhAnh, soluong));
                    }
                }
                else
                {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long giamoi = soluong * giaChiTiet;
                    MainActivity.mangGioHang.add(new GioHang(id, tenChiTiet, giamoi, hinhAnh, soluong));
                }
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEvenSpinner() {
        Integer[] soluong = new Integer[]{1,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetInformation() {

        Sach sach = (Sach) getIntent().getSerializableExtra("thongtinsach");
        id = sach.getMaSach();
        tenChiTiet = sach.getTenSach();
        hinhAnh = sach.getHinhAnh();
        moTa = sach.getMoTa();
        giaChiTiet = sach.getGiaBan();
        idLoai = sach.getMaTheLoai();
        txtName.setText(tenChiTiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtPrice.setText("Price : " + decimalFormat.format(sach.getGiaBan()) + " VNĐ");
        txtDetails.setText((moTa));
        Glide.with(getApplicationContext()).load(sach.getHinhAnh()).placeholder(R.drawable.no_image).error(R.drawable.error).into(imgDetails);
        ratingBar.setRating((float) 4.5);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolBarDetails);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarDetails.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolBarDetails = (Toolbar) findViewById(R.id.toolbarDetailSach);
        imgDetails = (ImageView) findViewById(R.id.imgSachDetail);
        txtName = (TextView) findViewById(R.id.tvTenSachDetail);
        txtPrice = (TextView) findViewById(R.id.tvGiaBanDetail);
        txtDetails = (TextView) findViewById(R.id.tvMoTaDetail);
        ratingBar = (RatingBar) findViewById(R.id.ratingBarDetail);
        spinner = (Spinner) findViewById(R.id.spinner);
        //edtSoluong = (EditText) findViewById(R.id.edtSL);
        butAdd = (Button) findViewById(R.id.btnAddToCart);
    }
}
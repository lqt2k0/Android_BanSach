package activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ulti.CheckConnection;
import ulti.Server;

public class ConfirmActivity extends AppCompatActivity {

    EditText dateDeli, edtEmail;
    TextView dateOrder;
    DatePickerDialog datePickerDialog;
    Button btnConfirm, btnBack;
    static TextView txtTongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        AnhXa();
        setDate(dateOrder);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
        {
            EventButton();
        }
        else 
        {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Check your connection!");
        }
    }

    private void EventButton() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = edtEmail.getText().toString().trim();
                final String ngaydat = dateOrder.getText().toString().trim();
                final String ngaygiao = dateDeli.getText().toString().trim();
                if(email.length() > 0 && ngaygiao.length() > 0)
                {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.DuongdanHD, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String macthd) {
//                            if(CheckConnection.haveNetworkConnection(getApplicationContext()))
//                            {
//                                Intent intent = new Intent(ConfirmActivity.this, PaymentSuccessful.class);
//                                startActivity(intent);
//                            }
//                            else
//                            {
//                                CheckConnection.ShowToast_Short(getApplicationContext(), "Check your connection" );
//                            }

                            Log.d("mahoadon", macthd);
                            if(Integer.parseInt(macthd) > 0)
                            {
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, Server.DuongdanCTHD, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("1"))
                                        {
                                            MainActivity.mangGioHang.clear();
                                            Intent intent = new Intent(ConfirmActivity.this, PaymentSuccessful.class);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            CheckConnection.ShowToast_Short(getApplicationContext(), "Check your confirm payment" );
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
                                        JSONArray jsonArray = new JSONArray();
                                        for(int i = 0; i < MainActivity.mangGioHang.size(); i++)
                                        {
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("MaHD", macthd);
                                                jsonObject.put("MaSach", MainActivity.mangGioHang.get(i).getIdSach());
                                                jsonObject.put("TenSach", MainActivity.mangGioHang.get(i).getTenSach());
                                                jsonObject.put("SoLuong", MainActivity.mangGioHang.get(i).getSoLuong());
                                                jsonObject.put("DonGia", MainActivity.mangGioHang.get(i).getGiaSach());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String, String> hashMap = new HashMap<String, String>();
                                        hashMap.put("json", jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
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
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("email", email);
                            hashMap.put("ngaydat", ngaydat);
                            hashMap.put("ngaygiao", ngaygiao);
                            long tongtien = 0;
                            for(int i = 0; i < MainActivity.mangGioHang.size(); i++)
                            {
                                tongtien += MainActivity.mangGioHang.get(i).getGiaSach();
                            }
                            hashMap.put("tongtien", String.valueOf(tongtien));
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
                else
                {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Check your confirm payment");
                }
            }
        });
    }


    private void AnhXa() {

        edtEmail = (EditText) findViewById(R.id.edtEmailPayment);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        txtTongTien = (TextView) findViewById(R.id.tvGiaTri);
        // initiate the date picker and a button
        dateOrder = (TextView) findViewById(R.id.orderDate);
        dateDeli = (EditText) findViewById(R.id.deliveryDate);
        // perform click event on edit text
        dateDeli.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year

                //c.setTime(new SimpleDateFormat("MMM").parse("July"));
                int mMonth = c.get(Calendar.MONTH) + 1;
                //String mMonth = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                // SimpleDateFormat mMonth = new SimpleDateFormat("MMM");
                // String Month_name = mMonth.format(c);

                // date picker dialog
                datePickerDialog = new DatePickerDialog(ConfirmActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth){
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                String dateString = format.format(calendar.getTime());
                                dateDeli.setText(dateString);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();

            }
        });
    }

    public void setDate (TextView view){
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//formating according to my need
        String date = formatter.format(today);
        view.setText(date);
    }
}
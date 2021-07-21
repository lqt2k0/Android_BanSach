package activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.baitapandroid.LoginActivity;
import com.example.baitapandroid.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

import adapter.ProfileAdapter;
import model.Khach;
import ulti.CheckConnection;

public class ProfileActivity extends AppCompatActivity {
    EditText userName, userEmail, userPhone, userAddress, userGender;
    Button editProfile, changePass, btnlogout;
    Toolbar toolbarUserProfile;
    CircularImageView imgUserProfile;
    String emailKH = "";
    int page = 1;
    ArrayList<Khach> arraykhach;
    ProfileAdapter profileAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AnhXa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
        {
            ActionToolbar();
            //GetEmailKH();
            //GetDataKH(page);
        }
        else
        {
            CheckConnection.ShowToast_Short(getApplicationContext(), "Check your connection!");
            finish();
        }
    }

//    private void GetDataKH(int Page) {
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        String duongdan = "http://192.168.1.14:8080/server/getKH.php?page="+Page;
//        //String duongdan = Server.DuongdanKH;
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                String idEmail = "";
//                String tenkhach = "";
//                String dtkhach = "";
//                String gioitinhkhach = "";
//                String diachikhach = "";
//                String hinhanhkhach = "";
//                try {
//                    JSONArray jsonArray = new JSONArray(response);
//                    for(int i = 0; i < jsonArray.length(); i++)
//                    {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        idEmail = jsonObject.getString("EmailKH");
//                        tenkhach = jsonObject.getString("HoTenKH");
//                        dtkhach = jsonObject.getString("DienThoaiKH");
//                        gioitinhkhach = jsonObject.getString("GioiTinh");
//                        diachikhach = jsonObject.getString("DiaChiKH");
//                        hinhanhkhach = jsonObject.getString("HinhAnhKH");
//                        arraykhach.add(new Khach(idEmail, tenkhach, dtkhach, gioitinhkhach, diachikhach, hinhanhkhach));
//                        profileAdapter.notifyDataSetChanged();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
////
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        })
//        {
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> param = new HashMap<String, String>();
//                param.put("emailKH", String.valueOf(emailKH));
//                return param;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }

//    private void GetEmailKH() {
//        emailKH = getIntent().getStringExtra("EmailKH");
//        Log.d("giatrikh", emailKH+"");
//    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarUserProfile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarUserProfile.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarUserProfile = (Toolbar) findViewById(R.id.toolbarProfile);

        userName = (EditText) findViewById(R.id.edtUserProfile);
        userEmail = (EditText) findViewById(R.id.edtEmailProfile);
        userPhone = (EditText) findViewById(R.id.edtPhoneProfile);
        userAddress = (EditText) findViewById(R.id.edtAddressProfile);
        userGender = (EditText) findViewById(R.id.edtGenderProfile);

        imgUserProfile = (CircularImageView) findViewById(R.id.imgUserProfile);

        editProfile = (Button) findViewById(R.id.btnEditUserProfile);
        changePass = (Button) findViewById(R.id.btnChangePass);

        btnlogout = (Button) findViewById(R.id.btnLogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getResources().getString(R.string.prefLoginState),"loggedout");
                editor.apply();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                finish();

            }
        });

//        if(!SharedPrefManager.getInstance(this).isLoggedIn())
//        {
//            finish();
//            startActivity(new Intent(this, LoginActivity.class));
//        }

//        userName.setText(SharedPrefManager.getInstance(this).getUsername());
//        userEmail.setText(SharedPrefManager.getInstance(this).getUseremail());
//        //userAddress.setText(mDiaChi);
//        userAddress.setText(SharedPrefManager.getInstance(this).getUseraddress());
//        userGender.setText(SharedPrefManager.getInstance(this).getUsergender());


        Intent i = getIntent();
        String mName = i.getStringExtra("username");
        String mEmail = i.getStringExtra("email");
        //String mDiaChi = i.getStringExtra("DiaChiKH");
        String mPhone = i.getStringExtra("mobile");
        String mGender = i.getStringExtra("gender");

//        userName.setText(mName);
//        userEmail.setText(mEmail);
//        //userAddress.setText(mDiaChi);
//        userPhone.setText(mPhone);
//        userGender.setText(mGender);

    }
}
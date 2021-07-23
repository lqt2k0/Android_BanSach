package activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.baitapandroid.R;

import ulti.CheckConnection;

public class NotificationActivity extends AppCompatActivity {


    Toolbar toolbarNoti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        AnhXa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
        {
            ActionToolbar();
        }
        else
        {
            CheckConnection.ShowToast_Short(getApplicationContext(), "Check your connection!");
            finish();
        }
    }

    private void AnhXa() {
        toolbarNoti = findViewById(R.id.toolbarNoti);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarNoti);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarNoti.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
package activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.baitapandroid.R;

import ulti.CheckConnection;

public class ContactActivity extends AppCompatActivity {
    VideoView videoView;
    Toolbar toolbarContacts;
    Button btnCall, btnEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
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
        videoView = findViewById(R.id.videoContacts);
        String videoPath = "android.resource://" + getPackageName()+"/" + R.raw.videocontact;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        toolbarContacts = findViewById(R.id.toolbarContacts);

        btnCall = (Button) findViewById(R.id.btnContact);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0377889760"));
                startActivity(intent);
            }
        });
        btnEmail = (Button) findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:wintrung01@gmail.com"));
                startActivity(intent);
            }
        });
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarContacts);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarContacts.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
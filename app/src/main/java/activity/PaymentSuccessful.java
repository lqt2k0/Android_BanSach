package activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitapandroid.R;

public class PaymentSuccessful extends AppCompatActivity {

    private  static  final int REQUEST_CALL = 1;

    Button btnHomePS, btnContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successful);
        AnhXa();
    }

    private void AnhXa() {
        btnContacts = (Button) findViewById(R.id.btnContactPS);
        btnHomePS = (Button) findViewById(R.id.btnHomePS);
        btnHomePS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0377889760"));
                startActivity(intent);
            }
        });
    }
}
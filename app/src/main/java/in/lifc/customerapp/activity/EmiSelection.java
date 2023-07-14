package in.lifc.customerapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import in.lifc.customerapp.R;

public class EmiSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi_selection);
        Button btn_Paynow = findViewById(R.id.btn_paynow);
        btn_Paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmiSelection.this,PayNow.class);
                startActivity(intent);
            }
        });
    }
}
package in.lifc.customerapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import in.lifc.customerapp.R;

public class CurrentDues extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_dues);
        Button btn_Emiselection = findViewById(R.id.btn_emi_selection);
        btn_Emiselection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CurrentDues.this,EmiSelection.class);
                startActivity(intent);
            }
        });}
}
package in.lifc.customerapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import in.lifc.customerapp.R;
public class ChooseLanguageActivity extends AppCompatActivity {
    private AppCompatButton btnHindi, btnEnglish,btnLetsgo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);
         btnHindi = findViewById(R.id.btn_hindi);
         btnEnglish = findViewById(R.id.btn_english);
         btnEnglish.setOnClickListener(v -> {
          //  Intent intent = new Intent(ChooseLanguageActivity.this, Dashboard_Customer.class);
            //startActivity(intent);
            btnHindi.setBackgroundResource(R.drawable.rounded_button);
            btnEnglish.setBackgroundResource(R.drawable.roundedbutton2);
         });
        btnHindi.setOnClickListener(v -> {
        //    Intent intent = new Intent(ChooseLanguageActivity.this,Dashboard_Customer.class);
          //  startActivity(intent);
            btnHindi.setBackgroundResource(R.drawable.roundedbutton2);
            btnEnglish.setBackgroundResource(R.drawable.rounded_button);
        });
        btnLetsgo = findViewById(R.id.btn_lets_go);
        btnLetsgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ChooseLanguageActivity.this,Dashboard_Customer.class);
                startActivity(intent);
            }
        });

    }
}
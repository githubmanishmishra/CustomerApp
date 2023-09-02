package in.lifc.customerapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import in.lifc.customerapp.R;
import in.lifc.customerapp.model.AboutModel;
import in.lifc.customerapp.retrofitservices.ApiClient;
import in.lifc.customerapp.retrofitservices.ApiService;
import in.lifc.customerapp.savedata.PrefConfig;
import retrofit2.Call;
import retrofit2.Callback;

public class Aboutus extends AppCompatActivity {
    TextView tvAbout;
    private PrefConfig prefConfig;
    String About;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        ImageView iv_back = findViewById(R.id.iv_back);
        tvAbout = findViewById(R.id.tv_about);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        prefConfig = new PrefConfig(this);
        getAboutus();
    }

    private void getAboutus() {
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<AboutModel> call = service.getAbout("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<AboutModel>() {

                         @Override
                         public void onResponse(@NonNull Call<AboutModel> call, @NonNull retrofit2.Response<AboutModel> response) {

                             final AboutModel allEvent = response.body();
                           //  About = tvAbout.getText().toString();
                             About = allEvent.getData().get(0).getAboutUs();

                             tvAbout.setText(About);

                         }

                         @Override
                         public void onFailure(Call<AboutModel> call, Throwable t) {

                         }


                     }


        );
    }

}
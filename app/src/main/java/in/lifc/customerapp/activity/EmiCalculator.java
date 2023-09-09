package in.lifc.customerapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import in.lifc.customerapp.R;
import in.lifc.customerapp.model.CustomerServiceRequest;
import in.lifc.customerapp.model.EmiCalModel;
import in.lifc.customerapp.retrofitservices.ApiClient;
import in.lifc.customerapp.retrofitservices.ApiService;
import in.lifc.customerapp.savedata.PrefConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmiCalculator extends AppCompatActivity {
    private TextInputEditText etLoanAmount,etRateofIntrest,etTotalNumberMonth;
    private PrefConfig prefConfig;
    ApiService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi_calculator);
        prefConfig = new PrefConfig(this);
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        etLoanAmount = findViewById(R.id.loan_amounts);
        etRateofIntrest = findViewById(R.id.et_roi);
        etTotalNumberMonth = findViewById(R.id.total_number_month);
        AppCompatButton btnCalculate = findViewById(R.id.btn_calculate);

    btnCalculate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            String LoanAmount = etLoanAmount.getText().toString();
            String Roi = etRateofIntrest.getText().toString();
            String Tenure = etTotalNumberMonth.getText().toString();
            emiCalculator(LoanAmount,Roi,Tenure);
        }
    });
    }
    private void emiCalculator(String loanAmount,String roi,String tenure )
    {
        Log.d("sgdhgjkk>>>>>",prefConfig.readToken()+"1 "+loanAmount+"2 "+roi+"3 "+tenure+"4 ");
        int loanAmounts = Integer.parseInt(loanAmount);
        int rois = Integer.parseInt(roi);
        int ternuress = Integer.parseInt(tenure);
        getRetrofitServiceWork();
     //   ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<EmiCalModel> call = service.emiCalculator(loanAmounts,rois,ternuress);
        call.enqueue(new Callback<EmiCalModel>() {
            @Override
            public void onResponse(@NonNull Call<EmiCalModel> call, @NonNull Response<EmiCalModel> response) {
                if (response.body() != null)
                {

                   // if(response.body().getMessage().equalsIgnoreCase("EMI Amount")) {
                    Toast.makeText(EmiCalculator.this, ""+response.body().getData(), Toast.LENGTH_SHORT).show();
                    //    finish();

                //    }else {
                 //       Toast.makeText(EmiCalculator.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                 //   }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<EmiCalModel> call, Throwable t) {

                // pDialog.dismiss();
                  Log.d("Errorerty", t.getMessage());
            }
        });

    }
    private void getRetrofitServiceWork() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(
                        chain -> {
                            okhttp3.Request original = chain.request();
                            // Request customization: add request headers
                            okhttp3.Request.Builder requestBuilder = original.newBuilder()
                                    .addHeader("Authorization", "Bearer " + prefConfig.readToken())
                                    .method(original.method(), original.body());
                            okhttp3.Request request = requestBuilder.build();
                            return chain.proceed(request);
                        })
                .addInterceptor(interceptor).connectTimeout(60, TimeUnit.SECONDS).
                readTimeout(60, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://customerapp.iqhertz.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        service = retrofit.create(ApiService.class);
    }

}

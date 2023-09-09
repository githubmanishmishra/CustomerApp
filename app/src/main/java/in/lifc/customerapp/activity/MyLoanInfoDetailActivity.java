package in.lifc.customerapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import in.lifc.customerapp.R;
import in.lifc.customerapp.model.MyLoanInfoModel;
import in.lifc.customerapp.retrofitservices.ApiClient;
import in.lifc.customerapp.retrofitservices.ApiService;
import in.lifc.customerapp.savedata.PrefConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyLoanInfoDetailActivity extends AppCompatActivity {
    private TextView etLoanProduct, etLoanNumber, etDisbursementStatus, etLoanAmount,
            etTotalEmiPaid, etTenure, etDueEmiDate;
    private PrefConfig prefConfig;

    ApiService service;

    List<MyLoanInfoModel.Datum> myLoanInfoModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_loan_info_detail);

        prefConfig = new PrefConfig(this);
        ImageView iv_back = findViewById(R.id.iv_back);
        getLoanType();
        etLoanProduct = findViewById(R.id.ev_loan_product);
        etLoanNumber = findViewById(R.id.ev_loan_number);
        etDisbursementStatus = findViewById(R.id.ev_disburse_status);
        etLoanAmount = findViewById(R.id.ev_loan_amount);
        etTotalEmiPaid = findViewById(R.id.ev_emi_paid);
        etTenure = findViewById(R.id.ev_tenure);
        etDueEmiDate = findViewById(R.id.ev_due_emi_date);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getLoanType() {
        ApiService service = ApiClient.getClient().create(ApiService.class);
//        getRetrofitServiceWork();
        Call<MyLoanInfoModel> call = service.getLoanInfo("Bearer "+prefConfig.readToken());
        call.enqueue(new Callback<MyLoanInfoModel>() {
            @Override
            public void onResponse(@NonNull Call<MyLoanInfoModel> call, @NonNull retrofit2.Response<MyLoanInfoModel> response) {

                final MyLoanInfoModel allEvent = response.body();
                if (allEvent != null) {
                    for (int i = 0; i < allEvent.getData().size(); i++) {
                        myLoanInfoModelList = allEvent.getData();

                    }
                } else {
                    Toast.makeText(MyLoanInfoDetailActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<MyLoanInfoModel> call, @NonNull Throwable t) {

                // pDialog.dismiss();
                Log.d("Error", t.getMessage());
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


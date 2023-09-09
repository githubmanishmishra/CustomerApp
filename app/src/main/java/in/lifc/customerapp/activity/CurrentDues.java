package in.lifc.customerapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import in.lifc.customerapp.R;
import in.lifc.customerapp.adapters.CurrentDuesAdapter;
import in.lifc.customerapp.model.EmiDueModel;
import in.lifc.customerapp.retrofitservices.ApiClient;
import in.lifc.customerapp.retrofitservices.ApiService;
import in.lifc.customerapp.savedata.PrefConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrentDues extends AppCompatActivity {
    PrefConfig prefConfig;
    private RecyclerView recyclerView;
    CurrentDuesAdapter adapter;
    ApiService service;
    List<EmiDueModel.Datum> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emi_due_recycler);
        prefConfig = new PrefConfig(this);
        getCustomerListData();
        recyclerView = findViewById(R.id.rv_emi_due);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        ImageView iv_back = findViewById(R.id.back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getCustomerListData() {
        ApiService service = ApiClient.getClient().create(ApiService.class);
//        getRetrofitServiceWork();
        Call<EmiDueModel> call = service.getEmiDue("Bearer "+prefConfig.readToken());
        call.enqueue(new Callback<EmiDueModel>() {
            @Override
            public void onResponse(@NonNull Call<EmiDueModel> call,
                                   @NonNull retrofit2.Response<EmiDueModel> response) {
                if (response.body() != null) {

                    Log.d("CustomerList", "" + response.body().getData());

                    dataList = response.body().getData();

                    for (int i = 0; i < dataList.size(); i++) {
                        dataList = response.body().getData();
                        Log.d("kjxngksjnkjsdn", dataList.toString());


                      //  Log.d("Manish", "" + dataList.get(i).getCustomerName());

                    }
                    adapter = new CurrentDuesAdapter(dataList, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }
                else
                {
                    Toast.makeText(CurrentDues.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<EmiDueModel> call, @NonNull Throwable t) {

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
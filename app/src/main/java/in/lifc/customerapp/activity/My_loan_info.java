package in.lifc.customerapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.lifc.customerapp.R;
import in.lifc.customerapp.adapters.MyLoanInfoAdapter;
import in.lifc.customerapp.model.MyLoanInfoModel;
import in.lifc.customerapp.retrofitservices.ApiClient;
import in.lifc.customerapp.retrofitservices.ApiService;
import in.lifc.customerapp.savedata.PrefConfig;
import retrofit2.Call;
import retrofit2.Callback;

public class My_loan_info extends AppCompatActivity {
    private PrefConfig prefConfig;
    List<MyLoanInfoModel.Datum> myLoanInfoModelList = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_loan_info);

        prefConfig = new PrefConfig(this);

        recyclerView = findViewById(R.id.rv_loan_info);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(My_loan_info.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.addItemDecoration(new DividerItemDecoration(My_loan_info.this, DividerItemDecoration.VERTICAL));
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        ImageView iv_back = findViewById(R.id.iv_back);

        getLoanInfo();


        iv_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

    private void getLoanInfo() {
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<MyLoanInfoModel> call = service.getLoanInfo("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<MyLoanInfoModel>() {
            @Override
            public void onResponse(@NonNull Call<MyLoanInfoModel> call, @NonNull retrofit2.Response<MyLoanInfoModel> response) {

                final MyLoanInfoModel allEvent = response.body();
                if (allEvent != null) {
                    for (int i = 0; i < allEvent.getData().size(); i++)
                    {
                        myLoanInfoModelList = allEvent.getData();

                    }

                    MyLoanInfoAdapter mMailAdapter = new MyLoanInfoAdapter(myLoanInfoModelList);
                    recyclerView.setAdapter(mMailAdapter);
                }
                else
                {
                    Toast.makeText(My_loan_info.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<MyLoanInfoModel> call, @NonNull Throwable t) {

                // pDialog.dismiss();
                Log.d("Error>>>>>>>>>>>>>", t.getMessage());
            }
        });
    }

}


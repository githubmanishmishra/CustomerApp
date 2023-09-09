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
import in.lifc.customerapp.adapters.LoanTypeAdapter;
import in.lifc.customerapp.model.LoanTypeModel;
import in.lifc.customerapp.retrofitservices.ApiClient;
import in.lifc.customerapp.retrofitservices.ApiService;
import in.lifc.customerapp.savedata.PrefConfig;
import retrofit2.Call;
import retrofit2.Callback;

public class Loan_type extends AppCompatActivity {
    private PrefConfig prefConfig;
    List<LoanTypeModel.Data> dataList = new ArrayList<>();
    List<LoanTypeModel.Data> loanTypeList = new ArrayList<>();

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_loan_type);
        prefConfig = new PrefConfig(this);

        ImageView iv_back = findViewById(R.id.back);

        recyclerView = findViewById(R.id.rv_loan_type);
        super.onCreate(savedInstanceState);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(Loan_type.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.addItemDecoration(new DividerItemDecoration(Loan_type.this, DividerItemDecoration.VERTICAL));
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getLoanType();

    }

    private void getLoanType() {
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<LoanTypeModel> call = service.getLoanType("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<LoanTypeModel>() {
            @Override
            public void onResponse(@NonNull Call<LoanTypeModel> call, @NonNull retrofit2.Response<LoanTypeModel> response) {

                final LoanTypeModel allEvent = response.body();
                if (allEvent != null) {
                    for (int i = 0; i < allEvent.getData().size(); i++) {
                        dataList = allEvent.getData();

                    }

                    LoanTypeAdapter mMailAdapter = new LoanTypeAdapter(dataList);
                    recyclerView.setAdapter(mMailAdapter);
                }
                else {
                    Toast.makeText(Loan_type.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<LoanTypeModel> call, @NonNull Throwable t) {

                // pDialog.dismiss();
                Log.d("Error", t.getMessage());
            }
        });
    }

}
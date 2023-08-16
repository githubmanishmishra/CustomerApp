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

import java.util.ArrayList;
import java.util.List;

import in.lifc.customerapp.R;
import in.lifc.customerapp.model.MyLoanInfoModel;
import in.lifc.customerapp.retrofitservices.ApiClient;
import in.lifc.customerapp.retrofitservices.ApiService;
import in.lifc.customerapp.savedata.PrefConfig;
import retrofit2.Call;
import retrofit2.Callback;

public class MyLoanInfoDetailActivity extends AppCompatActivity {
    private TextView etLoanProduct, etLoanNumber, etDisbursementStatus, etLoanAmount,
            etTotalEmiPaid, etTenure, etDueEmiDate;
    private PrefConfig prefConfig;

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
        Call<MyLoanInfoModel> call = service.getLoanInfo("Bearer " + prefConfig.readToken());
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

}


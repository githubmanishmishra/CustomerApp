package in.lifc.customerapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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

public class NewLoanRequest extends AppCompatActivity {
  private   Spinner spLoanType;
    private PrefConfig prefConfig;
    List<LoanTypeModel.Data> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_loan_request);

        prefConfig = new PrefConfig(this);

         spLoanType  = findViewById(R.id.sp_loan_type);
        ImageView iv_back = findViewById(R.id.iv_back);

        getLoanType();
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


    }
    private void getLoanType() {
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<LoanTypeModel> call = service.getLoanType("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<LoanTypeModel>() {
            @Override
            public void onResponse(@NonNull Call<LoanTypeModel> call, @NonNull retrofit2.Response<LoanTypeModel> response) {

                final LoanTypeModel allEvent = response.body();
                List<String> listLoanType = new ArrayList<>();

                if (allEvent != null) {
                    for (int i = 0; i < allEvent.getData().size(); i++) {
                        dataList = allEvent.getData();

                        listLoanType.add(dataList.get(i).getProductName());

                    }
                    ArrayAdapter aa = new ArrayAdapter(NewLoanRequest.this,
                            android.R.layout.simple_spinner_item, listLoanType);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spLoanType.setAdapter(aa);
                    aa.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(NewLoanRequest.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

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
package in.lifc.customerapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import in.lifc.customerapp.R;
import in.lifc.customerapp.model.LoginModel;
import in.lifc.customerapp.retrofitservices.ApiClient;
import in.lifc.customerapp.retrofitservices.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class My_loan_info extends AppCompatActivity {
    EditText et_LoanProduct,et_LoanNumber,et_DisbursementStatus,et_Loanamount,et_TotalEmiPaid,et_Tenure,et_DueEmiDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_loan_info);
        ImageView iv_back = findViewById(R.id.iv_back);
        et_LoanProduct = findViewById(R.id.ev_loan_product);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getLogin(String emp_code) {
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<LoginModel> call = service.getLogin(emp_code);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                if (response.body() != null) {
            /*        Log.d("Mainsj Mishra", "" + response.body().getMessage());

                    Log.d("token>>>>", response.body().getToken());*/
                    if (response.body().getMessage().equalsIgnoreCase("OTP has been sent successfully!")) {
                        //prefConfig.writeLoginStatus(true);
                        //prefConfig.writeName(response.body().getToken());
                        Log.d("token>>>>>>>>>>>>", response.body().getToken());
                      //  Intent intents = new Intent(LoginActivity.this, Otp_Verify_Customer.class);
                     //   startActivity(intents);
                        //Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
            //        Toast.makeText(LoginActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {

                // pDialog.dismiss();
                Log.d("Error", t.getMessage());
            }
        });
    }

}
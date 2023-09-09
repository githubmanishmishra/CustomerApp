package in.lifc.customerapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import in.lifc.customerapp.R;
import in.lifc.customerapp.model.LoanRequestModel;
import in.lifc.customerapp.model.LoanTypeModel;
import in.lifc.customerapp.retrofitservices.ApiClient;
import in.lifc.customerapp.retrofitservices.ApiService;
import in.lifc.customerapp.savedata.PrefConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewLoanRequest extends AppCompatActivity {
    private AppCompatSpinner  spLoanType;
    private PrefConfig prefConfig;
    String loanTypeValue;

    ApiService service;

    private TextInputEditText etLoanAmount, etTenure, etPurposeOfLoan, etOfferAndScheme;

    List<LoanTypeModel.Data> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_loan_request);
        prefConfig = new PrefConfig(this);
        spLoanType = findViewById(R.id.sp_loan_type);
        etLoanAmount = findViewById(R.id.et_loan_amount);
        etTenure = findViewById(R.id.et_tenure);
        etPurposeOfLoan = findViewById(R.id.et_purpose_of_loan);
        etOfferAndScheme = findViewById(R.id.ev_offer_and_scheme);
        AppCompatButton btnApply = findViewById(R.id.btn_apply);
        ImageView iv_back = findViewById(R.id.iv_back);

        getLoanType();
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    onUpdateFailed();

                } else {
                    String loanAmount = etLoanAmount.getText().toString();
                    String tenure = etTenure.getText().toString();
                    String purposeOfLoan = etPurposeOfLoan.getText().toString();
                    String offerAndScheme = etOfferAndScheme.getText().toString();
                    getLoanRequest(loanAmount, tenure, purposeOfLoan, offerAndScheme);

                }

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
                listLoanType.add(0,"Select  Loan Type");
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

                    spLoanType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            loanTypeValue = spLoanType.getSelectedItem().toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
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

    private boolean validate() {
        boolean valid = true;
        String loanAmount = etLoanAmount.getText().toString();
        String tenure = etTenure.getText().toString();
        String purposeOfLoan = etPurposeOfLoan.getText().toString();
        String offerAndScheme = etOfferAndScheme.getText().toString();

        if (loanAmount.isEmpty() ) {
            etLoanAmount.setError("Enter Loan Amount ");
            requestFocus(etLoanAmount);
            valid = false;
        } else {
            etLoanAmount.setError(null);
        }
        if (tenure.isEmpty() && tenure.equalsIgnoreCase("0")) {
            etTenure.setError("Enter Tenure");
            requestFocus(etTenure);
            valid = false;

        } else
        {
            etTenure.setError(null);
        }

        if (purposeOfLoan.isEmpty()) {
            etPurposeOfLoan.setError("Enter Purpose Of Loan");
            requestFocus(etPurposeOfLoan);
            valid = false;

        }
        else {
            etPurposeOfLoan.setError(null);
        }

        if (offerAndScheme.isEmpty()) {
            etOfferAndScheme.setError("Enter Offer And Scheme");
            requestFocus(etOfferAndScheme);
            valid = false;

        }
        else {
            etOfferAndScheme.setError(null);
        }
        return valid;
    }

    private void onUpdateFailed() {
        Toast.makeText(NewLoanRequest.this, "Creating account failed", Toast.LENGTH_LONG).show();

        //  btnCreateAccount.setEnabled(true);
    }

    private void requestFocus(View view)
    {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void getLoanRequest(String loanAmount, String tenure,
                                String purposeOfLoan, String offerAndScheme) {

        Log.d("sgdhgjkk>>>>>",prefConfig.readToken()+"1 "+loanTypeValue+"2 "+loanAmount+"3 "+tenure+"4 "+purposeOfLoan+"5 "
                +offerAndScheme);
     //   ApiService service = ApiClient.getClient().create(ApiService.class);

        getRetrofitServiceWork();

        Call<LoanRequestModel> call = service.loanRequest(loanTypeValue,
                loanAmount, tenure, purposeOfLoan, offerAndScheme);
        call.enqueue(new Callback<LoanRequestModel>()
        {
            @Override
            public void onResponse(@NonNull Call<LoanRequestModel> call,
                                   @NonNull retrofit2.Response<LoanRequestModel> response)
            {
                LoanRequestModel allEvent = response.body();

                if (allEvent != null) {
                   if(allEvent.getMessage().equalsIgnoreCase("Your are successfully applied a new Loan!")) {
                    Toast.makeText(NewLoanRequest.this, allEvent.getMessage(), Toast.LENGTH_SHORT).show();
                       finish();

                   }
                   else {
                       Toast.makeText(NewLoanRequest.this, "Invalid Params", Toast.LENGTH_SHORT).show();

                   }

                } else {
                    Toast.makeText(NewLoanRequest.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<LoanRequestModel> call, @NonNull Throwable t) {

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
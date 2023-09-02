package in.lifc.customerapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;
import java.util.List;

import in.lifc.customerapp.R;
import in.lifc.customerapp.model.CustomerServiceRequest;
import in.lifc.customerapp.model.RequestDropdownModel;
import in.lifc.customerapp.retrofitservices.ApiClient;
import in.lifc.customerapp.retrofitservices.ApiService;
import in.lifc.customerapp.savedata.PrefConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Service_request extends AppCompatActivity {
    private PrefConfig prefConfig;
    private Spinner spServiceType;
    private String serviceRequestValue;
   private AppCompatButton btnSubmit;
private EditText etRemark,etLoanNumber;

    List<RequestDropdownModel.Data> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_request);
        ImageView iv_back = findViewById(R.id.iv_back);
        spServiceType = findViewById(R.id.spservicetype);
        btnSubmit = findViewById(R.id.btn_submit);
        etLoanNumber = findViewById(R.id.et_loan_number);
        etRemark = findViewById(R.id.et_remark);
        prefConfig = new PrefConfig(this);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loanNumber = etLoanNumber.getText().toString();
                String Remark = etRemark.getText().toString();

                CustServiceRequest(loanNumber,Remark);
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        prefConfig = new PrefConfig(this);

        serviceRequestDropdown();
    }

    private void serviceRequestDropdown() {
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<RequestDropdownModel> call = service.getRequestType("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<RequestDropdownModel>() {
            @Override
            public void onResponse(Call<RequestDropdownModel> call, Response<RequestDropdownModel> response) {
                List<String> listServiceType = new ArrayList<>();
                final RequestDropdownModel allevent = response.body();
                if (allevent != null) {
                    for (int i = 0; i < allevent.getData().size(); i++) {
                        dataList = allevent.getData();
                        listServiceType.add(dataList.get(i).getRequestType());

                    }
                    ArrayAdapter aa = new ArrayAdapter(Service_request.this, android.R.layout.simple_spinner_item, listServiceType);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spServiceType.setAdapter(aa);
                    aa.notifyDataSetChanged();
                    spServiceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            serviceRequestValue = spServiceType.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<RequestDropdownModel> call, Throwable t) {

            }
        });
    }
    private void CustServiceRequest(String loanNumber,String remark)
    {



        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<CustomerServiceRequest> call = service.CustServiceRequest("Bearer "+prefConfig.readToken(),"",loanNumber,remark);
        call.enqueue(new Callback<CustomerServiceRequest>() {
            @Override
            public void onResponse(@NonNull Call<CustomerServiceRequest> call, @NonNull Response<CustomerServiceRequest> response) {
                if (response.body() != null) {
                    Toast.makeText(getApplicationContext(), "Service Request has been successfully submitted!", Toast.LENGTH_SHORT).show();
                  /*  Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                    startActivity(intent);*/
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CustomerServiceRequest> call, Throwable t) {

                // pDialog.dismiss();
                //  Log.d("Error", t.getMessage());
            }
        });
    }

}
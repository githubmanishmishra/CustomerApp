package in.lifc.customerapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import in.lifc.customerapp.R;
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
    List<RequestDropdownModel.Data> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_request);
        ImageView iv_back = findViewById(R.id.iv_back);
        spServiceType = findViewById(R.id.spservicetype);
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

}
package in.lifc.customerapp.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import in.lifc.customerapp.R;
import in.lifc.customerapp.local_database.PrefManager;
import in.lifc.customerapp.model.LoginModel;
import in.lifc.customerapp.retrofitservices.ApiClient;
import in.lifc.customerapp.retrofitservices.ApiService;
import in.lifc.customerapp.savedata.PrefConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etMobile;
    PrefConfig prefConfig;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefConfig = new PrefConfig(this);

        PrefManager prefManager = new PrefManager(getApplicationContext());
        if (prefManager.isFirstTimeLaunch()) {
            prefManager.setFirstTimeLaunch(false);
            startActivity(new Intent(LoginActivity.this, IntroductionActivity.class));
            finish();
        }
        TextView tvTermcndition = findViewById(R.id.term_condition);
        tvTermcndition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.show();
            }

        });
        checkBox = findViewById(R.id.chkBox);
        etMobile = findViewById(R.id.et_mobile);
        Button btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(view -> {

            if (!validate()) {
                onUpdateFailed();
            }
            else {
                String mobileNumber = etMobile.getText().toString();
                getLogin(mobileNumber);

            }
        }
        );


    }
/*private void checkbox()
{
    if(checkBox.isChecked())
    {
        Intent intent = new Intent(LoginActivity.this,Otp_Verify_Customer.class);
        startActivity(intent);
    }
    else {
        checkBox.setText( "Please Accept this!");
        checkBox.setTextColor(Color.parseColor("#FF0000"));
    }
}*/
    private boolean validate() {
        boolean valid = true;

        String mobileNumber = etMobile.getText().toString();

        if (mobileNumber.isEmpty() | mobileNumber.length() != 10) {
            etMobile.setError("Enter 10 digit Mobile Number ");
            requestFocus(etMobile);
            valid = false;
        } else {
            etMobile.setError(null);
        }
        return valid;
    }

    private void onUpdateFailed() {
        Toast.makeText(LoginActivity.this, "wrong credential ", Toast.LENGTH_LONG).show();

        //  btnCreateAccount.setEnabled(true);
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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
                        prefConfig.writeLoginStatus(true);
                        prefConfig.writeName(response.body().getToken());
                        Log.d("token>>>>>>>>>>>>", response.body().getToken());

                        if(checkBox.isChecked())
                        {
                            Intent intents = new Intent(LoginActivity.this, Otp_Verify_Customer.class);
                            startActivity(intents);
                            Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            checkBox.setText( "Please Accept this!");
                            checkBox.setTextColor(Color.parseColor("#FF0000"));
                        }


                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
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
package in.lifc.customerapp.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.phone.SmsRetriever;

import in.lifc.customerapp.R;
import in.lifc.customerapp.model.Otpcustomer;
import in.lifc.customerapp.retrofitservices.ApiClient;
import in.lifc.customerapp.retrofitservices.ApiService;
import in.lifc.customerapp.savedata.PrefConfig;
import in.lifc.customerapp.sms_auto.MySMSBroadcastReceiver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Otp_Verify_Customer extends AppCompatActivity {
    MySMSBroadcastReceiver mySMSBroadcastReceiver;
    PrefConfig prefConfig;
   private EditText editTextotp1, editTextotp2, editTextotp3, editTextotp4,editTextotp5,editTextotp6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify_customer);
//        startSMSRetrieverClient(); // Already implemented above.
         mySMSBroadcastReceiver = new MySMSBroadcastReceiver();
        registerReceiver(mySMSBroadcastReceiver, new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION));
        mySMSBroadcastReceiver.init(new MySMSBroadcastReceiver.OTPReceiveListener() {
            @Override
            public void onOTPReceived(String otp) {
                // OTP Received
            }

            @Override
            public void onOTPTimeOut() {

            }
        });

        editTextotp1 = findViewById(R.id.edittext_otp1);
        editTextotp2 = findViewById(R.id.edittext_otp2);
        editTextotp3 = findViewById(R.id.edittext_otp3);
        editTextotp4 = findViewById(R.id.edittext_otp4);
        editTextotp5 = findViewById(R.id.edittext_otp5);
        editTextotp6 = findViewById(R.id.edittext_otp6);
        Button btn_otp =findViewById(R.id.btn_otpverify);
        editTextotp1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editTextotp1.getText().toString().length() == 1)     //size as per your requirement
                {
                    editTextotp2.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        editTextotp2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editTextotp2.getText().toString().length() == 1)     //size as per your requirement
                {
                    editTextotp3.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        editTextotp3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editTextotp3.getText().toString().length() == 1)     //size as per your requirement
                {
                    editTextotp4.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        editTextotp4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editTextotp4.getText().toString().length() == 1)     //size as per your requirement
                {
                    editTextotp5.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        editTextotp5.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editTextotp5.getText().toString().length() == 1)     //size as per your requirement
                {
                    editTextotp6.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        editTextotp6.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editTextotp6.getText().toString().length() == 1)     //size as per your requirement
                {
                    btn_otp.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        btn_otp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!editTextotp1.getText().toString().isEmpty() &&
                            !editTextotp2.getText().toString().isEmpty() &&
                            !editTextotp3.getText().toString().isEmpty() &&
                            ! editTextotp4.getText().toString().isEmpty()&&
                            ! editTextotp5.getText().toString().isEmpty()&&
                            ! editTextotp6.getText().toString().isEmpty())
                    {
                        String editTextValue = editTextotp1.getText().toString()+
                                editTextotp2.getText().toString()+editTextotp3.getText().toString()
                                +editTextotp4.getText().toString()+editTextotp5.getText().toString()+
                                editTextotp6.getText().toString();

                    //    if(editTextValue.equalsIgnoreCase(""+otpValue)){
                            Intent intent = new Intent(Otp_Verify_Customer.this, ChooseLanguageActivity.class);
                            Bundle bundle1 = new Bundle();
                        //    bundle1.putString("mob_no", mobileNumber);
                         //   bundle1.putString("otp", String.valueOf(otpValue));
                            intent.putExtras(bundle1);
                            startActivity(intent);


                  //      }
                    }
                    else
                    {
                        Toast.makeText(Otp_Verify_Customer.this, "Enter Otp sent on your mobile", Toast.LENGTH_SHORT).show();
                    }

                }
            });


    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mySMSBroadcastReceiver != null)
            unregisterReceiver(mySMSBroadcastReceiver);
    }

}
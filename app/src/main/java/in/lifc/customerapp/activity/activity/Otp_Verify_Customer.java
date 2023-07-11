package in.lifc.customerapp.activity.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.phone.SmsRetriever;

import in.lifc.customerapp.R;
import in.lifc.customerapp.activity.sms_auto.MySMSBroadcastReceiver;

public class Otp_Verify_Customer extends AppCompatActivity {
    MySMSBroadcastReceiver mySMSBroadcastReceiver;

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
        Button btn_otp = findViewById(R.id.btn_otpverify);

        btn_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Otp_Verify_Customer.this, ChooseLanguageActivity.class);
                startActivity(intent);

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
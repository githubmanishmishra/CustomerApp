package in.lifc.customerapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import in.lifc.customerapp.R;
import in.lifc.customerapp.adapters.The_Slide_items_Pager_Adapter;
import in.lifc.customerapp.model.The_Slide_Items_Model_Class;
import in.lifc.customerapp.retrofitservices.ApiClient;
import in.lifc.customerapp.retrofitservices.ApiService;
import in.lifc.customerapp.savedata.PrefConfig;
import retrofit2.Call;
import retrofit2.Callback;


public class OfferAndSchemeActivity extends AppCompatActivity {

    private List<The_Slide_Items_Model_Class.Datum> listItems = new ArrayList<>();
    ;
    private ViewPager page;
    private TabLayout tabLayout;

    PrefConfig prefConfig;

    TextView noScheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_and_scheme);

        prefConfig = new PrefConfig(this);
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        page = findViewById(R.id.my_pager);
        tabLayout = findViewById(R.id.my_tablayout);
        noScheme = findViewById(R.id.tv_no_scheme);

        getBannerData();

        noScheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OfferAndSchemeActivity.this, NoSchemeActivity.class));
            }
        });

    }

    private void getBannerData() {
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<The_Slide_Items_Model_Class> call = service.getLoanOffer("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<The_Slide_Items_Model_Class>() {
            @Override
            public void onResponse(@NonNull Call<The_Slide_Items_Model_Class> call, @NonNull retrofit2.Response<The_Slide_Items_Model_Class> response) {

                final The_Slide_Items_Model_Class allEvent = response.body();
                if (allEvent != null) {
                    for (int i = 0; i < allEvent.getData().size(); i++) {
                        listItems = allEvent.getData();

                    }

                    // Make a copy of the slides you'll be presenting.

                    The_Slide_items_Pager_Adapter itemsPager_adapter = new The_Slide_items_Pager_Adapter(OfferAndSchemeActivity.this, listItems);
                    page.setAdapter(itemsPager_adapter);

                    // The_slide_timer
//        java.util.Timer timer = new java.util.Timer();
//        timer.scheduleAtFixedRate(new The_slide_timer(),2000,3000);
                    tabLayout.setupWithViewPager(page, true);


                } else {
                    Toast.makeText(OfferAndSchemeActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<The_Slide_Items_Model_Class> call, @NonNull Throwable t) {

                // pDialog.dismiss();
                Log.d("Error", t.getMessage());
            }
        });

    }

    /*public class The_slide_timer extends TimerTask {
        @Override
        public void run() {

            OfferAndSchemeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (page.getCurrentItem()< listItems.size()-1) {
                        page.setCurrentItem(page.getCurrentItem()+1);
                    }
                    else
                        page.setCurrentItem(0);
                }
            });
        }
    }*/
}
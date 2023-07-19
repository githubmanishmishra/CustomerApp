package in.lifc.customerapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import in.lifc.customerapp.R;
import in.lifc.customerapp.adapters.The_Slide_items_Pager_Adapter;
import in.lifc.customerapp.model.The_Slide_Items_Model_Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;


public class OfferAndSchemeActivity extends AppCompatActivity {

    private List<The_Slide_Items_Model_Class> listItems;
    private ViewPager page;
    private TabLayout tabLayout;

    TextView noScheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_and_scheme);
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        page = findViewById(R.id.my_pager) ;
        tabLayout = findViewById(R.id.my_tablayout);
        noScheme = findViewById(R.id.tv_no_scheme);

        noScheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OfferAndSchemeActivity.this,NoSchemeActivity.class));
            }
        });

        // Make a copy of the slides you'll be presenting.
        listItems = new ArrayList<>() ;
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.laxmi_india_finance_pvt_ltd,"Slider 1 Title"));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.laxmi_india_finance_pvt_ltd,"Slider 2 Title"));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.laxmi_india_finance_pvt_ltd,"Slider 3 Title"));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.laxmi_india_finance_pvt_ltd,"Slider 4 Title"));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.laxmi_india_finance_pvt_ltd,"Slider 5 Title"));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.laxmi_india_finance_pvt_ltd,"Slider 6 Title"));


        The_Slide_items_Pager_Adapter itemsPager_adapter = new The_Slide_items_Pager_Adapter(this, listItems);
        page.setAdapter(itemsPager_adapter);

        // The_slide_timer
//        java.util.Timer timer = new java.util.Timer();
//        timer.scheduleAtFixedRate(new The_slide_timer(),2000,3000);
        tabLayout.setupWithViewPager(page,true);

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
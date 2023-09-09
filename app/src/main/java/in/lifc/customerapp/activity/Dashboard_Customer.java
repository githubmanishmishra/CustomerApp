package in.lifc.customerapp.activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

import in.lifc.customerapp.R;
import in.lifc.customerapp.model.The_Slide_Items_Model_Class;
import in.lifc.customerapp.retrofitservices.ApiClient;
import in.lifc.customerapp.retrofitservices.ApiService;
import in.lifc.customerapp.savedata.PrefConfig;
import retrofit2.Call;
import retrofit2.Callback;

public class Dashboard_Customer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbars;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private static final float END_SCALE = 0.7f;
    private TextView labelView;
    private View contentView;

    private List<The_Slide_Items_Model_Class.Datum> listItems = new ArrayList<>();
    private ImageCarousel page;
//    private TabLayout tabLayout;

    PrefConfig prefConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_customer);

        prefConfig = new PrefConfig(this);

        page = findViewById(R.id.my_pager);
        page.registerLifecycle(getLifecycle());

        getBannerData();

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbars = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbars);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbars, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        labelView = (TextView) findViewById(R.id.label);
        contentView = findViewById(R.id.content);

        toolbars.setNavigationIcon(new DrawerArrowDrawable(this));
        toolbars.setNavigationOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     if (drawerLayout.isDrawerOpen(navigationView)) {
                                                         drawerLayout.closeDrawer(navigationView);
                                                     }
                                                     else {
                                                         drawerLayout.openDrawer(navigationView);
                                                     }
                                                 }
                                             }
        );

        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                                           @Override
                                           public void onDrawerSlide(View drawerView, float slideOffset) {
                                             //  labelView.setVisibility(slideOffset > 0 ? View.VISIBLE : View.GONE);

                                               // Scale the View based on current slide offset
                                               final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                                               final float offsetScale = 1 - diffScaledOffset;
                                               contentView.setScaleX(offsetScale);
                                               contentView.setScaleY(offsetScale);

                                               // Translate the View, accounting for the scaled width
                                               final float xOffset = drawerView.getWidth() * slideOffset;
                                               final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                                               final float xTranslation = xOffset - xOffsetDiff;
                                               contentView.setTranslationX(xTranslation);
                                           }

                                           @Override
                                           public void onDrawerClosed(View drawerView) {
//                                               labelView.setVisibility(View.GONE);
                                           }
                                       }
        );


        CardView cv_new_loan_request = findViewById(R.id.new_loan_request);
        cv_new_loan_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard_Customer.this, NewLoanRequest.class);
                startActivity(intent);
            }
        });
        CardView Cv_my_loan_info = findViewById(R.id.cv_my_loan_info);
        Cv_my_loan_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard_Customer.this, My_loan_info.class);
                startActivity(intent);
            }
        });

        CardView cv_nearest_branch = findViewById(R.id.cv_nearest_branch);
        cv_nearest_branch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard_Customer.this, Nearest_branch.class);
                startActivity(intent);
            }
        });
        CardView cv_current_dues = findViewById(R.id.cv_current_dues);
        cv_current_dues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard_Customer.this, CurrentDues.class);
                startActivity(intent);
            }
        });
        CardView cv_Clculator  = findViewById(R.id.cv_calculator);
        cv_Clculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard_Customer.this, EmiCalculator.class);
                startActivity(intent);
            }
        });
CardView cv_serviceRequest = findViewById(R.id.cv_service_request);
cv_serviceRequest.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Dashboard_Customer.this,Service_request.class);
        startActivity(intent);
    }
});
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_loantype) {
            Intent intent = new Intent(Dashboard_Customer.this, Loan_type.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_offerandscheme) {
            Intent intent = new Intent(Dashboard_Customer.this,OfferAndSchemeActivity.class);
            startActivity(intent);
            
        }
        else if (id == R.id.nav_offerandscheme) {
            Intent intent = new Intent(Dashboard_Customer.this,OfferAndSchemeActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_connect) {
            Intent intent = new Intent(Dashboard_Customer.this,ConnectWithUs.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_about) {
            Intent intent = new Intent(Dashboard_Customer.this,Aboutus.class);
            startActivity(intent);
        }

        return true;
    }

    private void getBannerData() {
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<The_Slide_Items_Model_Class> call = service.getLoanOffer("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<The_Slide_Items_Model_Class>() {
            @Override
            public void onResponse(@NonNull Call<The_Slide_Items_Model_Class> call, @NonNull retrofit2.Response<The_Slide_Items_Model_Class> response) {

                final The_Slide_Items_Model_Class allEvent = response.body();
                if (allEvent != null) {
                    List<CarouselItem> list = new ArrayList<>();

                    for (int i = 0; i < allEvent.getData().size(); i++) {
                        listItems = allEvent.getData();
                        list.add(new CarouselItem(listItems.get(i).getOfferUrl()));

                    }

                    // Make a copy of the slides you'll be presenting.

                    //  The_Slide_items_Pager_Adapter itemsPager_adapter = new The_Slide_items_Pager_Adapter(OfferAndSchemeActivity.this, listItems);
                    //  page.setAdapter(itemsPager_adapter);

                    // The_slide_timer
//        java.util.Timer timer = new java.util.Timer();
//        timer.scheduleAtFixedRate(new The_slide_timer(),2000,3000);
                    //      tabLayout.setupWithViewPager(page, true);

                    page.setData(list);


                } else {
                    Toast.makeText(Dashboard_Customer.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<The_Slide_Items_Model_Class> call, @NonNull Throwable t) {

                // pDialog.dismiss();
                Log.d("Error", t.getMessage());
            }
        });

    }
}
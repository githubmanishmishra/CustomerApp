package in.lifc.customerapp.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

import in.lifc.customerapp.R;
public class Dashboard_Customer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbars;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_customer);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbars = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbars);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbars, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
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
}
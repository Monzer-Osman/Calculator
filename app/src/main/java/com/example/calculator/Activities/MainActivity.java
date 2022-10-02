package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new StandardCalculatorFragment()).commit();
            getSupportActionBar().setTitle("Standard");
            navigationView.setCheckedItem(R.id.standard);
        }
    }

    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.standard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StandardCalculatorFragment()).commit();
                getSupportActionBar().setTitle("Standard");
                break;

            case R.id.scientific:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ScientificFragment()).commit();
                getSupportActionBar().setTitle("Scientific");
                break;

            case R.id.programmer:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProgrammerFragment()).commit();
                getSupportActionBar().setTitle("Programmer");
                break;

            case R.id.date:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DateFragment()).commit();
                getSupportActionBar().setTitle("Date Calculation");
                break;

            case R.id.currency:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CurrencyExchangeFragment()).commit();
                getSupportActionBar().setTitle("Currency");
                break;

            case R.id.volume:
                break;

            case R.id.length:
                break;

            case R.id.feedBack:
                sendFeedBack();
                break;
            case R.id.contactUs:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ContactUsFragment()).commit();
                getSupportActionBar().setTitle("Contact");
                break;

            case R.id.about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutFragment()).commit();
                getSupportActionBar().setTitle("About");
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void sendFeedBack() {
        Toast.makeText(this, "One Day I'm Gonna hahaha", Toast.LENGTH_LONG).show();
    }
}
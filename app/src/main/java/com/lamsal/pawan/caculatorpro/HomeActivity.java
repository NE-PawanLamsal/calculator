package com.lamsal.pawan.caculatorpro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private long backPressed;
    private Toast backToast;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        getSupportActionBar().setTitle("Mero Vote");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.frame_layout);

        navigationView = findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(this);


        NavigationUI.setupWithNavController(bottomNavigationView, navController);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }

    public void userlogout(View view) {
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        preferences.clearData(this);
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_video:
                gotoUrl("https://youtube.com/channel/UCeEua2oaWMNJJSS6O2ygoNA");
                break;
            case R.id.navigation_ebook:
                Toast.makeText(this, "Unable", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_website:
                gotoUrl("https://pec.edu.np");
                break;
            case R.id.navigation_share:
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Mero Vote");
                    i.putExtra(Intent.EXTRA_TEXT, "Currently unavialable in PlayStore");
                    startActivity(Intent.createChooser(i, "Share With"));
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to share", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.navigation_developer:
                Toast.makeText(this, "Unable", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_logout:
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                preferences.clearData(this);
                finish();
                break;
        }
        return true;
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (backPressed + 3000 > System.currentTimeMillis()) {
                super.onBackPressed();
                backToast.cancel();
                return;
            } else {
                backToast = Toast.makeText(this, "Press Back Again To Exit", Toast.LENGTH_SHORT);
                backToast.show();
            }
            backPressed = System.currentTimeMillis();
        }
    }
}
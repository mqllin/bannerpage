package com.example.gameHelper.Activity.fun.normal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import com.example.gameHelper.MainActivity;
import com.example.gameHelper.R;
import com.google.android.material.navigation.NavigationView;

public class DrawActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        mDrawerLayout = findViewById(R.id.navigation_draw_layout);
        mToolbar = findViewById(R.id.navigation_toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.app_name,R.string.app_name);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.e("title==>", (String) item.getTitle());
                mDrawerLayout.closeDrawers();
                TextView title = findViewById(R.id.draw_title);
                title.setText((String) item.getTitle());

                Toast.makeText(DrawActivity.this, (String)item.getTitle(),Toast.LENGTH_LONG);

                return true;
            }
        });
    }
}
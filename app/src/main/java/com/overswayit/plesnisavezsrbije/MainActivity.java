package com.overswayit.plesnisavezsrbije;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupActionBar();
        setupNavigationDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawer() {
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                navigationItemSelected(menuItem.getItemId());

                return true;
            }
        });

        navigationView.setCheckedItem(R.id.nav_news);
    }

    private void navigationItemSelected(int itemId) {
        switch (itemId) {
            case R.id.nav_news:
//                ToDo: Open News Activity
                Toast.makeText(getApplicationContext(), R.string.news, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_point_list:
//                ToDo: Open Point ListActivity
                Toast.makeText(getApplicationContext(), R.string.point_list, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_rating_list:
//                ToDo: Open Rating List Activity
                Toast.makeText(getApplicationContext(), R.string.rating_list, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_favorite_couples:
//                ToDo: Open Favorite Couples Activity
                Toast.makeText(getApplicationContext(), R.string.favorite_couples, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_clubs:
//                ToDo: Open Clubs Activity
                Toast.makeText(getApplicationContext(), R.string.clubs, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_adjudicators:
//                ToDo: Open Adjudicators Activity
                Toast.makeText(getApplicationContext(), R.string.adjudicators, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_competitions:
//                ToDo: Open Competitions Activity
                Toast.makeText(getApplicationContext(), R.string.competitions, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_contacts:
//                ToDo: Open Contact Activity
                Toast.makeText(getApplicationContext(), R.string.contact, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }
}

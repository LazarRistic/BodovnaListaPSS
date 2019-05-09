package com.overswayit.plesnisavezsrbije;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.overswayit.plesnisavezsrbije.activities.AdjudicatorsActivity;
import com.overswayit.plesnisavezsrbije.activities.BaseActivity;
import com.overswayit.plesnisavezsrbije.activities.ClubsActivity;
import com.overswayit.plesnisavezsrbije.models.News;
import com.overswayit.plesnisavezsrbije.utils.StringUtil;
import com.overswayit.plesnisavezsrbije.viewmodels.NewsViewModel;
import com.overswayit.plesnisavezsrbije.views.NewsAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.news_recycler_view)
    RecyclerView recyclerView;

    private DrawerLayout mDrawerLayout;
    private NewsAdapter newsAdapter;
    private ArrayList<News> newsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupActionBar();
        setupNavigationDrawer();

        newsAdapter = new NewsAdapter(newsArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(newsAdapter);

        NewsViewModel viewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        viewModel.getAllNews().observe(this, news -> {
            newsArrayList.clear();
            newsArrayList.addAll(news);
            newsAdapter.notifyDataSetChanged();
        });
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
                openActivity(ClubsActivity.class);
                break;
            case R.id.nav_adjudicators:
                openActivity(AdjudicatorsActivity.class);
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

    private void openActivity(Class<?> clubsActivityClass) {
        startActivity(new Intent(this, clubsActivityClass));
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }
}

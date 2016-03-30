package com.skytalkers.app.moviematcher.controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.controllers.Fragments.MovieListFragment;
import com.skytalkers.app.moviematcher.controllers.Fragments.ProfileFragment;
import com.skytalkers.app.moviematcher.controllers.Fragments.RecommendationFragment;
import com.skytalkers.app.moviematcher.controllers.Fragments.SearchFragment;
import com.skytalkers.app.moviematcher.controllers.Fragments.UserListFragment;
import com.skytalkers.app.moviematcher.models.MovieManager;
import com.skytalkers.app.moviematcher.models.ToastWrapper;
import com.skytalkers.app.moviematcher.models.UserManager;

import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, 0);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        android.support.v4.app.Fragment myFragment = new ProfileFragment();
        android.support.v4.app.FragmentManager fManager = getSupportFragmentManager();
        fManager.beginTransaction()
                .replace(R.id.container, myFragment)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        android.support.v4.app.Fragment myFragment = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        MovieManager mm = new MovieManager();
        /*
        If you want to add a new Fragment to the system, make the fragment and add it to this if - else if series.
        */
        if (id == R.id.nav_search) {
            myFragment = new SearchFragment();
        } else if (id == R.id.nav_profile) {
            myFragment = new ProfileFragment();
        } else if (id == R.id.nav_new_movies) {
            mm.setTitle("New Movies");
            try { mm.sendNewMovieRequest(); } catch (Exception e) {
                Log.d("**MOVIEMATCHER**", "Whoops, something went wrong.");
                ToastWrapper.show(this, "Failed to get movies");
            }
            myFragment = new MovieListFragment();
        } else if (id == R.id.nav_new_dvds) {
            mm.setTitle("New DVDs");
            try { mm.sendRecentDVDRequest(); } catch (Exception e) {
                Log.d("**MOVIEMATCHER**", "Whoops, something went wrong.");
                ToastWrapper.show(this, "Failed to get movies");
            }
            myFragment = new MovieListFragment();
        } else if (id == R.id.nav_rec) {
            myFragment = new RecommendationFragment();
        }

        android.support.v4.app.FragmentManager fManager = getSupportFragmentManager();
        fManager.beginTransaction()
                .replace(R.id.container, myFragment)
                .commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*
    All button and screen responses must be referred here in the ACTIVITY, NOT the fragment they are contained in.
     */
    public void onLogoutButtonClick(View v) {
        UserManager um = new UserManager();
        um.logout();
        finish();
    }

    public void onUsersButtonClick(View v) {
        android.support.v4.app.FragmentManager fManager = getSupportFragmentManager();
        fManager.beginTransaction()
                .replace(R.id.container, new UserListFragment())
                .commit();
    }

    public void onUserEditButtonClick(View v) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    public void onChangePassButtonClick(View v) {
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivity(intent);
    }

    public void onViewProfileButtonClick(View v) {
        UserManager um = new UserManager();
        Intent intent = new Intent(this, UserProfileActivity.class);
        intent.putExtra("user", um.getUserName());
        startActivity(intent);
    }

    //http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=[your_api_key]&q=Toy+Story+3&page_limit=1
    public void onBasicSearchButtonClick(View v) throws Exception{
        String name = ((EditText) findViewById(R.id.basicSearchEditText)).getText().toString();
        String parsed[] = name.split(" ");
        String parsedName = TextUtils.join("+", parsed);
        MovieManager mm = new MovieManager();
        mm.sendRTRequest(parsedName);
        ArrayList<String> movies = mm.getTitles();
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movies);
        ListView lv = (ListView) findViewById(R.id.searchListView);
        lv.setAdapter(adapter);
    }

    public void onOverallButtonClick(View v) {
        MovieManager mm = new MovieManager();
        mm.setTitle("Overall Recommendations");
        mm.getOverallRec();
        android.support.v4.app.Fragment myFragment = new MovieListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).commit();
    }

    public void onMajorButtonClick(View v) {
        MovieManager mm = new MovieManager();
        mm.setTitle("Major Recommendations");
        Log.d("**MOVIEMATCHER**", String.valueOf(mm.getUserTitles()));
        mm.getMajorRec(new UserManager().getUserMajor());
        android.support.v4.app.Fragment myFragment = new MovieListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).commit();
    }
}

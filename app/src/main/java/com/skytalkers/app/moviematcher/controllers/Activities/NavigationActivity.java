package com.skytalkers.app.moviematcher.controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.skytalkers.app.moviematcher.controllers.Fragments.SearchFragment;
import com.skytalkers.app.moviematcher.controllers.Fragments.UserListFragment;
import com.skytalkers.app.moviematcher.models.MovieManager;
import com.skytalkers.app.moviematcher.models.UserManager;

import java.util.ArrayList;
import java.util.HashSet;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, 0);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        final android.support.v4.app.Fragment myFragment = new ProfileFragment();
        final android.support.v4.app.FragmentManager fManager = getSupportFragmentManager();
        fManager.beginTransaction()
                .replace(R.id.container, myFragment)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onBackPressed() {
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        final int id = item.getItemId();

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
        final int id = item.getItemId();
        final MovieManager mm = new MovieManager();
        /*
        If you want to add a new Fragment to the system, make the fragment and add it to this if - else if series.
        */
        if (id == R.id.nav_search) {
            myFragment = new SearchFragment();
        } else if (id == R.id.nav_profile) {
            myFragment = new ProfileFragment();
        } else if (id == R.id.nav_new_movies) {
            mm.setTitle("New Movies");
            mm.setType(0);
            mm.sendNewMovieRequest();
            myFragment = new MovieListFragment();
        } else if (id == R.id.nav_rec) {
            myFragment = new MovieListFragment();
            mm.setTitle("Recommendations");
            mm.setType(1);
            mm.getOverallRec();
        }

        final android.support.v4.app.FragmentManager fManager = getSupportFragmentManager();
        fManager.beginTransaction()
                .replace(R.id.container, myFragment)
                .commit();
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*
    All button and screen responses must be referred here in the ACTIVITY, NOT the fragment they are contained in.
     */

    /**
     * Logout user
     * @param v Current View
     */
    public void onLogoutButtonClick(View v) {
        final UserManager um = new UserManager();
        um.logout();
        finish();
    }

    /**
     * Brings up user list
     * @param v Current View
     */
    public void onUsersButtonClick(View v) {
        final android.support.v4.app.FragmentManager fManager = getSupportFragmentManager();
        fManager.beginTransaction()
                .replace(R.id.container, new UserListFragment())
                .commit();
    }

    /**
     * Start EditProfile Activity
     * @param v Current View
     */
    public void onUserEditButtonClick(View v) {
        final Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    /**
     * Start ChangePassword Activity
     * @param v Current View
     */
    public void onChangePassButtonClick(View v) {
        final Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivity(intent);
    }

    //http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=[your_api_key]&q=Toy+Story+3&page_limit=1

    /**
     * Send a movie search request
     * @param v Current View
     */
    public void onBasicSearchButtonClick(View v) {
        final String name = ((EditText) findViewById(R.id.basicSearchEditText)).getText().toString();
        final String parsed[] = name.split(" ");
        final String parsedName = TextUtils.join("+", parsed);
        final MovieManager mm = new MovieManager();
        mm.sendRTRequest(parsedName);
        final ArrayList<String> movies = new ArrayList<>(mm.getTitles());
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movies);
        final ListView lv = (ListView) findViewById(R.id.searchListView);
        lv.setAdapter(adapter);
    }

    /**
     * Changes movie list depending on type
     * @param v Current View
     */
    public void onRightButtonClick(View v) {
        final MovieManager mm = new MovieManager();
        if (mm.getType() == 0) {
            mm.sendRecentDVDRequest();
        } else {
            mm.getMajorRec(new UserManager().getUserMajor());
        }
        final ArrayList<String> titlesToShow = new ArrayList<>(new HashSet<>(mm.getTitles()));
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titlesToShow);
        ((ListView) findViewById(R.id.movieListView)).setAdapter(adapter);
    }

    /**
     * Changes movie list depending on type
     * @param v Current View
     */
    public void onLeftButtonClick(View v) {
        final MovieManager mm = new MovieManager();
        if (mm.getType() == 0) {
            mm.sendNewMovieRequest();
        } else {
            mm.getOverallRec();
        }
        final ArrayList<String> titlesToShow = new ArrayList<>(new HashSet<>(mm.getTitles()));
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titlesToShow);
        ((ListView) findViewById(R.id.movieListView)).setAdapter(adapter);
    }
}

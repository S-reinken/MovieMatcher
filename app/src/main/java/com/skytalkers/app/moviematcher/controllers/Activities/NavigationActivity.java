package com.skytalkers.app.moviematcher.controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.firebase.client.Firebase;
import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.controllers.Fragments.NewDVDsFragment;
import com.skytalkers.app.moviematcher.controllers.Fragments.NewMoviesFragment;
import com.skytalkers.app.moviematcher.controllers.Fragments.ProfileFragment;
import com.skytalkers.app.moviematcher.controllers.Fragments.SearchFragment;
import com.skytalkers.app.moviematcher.models.DatabaseManager;
import com.skytalkers.app.moviematcher.models.HTTPRequest;
import com.skytalkers.app.moviematcher.models.User;
import com.skytalkers.app.moviematcher.models.UserManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);

        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

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
        /*
        If you want to add a new Fragment to the system, make the fragment and add it to this if - else if series.
        */
        if (id == R.id.nav_search) {
            myFragment = new SearchFragment();
        } else if (id == R.id.nav_profile) {
            myFragment = new ProfileFragment();
        } else if (id == R.id.nav_new_movies) {
            myFragment = new NewMoviesFragment();
        } else if (id == R.id.nav_new_dvds) {
            myFragment = new NewDVDsFragment();
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
        Intent intent = new Intent(this, UserListActivity.class);
        startActivity(intent);
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
        String user = um.getUserName();
        Intent intent = new Intent(this, UserProfileActivity.class);
        intent.putExtra("user_object", user);
        startActivity(intent);
    }
    //http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=[your_api_key]&q=Toy+Story+3&page_limit=1
    public void onBasicSearchButtonClick(View v) throws Exception{
        String name = ((EditText) findViewById(R.id.basicSearchEditText)).getText().toString();
        String parsed[] = name.split(" ");
        String parsedName = TextUtils.join("+", parsed);
        String req = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey="
                + HTTPRequest.getKey()
                + "q="
                + parsedName + "&page_limit=5";
        String res = HTTPRequest.sendRequest(req);
        JSONObject json = new JSONObject(res);
        Log.d("**MOVIEMATCHER**", res);
        Log.d("**MOVIEMATCHER**", json.getJSONArray("movies").getJSONObject(0).getString("title"));

        int count = Math.min(json.getInt("total"), 5);
        ArrayList<String> movies = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            movies.add(json.getJSONArray("movies").getJSONObject(i).getString("title"));
        }
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movies);
        ListView lv = (ListView) findViewById(R.id.searchListView);
        lv.setAdapter(adapter);

    }
}

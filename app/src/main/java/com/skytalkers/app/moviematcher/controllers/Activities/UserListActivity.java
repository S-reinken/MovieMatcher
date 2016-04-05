package com.skytalkers.app.moviematcher.controllers.Activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.UserManager;

public class UserListActivity extends ListActivity {

    private ArrayAdapter<String> adapter;

    /**
     * Occurs on creation of activity, populates list and displays it in ListView
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        final UserManager um = new UserManager();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, um.getUserList());
        final ListView listview = (ListView) findViewById(android.R.id.list);
        listview.setAdapter(adapter);
    }

    /**
     * Starts UserProfileActivity of selected user
     * @param lv ListView of users
     * @param v Specific view in ListView
     * @param position Position of selected user
     * @param id Row id of item clicked
     */
    @Override
    public void onListItemClick(ListView lv, View v, int position, long id) {
        final UserManager um = new UserManager();
        final String user = um.getUserByPos(position);
        final Intent intent = new Intent(this, UserProfileActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

}

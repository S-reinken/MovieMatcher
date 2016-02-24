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

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        UserManager um = new UserManager();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, um.getUserList());
        ListView listview = (ListView) findViewById(android.R.id.list);
        listview.setAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView lv, View v, int position, long id) {
        UserManager um = new UserManager();
        String user = um.getUserByPos(position).getUsername();
        Intent intent = new Intent(this, UserProfileActivity.class);
        intent.putExtra("user_object", user);
        startActivity(intent);
    }

}

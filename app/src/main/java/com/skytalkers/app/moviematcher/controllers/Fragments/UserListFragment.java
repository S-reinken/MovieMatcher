package com.skytalkers.app.moviematcher.controllers.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.controllers.Activities.UserStatusActivity;
import com.skytalkers.app.moviematcher.models.UserManager;

import java.util.List;

/**
 * Created by Bruce on 3/15/2016.
 */

/**
 * Displays list of users along with their banned status
 */
public class UserListFragment extends Fragment {
    private View myView;
    private ListAdapter adapter;

    /**
     * Sets up User List view
     * @param inflater Inflates raw XML into View objects
     * @param container Parent ViewGroup
     * @param savedInstanceState Android instance state
     * @return User list view view
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView;
        myView = inflater.inflate(R.layout.movie_list_layout, container, false);
        ListAdapter adapter;
        final UserManager um = new UserManager();
        final List<String> users = um.getUserList();
        adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, users);
        ((TextView) myView.findViewById(R.id.movieListTextView)).setText("Users");
        final ListView lv = (ListView) (myView.findViewById(R.id.movieListView));
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final UserManager um = new UserManager();
                final String name = um.getUserList().get(position);
                final Intent intent = new Intent(getActivity().getApplicationContext(), UserStatusActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("ban", um.isBanned(name));
                startActivity(intent);
            }
        });
        return myView;
    }
}

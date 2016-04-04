package com.skytalkers.app.moviematcher.controllers.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.controllers.Activities.MovieActivity;
import com.skytalkers.app.moviematcher.models.MovieManager;
import com.skytalkers.app.moviematcher.models.UserManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schuylerreinken on 2/18/16.
 * Fragment to display a list of searched movies.
 */
public class SearchFragment extends Fragment {
    private View myView;
    private ListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.search_layout, container, false);

        final List<String> content = new ArrayList<>();
        adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, content);
        final ListView lv = (ListView) (myView.findViewById(R.id.searchListView));
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final MovieManager mm = new MovieManager();
                mm.addMovie(mm.getMovies().get(position).getTitle());
                final Intent intent = new Intent(getActivity().getApplicationContext(), MovieActivity.class);
                intent.putExtra("title", mm.getMovies().get(position).getTitle());
                intent.putExtra("image", mm.getMovies().get(position).getImage());
                intent.putExtra("rating", String.valueOf(mm.getMovies().get(position).getRating(new UserManager().getUserName())));
                startActivity(intent);
            }
        });
        return myView;
    }

}

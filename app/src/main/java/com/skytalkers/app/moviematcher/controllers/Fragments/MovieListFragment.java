package com.skytalkers.app.moviematcher.controllers.Fragments;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.controllers.Activities.MovieActivity;
import com.skytalkers.app.moviematcher.models.MovieManager;
import com.skytalkers.app.moviematcher.models.UserManager;

import java.util.ArrayList;

/**
 * Created by Bruce on 3/8/2016.
 */
public class MovieListFragment extends Fragment {
    private View myView;
    private ListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.movie_list_layout, container, false);

        final MovieManager mm = new MovieManager();
        final ArrayList<String> titlesToShow = mm.getTitles();
        adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, titlesToShow);
        ((TextView) myView.findViewById(R.id.movieListTextView)).setText(mm.getTitle());
        final ListView lv = (ListView) (myView.findViewById(R.id.movieListView));
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final MovieManager mm = new MovieManager();
                Log.d("MovieContains", String.valueOf(mm.contains(mm.getMovies().get(position))));
                if (!mm.contains(mm.getMovies().get(position))) {
                    mm.addMovie(mm.getMovies().get(position).getTitle());
                }
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

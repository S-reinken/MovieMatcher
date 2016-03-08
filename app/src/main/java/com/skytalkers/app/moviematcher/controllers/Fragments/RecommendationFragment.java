package com.skytalkers.app.moviematcher.controllers.Fragments;

import android.app.Fragment;
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

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.controllers.Activities.MovieActivity;
import com.skytalkers.app.moviematcher.models.MovieManager;
import com.skytalkers.app.moviematcher.models.ToastWrapper;
import com.skytalkers.app.moviematcher.models.UserManager;

import java.util.ArrayList;

/**
 * Created by Bruce on 3/7/2016.
 */
public class RecommendationFragment extends Fragment {
    View myView;
    ListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.movie_list_layout, container, false);

        MovieManager mm = new MovieManager();
        try { mm.sendNewMovieRequest(); } catch (Exception e) {
            Log.d("**MOVIEMATCHER**", "Whoops, something went wrong.");
            ToastWrapper.show(getActivity().getApplicationContext(), "Failed to get movies");
        }
        ArrayList<String> titlesToShow = mm.getTitles();
        adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, titlesToShow);
        ListView lv = (ListView) (myView.findViewById(R.id.movieListView));
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieManager mm = new MovieManager();
                mm.addMovie(mm.getMovies().get(position).getTitle());
                Intent intent = new Intent(getActivity().getApplicationContext(), MovieActivity.class);
                intent.putExtra("title", mm.getMovies().get(position).getTitle());
                intent.putExtra("image", mm.getMovies().get(position).getImage());
                intent.putExtra("rating", String.valueOf(mm.getMovies().get(position).getRating(new UserManager().getUserName())));
                startActivity(intent);
            }
        });
        return myView;
    }
}

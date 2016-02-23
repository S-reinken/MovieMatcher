package com.skytalkers.app.moviematcher.controllers.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.*;

import java.util.ArrayList;

/**
 * Created by schuylerreinken on 2/19/16.
 */
public class NewMoviesFragment extends Fragment {

    View myView;
    ListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.new_movies_layout, container, false);

        ArrayList<Movie> newMovies = RottenTomatoesManager.getNewMovies();
        ArrayList<String> titlesToShow = new ArrayList<>();
        for (int i = 0; i < newMovies.size(); i++) {
            titlesToShow.add(newMovies.get(i).getTitle());
        }
        adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, titlesToShow);
        ListView list = (ListView) (myView.findViewById(R.id.listView3));
        list.setAdapter(adapter);
        return myView;
    }
}

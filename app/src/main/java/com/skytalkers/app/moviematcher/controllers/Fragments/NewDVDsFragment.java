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

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.controllers.Activities.MovieActivity;
import com.skytalkers.app.moviematcher.models.*;

import java.util.ArrayList;

/**
 * Created by schuylerreinken on 2/23/16.
 * Fragment to display new DVDs.
 */
public class NewDVDsFragment extends Fragment {
    View myView;
    ListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.new_dvds_layout, container, false);

        MovieManager mm = new MovieManager();
        try { mm.sendRecentDVDRequest(); } catch (Exception e) {
            ToastWrapper.show(getActivity().getApplicationContext(), "Failed to get movies");
        }
        ArrayList<String> titlesToShow = mm.getTitles();
        adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, titlesToShow);
        ListView lv = (ListView) (myView.findViewById(R.id.dvdListView));
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieManager mm = new MovieManager();
                Intent intent = new Intent(getActivity().getApplicationContext(), MovieActivity.class);
                intent.putExtra("title", mm.getMovies().get(position).getTitle());
                intent.putExtra("image", mm.getMovies().get(position).getImage());
                startActivity(intent);
            }
        });
        return myView;
    }
}

package com.skytalkers.app.moviematcher.controllers.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.skytalkers.app.moviematcher.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schuylerreinken on 2/18/16.
 */
public class SearchFragment extends Fragment {

    ListAdapter adapter;

    View myView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.search_layout, container, false);

        List<String> content = new ArrayList<>();
        content.add("Avatar");
        content.add("The Jungle Book");
        content.add("Insidious");
        content.add("The Artist");
        adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, content);
        ListView list = (ListView) (myView.findViewById(R.id.listView));
        list.setAdapter(adapter);

        return myView;
    }
}

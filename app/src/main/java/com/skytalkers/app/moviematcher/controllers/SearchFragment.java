package com.skytalkers.app.moviematcher.controllers;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skytalkers.app.moviematcher.R;

/**
 * Created by schuylerreinken on 2/18/16.
 */
public class SearchFragment extends Fragment {

    View myView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.search_layout, container, false);
        return myView;
    }
}

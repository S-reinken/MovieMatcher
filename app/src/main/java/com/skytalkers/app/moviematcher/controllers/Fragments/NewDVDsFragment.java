package com.skytalkers.app.moviematcher.controllers.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skytalkers.app.moviematcher.R;

/**
 * Created by schuylerreinken on 2/23/16.
 */
public class NewDVDsFragment extends Fragment {
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.new_dvds_layout, container, false);
        return myView;
    }
}
package com.skytalkers.app.moviematcher.controllers.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skytalkers.app.moviematcher.R;

/**
 * Created by Bruce on 3/7/2016.
 */


public class RecommendationFragment extends Fragment {

    /**
     * Creates view for recommendation fragment
     * @param inflater Inflates XML into View objects
     * @param container Parent ViewGroup
     * @param savedInstanceState Android instance data
     * @return Recommendation view
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recommendation_layout, container, false);
    }
}

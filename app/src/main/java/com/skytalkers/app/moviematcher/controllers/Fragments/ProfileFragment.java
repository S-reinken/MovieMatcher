package com.skytalkers.app.moviematcher.controllers.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.UserManager;

/**
 * Created by schuylerreinken on 2/18/16.
 */
public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView;
        final RelativeLayout layout = (RelativeLayout) this.getActivity().findViewById(R.id.profileLayout);
        final Button adminButton = new Button(this.getContext());
        adminButton.setText("Show Users");
        final int PARAM = 20;
        adminButton.setLayoutParams(new RelativeLayout.LayoutParams(PARAM, PARAM));
        if (this.getActivity().getIntent().getBooleanExtra("Admin", false)) {
            layout.addView(adminButton);
        }
        myView = inflater.inflate(R.layout.content_primary, container, false);
        final Button b = (Button) myView.findViewById(R.id.usersButton);
        if (!(new UserManager().isAdmin())) {
            b.setVisibility(View.INVISIBLE);
        } else {
            b.setVisibility(View.VISIBLE);
        }
        return myView;
    }
}

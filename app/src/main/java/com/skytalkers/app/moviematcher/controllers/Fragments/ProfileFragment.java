package com.skytalkers.app.moviematcher.controllers.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.UserManager;

/**
 * Created by schuylerreinken on 2/18/16.
 */
public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final RelativeLayout layout = (RelativeLayout) this.getActivity().findViewById(R.id.profileLayout);
        final Button adminButton = new Button(this.getContext());
        adminButton.setText("Show Users");
        final int coords = 20;
        adminButton.setLayoutParams(new RelativeLayout.LayoutParams(coords, coords));
        if (this.getActivity().getIntent().getBooleanExtra("Admin", false)) {
            layout.addView(adminButton);
        }
        final View myView = inflater.inflate(R.layout.content_user_profile, container, false);
        final Button b = (Button) myView.findViewById(R.id.usersButton);
        if (!(new UserManager().isAdmin())) {
            b.setVisibility(View.INVISIBLE);
        } else {
            b.setVisibility(View.VISIBLE);
        }
        final UserManager um = new UserManager();
        final String name = um.getUserName();

        ((TextView) myView.findViewById(R.id.usernameText)).setText(name);
        ((TextView) myView.findViewById(R.id.firstText)).setText(um.findUserFirst(name));
        ((TextView) myView.findViewById(R.id.lastText)).setText(um.findUserLast(name));
        ((TextView) myView.findViewById(R.id.emailText)).setText(um.findUserEmail(name));
        ((TextView) myView.findViewById(R.id.majorText)).setText(um.findUserMajor(name));

        return myView;
    }
}

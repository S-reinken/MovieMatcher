package com.skytalkers.app.moviematcher.controllers.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.UserManager;

/**
 * Created by schuylerreinken on 2/18/16.
 */
public class ProfileFragment extends Fragment {
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.content_primary, container, false);
        return myView;
    }

    /*
    public void onLogoutButtonClick(View v) {
        UserManager um = new UserManager();
        um.logout();
        this.getActivity().finish();
    }

    public void onUsersButtonClick(View v) {
        Intent intent = new Intent(this.getContext(), UserListActivity.class);
        startActivity(intent);
    }

    public void onUserEditButtonClick(View v) {
        Intent intent = new Intent(this.getContext(), EditProfileActivity.class);
        startActivity(intent);
    }

    public void onChangePassButtonClick(View v) {
        Intent intent = new Intent(this.getContext(), ChangePasswordActivity.class);
        startActivity(intent);
    }

    public void onViewProfileButtonClick(View v) {
        UserManager um = new UserManager();
        String user = um.getUserName();
        Intent intent = new Intent(this.getContext(), UserProfileActivity.class);
        intent.putExtra("user_object", user);
        startActivity(intent);
    }
    */
}

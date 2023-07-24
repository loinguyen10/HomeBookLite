package com.united.homebooklite.signActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.united.homebooklite.R;

public class ForgetPassFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forgetpass, container, false);



        return view;
    }

    private void logIn() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.signLayout, new LogInFragment())
                .commit();
    }
}
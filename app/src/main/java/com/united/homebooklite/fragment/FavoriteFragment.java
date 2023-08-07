package com.united.homebooklite.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.united.homebooklite.R;
import com.united.homebooklite.SharedClass;
import com.united.homebooklite.api.InterfacePhp;
import com.united.homebooklite.api.SvrResponse;
import com.united.homebooklite.models.Favorite;
import com.united.homebooklite.models.Room;
import com.united.homebooklite.reservationActivity.ReservationFragment;
import com.united.homebooklite.roomActivity.RoomActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoriteFragment extends Fragment {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    TextView nothing;
    SharedClass dao;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        mapping(view);

        SharedPreferences sP = getActivity().getSharedPreferences("Account_File", MODE_PRIVATE);
        int accId = sP.getInt("Id",0);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        dao.selectVolleyFavorite(getActivity(),recyclerView,accId);


        return view;
    }

    private void mapping(View view) {
        progressBar = view.findViewById(R.id.progressFavorite);
        recyclerView = view.findViewById(R.id.fFavoriteRView);
        nothing = view.findViewById(R.id.txtNothingFavorite);
        dao = new SharedClass();
    }
}
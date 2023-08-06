package com.united.homebooklite.reservationActivity;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.united.homebooklite.R;
import com.united.homebooklite.SharedClass;
import com.united.homebooklite.adapter.PropertyShowAdapter;
import com.united.homebooklite.adapter.ReservationAdapter;
import com.united.homebooklite.models.Account;
import com.united.homebooklite.models.Property;
import com.united.homebooklite.models.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationFragment extends Fragment {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    TextView nothing;
    int accId;
    String email,password;
    SharedClass dao;
    List<Reservation> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservation, container, false);
        mapping(view);
        transfer();

        return view;
    }

    private void mapping(View view) {
        progressBar = view.findViewById(R.id.progressReservation);
        recyclerView = view.findViewById(R.id.fReservationRView);
        nothing = view.findViewById(R.id.txtNothingReservation);
        dao = new SharedClass();
    }

    private void transfer() {
        SharedPreferences sP = getActivity().getSharedPreferences("Account_File", MODE_PRIVATE);
        accId = sP.getInt("Id",0);

        loading();

    }

    private void loading(){
        if(list == null){

        }else{
            progressBar.setVisibility(View.INVISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            dao.selectVolleyReservationAccount(getActivity(),recyclerView,ReservationFragment.this,accId);

            if(list.size() <= 0){

            }
        }
    }

}
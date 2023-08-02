package com.united.homebooklite.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.united.homebooklite.R;
import com.united.homebooklite.adapter.PropertyShowAdapter;
import com.united.homebooklite.api.InterfacePhp;
import com.united.homebooklite.api.SvrResponse;
import com.united.homebooklite.models.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    ToggleButton hotel,motel,homestay,apartment,villa,resort;
    RecyclerView topProperties,mostProperties;
    ProgressBar progressTop,progressMost;
    List<Property> listTop,listMost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mapping(view);
        getTop();
        getMost();

        return view;
    }

    private void mapping(View view){
        hotel = view.findViewById(R.id.toggle_hotel);
        motel = view.findViewById(R.id.toggle_motel);
        homestay = view.findViewById(R.id.toggle_homestay);
        apartment = view.findViewById(R.id.toggle_apartment);
        villa = view.findViewById(R.id.toggle_villa);
        resort = view.findViewById(R.id.toggle_resort);

        topProperties = view.findViewById(R.id.propertyTRView);
        mostProperties = view.findViewById(R.id.propertyMRView);
        progressTop = view.findViewById(R.id.progressTHome);
        progressMost = view.findViewById(R.id.progressMHome);
    }

    private void loading(){
        if(listTop == null || listMost == null){

        }else{
            progressTop.setVisibility(View.INVISIBLE);
            progressMost.setVisibility(View.INVISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
            topProperties.setLayoutManager(linearLayoutManager);
            mostProperties.setLayoutManager(linearLayoutManager1);
            PropertyShowAdapter adapterTop = new PropertyShowAdapter(getContext(), (ArrayList<Property>) listTop);
            PropertyShowAdapter adapterMost = new PropertyShowAdapter(getContext(), (ArrayList<Property>) listMost);
            topProperties.setAdapter(adapterTop);
            mostProperties.setAdapter(adapterMost);

            if(listTop.size() <= 0 || listMost.size() <= 0){

            }
        }
    }

    private void getTop() {
        progressTop.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/property/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfacePhp interfaceSelect = retrofit.create(InterfacePhp.class);
        Call<SvrResponse> call = interfaceSelect.getPropertiesShow();
        call.enqueue(new Callback<SvrResponse>() {

            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseSelect = response.body();
                Log.d("top", svrResponseSelect.getProperties() + "");
                if(svrResponseSelect.getProperties() == null) {
                    progressTop.setVisibility(View.INVISIBLE);
                }else{
                    listTop = new ArrayList<>(Arrays.asList(svrResponseSelect.getProperties()));
                    loading();
                }
            }

            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT);
                Log.d("Message: ", t.getMessage());
                System.out.println(t.getMessage());
                loading();
            }
        });
    }

    private void getMost() {
        progressTop.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/property/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfacePhp interfaceSelect = retrofit.create(InterfacePhp.class);
        Call<SvrResponse> call = interfaceSelect.getPropertiesShow();
        call.enqueue(new Callback<SvrResponse>() {

            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseSelect = response.body();
                if(svrResponseSelect.getProperties() == null) {
                    progressMost.setVisibility(View.INVISIBLE);
                }else{
                    listMost = new ArrayList<>(Arrays.asList(svrResponseSelect.getProperties()));
                    loading();
                }
            }

            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT);
                System.out.println(t.getMessage());
                Log.d("Message: ", t.getMessage());
                loading();
            }
        });
    }


}
package com.united.homebooklite.propertyActivity;

import android.content.Intent;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.united.homebooklite.BGActivity;
import com.united.homebooklite.R;
import com.united.homebooklite.adapter.PropertyAdapter;
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

public class PropertyFragment extends Fragment {

    TextView txtNothing;
    ProgressBar progress;
    RecyclerView recyclerView;
    FloatingActionButton add;
    List<Property> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_property, container, false);

        mapping(view);

        txtNothing.setVisibility(View.INVISIBLE);

        getProperties();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BGActivity.class);
                intent.putExtra("screen","property");
                startActivity(intent);
            }
        });

        return view;
    }

    private void mapping(View view) {
        txtNothing = view.findViewById(R.id.txtNothingProperty);
        recyclerView = view.findViewById(R.id.fPropertyRView);
        add = view.findViewById(R.id.addPropertyBtn);
        progress = view.findViewById(R.id.progressProperty);
    }

    private void loading(){
        if(list == null){

        }else{
            progress.setVisibility(View.INVISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            PropertyAdapter adapter = new PropertyAdapter(getContext(), (ArrayList<Property>) list);
            recyclerView.setAdapter(adapter);

            if(list.size() <= 0){
                txtNothing.setVisibility(View.VISIBLE);
            }
        }
    }

    public void getProperties() {
        progress.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/property/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfacePhp interfaceSelect = retrofit.create(InterfacePhp.class);
        Call<SvrResponse> call = interfaceSelect.getProperties();
        call.enqueue(new Callback<SvrResponse>() {

            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseSelect = response.body();
                if(svrResponseSelect.getProperties() == null) {
                    progress.setVisibility(View.INVISIBLE);
                    txtNothing.setVisibility(View.VISIBLE);
                }else{
                    list = new ArrayList<>(Arrays.asList(svrResponseSelect.getProperties()));
                    loading();
                }
            }

            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT);
                Log.d("Message: ", t.getMessage());
                Log.e("Message: ", t.getMessage());
            }
        });
    }
}
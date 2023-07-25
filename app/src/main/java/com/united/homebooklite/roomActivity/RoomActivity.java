package com.united.homebooklite.roomActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.united.homebooklite.BGActivity;
import com.united.homebooklite.R;
import com.united.homebooklite.adapter.RoomAdapter;
import com.united.homebooklite.api.InterfaceSelectRoom;
import com.united.homebooklite.api.SvrResponse;
import com.united.homebooklite.models.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomActivity extends AppCompatActivity {

    TextView txtNothing;
    ProgressBar progress;
    RecyclerView recyclerView;
    FloatingActionButton add;
    List<Room> list = new ArrayList<>();
    int pId;String pType,ttt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        mapping();

        SharedPreferences sP = getSharedPreferences("Property_File", MODE_PRIVATE);
        pId = sP.getInt("Id", 0);
        pType = sP.getString("Type","");

        txtNothing.setVisibility(View.INVISIBLE);
        getRooms();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (pType){
                    case "hotel":
                    case "motel": {
                        ttt = "hotel";
                        break;
                    }
                    case "homestay":{
                        ttt = "homestay";
                        break;
                    }
                    case "apartment":{
                        ttt = "apartment";
                        break;
                    }
                    case "villa":
                    case "resort": {
                        ttt = "villa";
                        break;
                    }
                }

                Intent intent = new Intent(RoomActivity.this, BGActivity.class);
                intent.putExtra("screen",ttt);
                startActivity(intent);
            }
        });

    }

    private void mapping() {
        txtNothing = findViewById(R.id.txtNothingRoom);
        recyclerView = findViewById(R.id.fRoomRView);
        add = findViewById(R.id.addRoomBtn);
        progress = findViewById(R.id.progressRoom);
    }

    private void loading(){
        if(list == null){

        }else{
            progress.setVisibility(View.INVISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RoomActivity.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            RoomAdapter adapter = new RoomAdapter(RoomActivity.this, (ArrayList<Room>) list);
            recyclerView.setAdapter(adapter);
        }
    }

    public void getRooms() {
        progress.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/room/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceSelectRoom interfaceSelect = retrofit.create(InterfaceSelectRoom.class);
        Call<SvrResponse> call = interfaceSelect.getRooms(pId);
        call.enqueue(new Callback<SvrResponse>() {

            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseSelect = response.body();
                if(svrResponseSelect.getRooms() == null){
                    progress.setVisibility(View.INVISIBLE);
                    txtNothing.setVisibility(View.VISIBLE);
                }else{
                    list = new ArrayList<>(Arrays.asList(svrResponseSelect.getRooms()));
                    loading();
                }
            }

            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Toast.makeText(RoomActivity.this, t.getMessage(), Toast.LENGTH_SHORT);
                Log.d("Message: ", t.getMessage());
                Log.e("Message: ", t.getMessage());
            }
        });
    }
}
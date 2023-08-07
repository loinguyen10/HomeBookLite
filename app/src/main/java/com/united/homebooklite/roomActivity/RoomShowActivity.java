package com.united.homebooklite.roomActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.united.homebooklite.R;
import com.united.homebooklite.SharedClass;
import com.united.homebooklite.adapter.SingleAdapter;
import com.united.homebooklite.api.InterfacePhp;
import com.united.homebooklite.api.SvrResponse;
import com.united.homebooklite.models.Favorite;
import com.united.homebooklite.models.Room;
import com.united.homebooklite.reservationActivity.ReservationActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomShowActivity extends AppCompatActivity {

    String typeS, qualityS, amenitiesS, checkInDate, checkOutDate;
    int propertyIdS, sizeS, bedS, peopleS, roomS, priceS, idS,night,accId;
    TextView type, bed, people, size, price, checkDate;
    RecyclerView amenities;
    Button booking;
    ImageView favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomshow);
        SharedPreferences sP = getSharedPreferences("Account_File", MODE_PRIVATE);
        accId = sP.getInt("Id",0);

        mapping();
        shared();
        transfer();

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RoomShowActivity.this, ReservationActivity.class));
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favorite.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_favorite_off).getConstantState())) {
                    setFavorite();
                } else if (favorite.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_favorite_on).getConstantState())) {
                    deleteFavorite(accId, idS);
                }
            }
        });


    }

    private void shared() {
        SharedPreferences sP = getSharedPreferences("Room_View_File", MODE_PRIVATE);
        SharedPreferences cK = getSharedPreferences("Date_View_File", MODE_PRIVATE);

        typeS = sP.getString("Type", "");
        qualityS = sP.getString("Quality", "");
        propertyIdS = sP.getInt("Property Id", 0);
        sizeS = sP.getInt("Size", 0);
        bedS = sP.getInt("Bed", 0);
        peopleS = sP.getInt("People", 0);
        roomS = sP.getInt("Room", 0);
        amenitiesS = sP.getString("Amenities", "");
        priceS = sP.getInt("Price", 0);
        idS = sP.getInt("Id", 0);

        checkInDate = cK.getString("CheckIn Date", "");
        checkOutDate = cK.getString("CheckOut Date", "");
    }

    private void mapping() {
        type = findViewById(R.id.txtTypeRoomShowA);
        bed = findViewById(R.id.txtBedRoomShowA);
        people = findViewById(R.id.txtPeopleRoomShowA);
        size = findViewById(R.id.txtSizeRoomShowA);
        amenities = findViewById(R.id.rvAmenitiesRoomShowA);
        price = findViewById(R.id.txtPriceRoomShowA);
        booking = findViewById(R.id.bookingButton);
        checkDate = findViewById(R.id.txtCheckDateRoomShowA);
        favorite = findViewById(R.id.txtFavoriteRoomShowA);
    }

    private void transfer() {
        try{
            night = SharedClass.calculate(checkInDate,checkOutDate);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        type.setText(qualityS + " " + typeS + " Room");
        bed.setText(bedS + " giường");
        people.setText(peopleS + " người");
        price.setText((priceS * night) + " VNĐ");
        size.setText(sizeS + " m2");

        String[] amenitiesList = amenitiesS.split(";");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        amenities.setLayoutManager(linearLayoutManager);

        SingleAdapter adapter = new SingleAdapter(this, Arrays.asList(amenitiesList));
        amenities.setAdapter(adapter);

        checkDate.setText("Begin " + checkInDate + " to " + checkOutDate);
    }

    private void setFavorite(){
        Favorite f = new Favorite();

        f.setAccount_id(accId);
        f.setRoom_id(idS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/favorite/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfacePhp interfaceInsert = retrofit.create(InterfacePhp.class);
        Call<SvrResponse> call =
                interfaceInsert.insertFavorite(
                        f.getRoom_id(),
                        f.getAccount_id()
                );
        call.enqueue(new Callback<SvrResponse>() {
            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseInsert = response.body();
                Toast.makeText(RoomShowActivity.this, "Thành công.", Toast.LENGTH_SHORT).show();
                Log.d("Message: ", svrResponseInsert.getMessage());
                favorite.setImageResource(R.drawable.ic_favorite_on);
            }

            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Toast.makeText(RoomShowActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                Log.d("Message: ", t.getMessage());
                Log.e("Message: ", t.getMessage());
            }
        });
    }

    private void deleteFavorite(int accId,int room_id){
        Favorite f = new Favorite();
        f.setRoom_id(room_id);
        f.setAccount_id(accId);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfacePhp interfaceDelete = retrofit.create(InterfacePhp.class);

        Call<SvrResponse> call = interfaceDelete.deleteFavorite(f.getAccount_id(),f.getRoom_id());

        call.enqueue(new Callback<SvrResponse>() {
            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseDelete = response.body();
                if(svrResponseDelete != null){
                    Log.d("delete",svrResponseDelete.getMessage()+"");
                    favorite.setImageResource(R.drawable.ic_favorite_off);
                }
            }

            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Log.d("Message: ", t.getMessage());
            }
        });
    }

}
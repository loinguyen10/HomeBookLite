package com.united.homebooklite.roomActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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
import com.android.volley.toolbox.JsonObjectRequest;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    boolean favoriteXXX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomshow);

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
                //if (favorite.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_favorite_off).getConstantState())) {
                //    setFavorite();
                //} else if (favorite.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_favorite_on).getConstantState())) {
                //    deleteFavorite(accId, idS);
                //}
                System.out.println(favoriteXXX);
                if (favoriteXXX) {
                    Log.d("run","no");
                    deleteFavorite(accId, idS);
                } else {
                    Log.d("run","yes");
                    setFavorite();
                }
            }
        });

    }

    private void shared() {
        SharedPreferences sP = getSharedPreferences("Room_View_File", MODE_PRIVATE);
        SharedPreferences cK = getSharedPreferences("Date_View_File", MODE_PRIVATE);
        SharedPreferences aC = getSharedPreferences("Account_File", MODE_PRIVATE);
        accId = aC.getInt("Id",0);

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
        System.out.println(idS + accId);

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
        favoriteXXX = false;
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

        selectFavorite(RoomShowActivity.this,accId,idS);
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
                favoriteXXX = true;
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
        //f.setRoom_id(room_id);
        //f.setAccount_id(accId);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/favorite/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfacePhp interfaceDelete = retrofit.create(InterfacePhp.class);

        //Call<SvrResponse> call = interfaceDelete.deleteFavorite(f.getAccount_id(),f.getRoom_id());
        Call<SvrResponse> call = interfaceDelete.deleteFavorite(accId,room_id);

        call.enqueue(new Callback<SvrResponse>() {
            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseDelete = response.body();
                if(svrResponseDelete != null){
                    Log.d("delete",svrResponseDelete.getMessage()+"");
                    favorite.setImageResource(R.drawable.ic_favorite_off);
                    favoriteXXX = false;
                }
            }

            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Log.d("Message: ", t.getMessage());
            }
        });
    }

    private void selectFavorite(Context context, int accId, int roomId){
        List<Favorite> list = new ArrayList<>();
        RequestQueue queue1 = Volley.newRequestQueue(context);
        String url1 = "https://lmatmet1234.000webhostapp.com/homebook/favorite/api_getFavorite.php?account_id=" + accId;
        JsonObjectRequest request1 = new JsonObjectRequest(url1, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray favorites = response.getJSONArray("favorites");
                    for (int i = 0; i < favorites.length(); i++) {
                        JSONObject favorite = favorites.getJSONObject(i);
                        int id = favorite.getInt("id");
                        int account_id = favorite.getInt("account_id");
                        int room_id = favorite.getInt("room_id");

                        list.add(new Favorite(id, account_id, room_id));
                    }

                    for(Favorite f : list){
                        if(f.getRoom_id() == roomId){
                            favoriteXXX = true;
                        }
                    }
                    System.out.println(favoriteXXX);
                    if(favoriteXXX){
                        favorite.setImageResource(R.drawable.ic_favorite_on);
                    }else{
                        favorite.setImageResource(R.drawable.ic_favorite_off);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        queue1.add(request1);
    }

}
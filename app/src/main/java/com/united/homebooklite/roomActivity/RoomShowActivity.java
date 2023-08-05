package com.united.homebooklite.roomActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.united.homebooklite.R;
import com.united.homebooklite.SharedClass;
import com.united.homebooklite.adapter.SingleAdapter;
import com.united.homebooklite.reservationActivity.ReservationActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

public class RoomShowActivity extends AppCompatActivity {

    String typeS, qualityS, amenitiesS, checkInDate, checkOutDate;
    int propertyIdS, sizeS, bedS, peopleS, roomS, priceS, idS,night;
    TextView type, bed, people, size, price, checkDate;
    RecyclerView amenities;
    Button booking;

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


}
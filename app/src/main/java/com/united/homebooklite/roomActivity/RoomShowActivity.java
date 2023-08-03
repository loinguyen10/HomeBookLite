package com.united.homebooklite.roomActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.united.homebooklite.R;
import com.united.homebooklite.adapter.SingleAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

public class RoomShowActivity extends AppCompatActivity {

    String typeS, qualityS, amenitiesS, checkInDate, checkOutDate;
    int propertyIdS, sizeS, bedS, peopleS, roomS, priceS, idS;
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

            }
        });


    }

    private void shared() {
        SharedPreferences sP = getSharedPreferences("Room_View_File", MODE_PRIVATE);
        SharedPreferences cK = getSharedPreferences("Reservation_View_File", MODE_PRIVATE);

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
        Log.d("CheckIn Date",checkInDate);
        Log.d("CheckOut Date",checkOutDate);
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
        type.setText(qualityS + " " + typeS + " Room");
        bed.setText(bedS + " giường");
        people.setText(peopleS + " người");
        price.setText(priceS + " VNĐ");
        size.setText(sizeS + " m2");

        String[] amenitiesList = amenitiesS.split(";");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        amenities.setLayoutManager(linearLayoutManager);

        SingleAdapter adapter = new SingleAdapter(this, Arrays.asList(amenitiesList));
        amenities.setAdapter(adapter);

        checkDate.setText("Begin " + checkInDate + " to " + checkOutDate);

        try{
            calculate(checkInDate,checkOutDate);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private int calculate(String date1, String date2) throws ParseException {
        int kq = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date11 = format.parse(date1);
        Date date22 = format.parse(date2);

        Log.d("date1",date11+"");
        Log.d("date2",date22+"");

        kq = (int) (date22.getTime() - date11.getTime());

        Log.d("kq",kq+"");
        return kq;
    }
}
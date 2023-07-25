package com.united.homebooklite.propertyActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.united.homebooklite.R;

public class PropertyShowActivity extends AppCompatActivity {

    ImageView img;
    TextView name,province,address,checkIn,checkOut,description,price;
    Button type,booking;
    RatingBar rating;
    RecyclerView amenitiesRV;
    String nameS,typeS,provinceS,districtS,amenitiesS,addressS,checkS,descriptionS;
    int ratingS,priceS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propertyshow);

        SharedPreferences sP = getSharedPreferences("Property_View_File", MODE_PRIVATE);
        nameS = sP.getString("Fullname","");
        typeS = sP.getString("Type","");
        typeS = sP.getString("Type","");
        provinceS = sP.getString("Province","");
        districtS = sP.getString("District","");
        amenitiesS = sP.getString("Amenities","");
        addressS = sP.getString("Address","");
        checkS = sP.getString("Check","");
        descriptionS = sP.getString("Description","");
        ratingS = sP.getInt("Rating",0);
        priceS = sP.getInt("Price",0);

        mapping();

        String[] checkTime = checkS.split(";");

        name.setText(nameS);
        type.setText(typeS);
        rating.setRating(ratingS);
        province.setText(districtS + ", " + provinceS);
        address.setText(addressS);
        checkIn.setText("After " + checkTime[0]);
        checkOut.setText("Before " + checkTime[1]);
        description.setText(descriptionS);
        price.setText(priceS+" VNĐ");

        Log.d("Time",checkTime[0] + checkTime[1]);

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void mapping(){
        img = findViewById(R.id.imgPropertyShowA);
        name = findViewById(R.id.txtNamePropertyShowA);
        type = findViewById(R.id.txtTypePropertyShowA);
        rating = findViewById(R.id.txtRatingPropertyShowA);
        province = findViewById(R.id.txtProvincePropertyShowA);
        amenitiesRV = findViewById(R.id.rvAmenitiesPropertyA);
        address = findViewById(R.id.txtAddressPropertyShowA);
        checkIn = findViewById(R.id.txtCheckInPropertyShowA);
        checkOut = findViewById(R.id.txtCheckOutPropertyShowA);
        description = findViewById(R.id.txtDescriptionPropertyShowA);
        price = findViewById(R.id.txtPricePropertyShowA);
        booking = findViewById(R.id.bookingButton);
    }


}
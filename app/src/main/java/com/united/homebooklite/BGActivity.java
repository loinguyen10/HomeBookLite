package com.united.homebooklite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.united.homebooklite.propertyActivity.AddPropertyFragment;
import com.united.homebooklite.roomActivity.AddApartmentFragment;
import com.united.homebooklite.roomActivity.AddHomestayFragment;
import com.united.homebooklite.roomActivity.AddHotelFragment;
import com.united.homebooklite.roomActivity.AddVillaFragment;

public class BGActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bg);

        Intent intent = getIntent();
        String screen = intent.getStringExtra("screen");

        findViewById(R.id.exitBGButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if(screen.equals("property")){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameBGLayout, new AddPropertyFragment())
                    .commit();
        }

        if(screen.equals("hotel")){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameBGLayout, new AddHotelFragment())
                    .commit();
        }

        if(screen.equals("homestay")){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameBGLayout, new AddHomestayFragment())
                    .commit();
        }

        if(screen.equals("apartment")){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameBGLayout, new AddApartmentFragment())
                    .commit();
        }

        if(screen.equals("villa")){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameBGLayout, new AddVillaFragment())
                    .commit();
        }
    }

    public void onBackPressed() {
//        Intent intent = new Intent();
//        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
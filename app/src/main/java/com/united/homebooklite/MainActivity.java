package com.united.homebooklite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.united.homebooklite.fragment.BlankFragment;
import com.united.homebooklite.fragment.FavoriteFragment;
import com.united.homebooklite.fragment.HomeFragment;
import com.united.homebooklite.propertyActivity.PropertyFragment;
import com.united.homebooklite.reservationActivity.ReservationFragment;
import com.united.homebooklite.signActivity.SignActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView menuIcon;
    FrameLayout mainView;
    RelativeLayout relativeLayout;
    EditText txtSearchBar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        View header = navigationView.getHeaderView(0);
        TextView txtUsernameHeader = header.findViewById(R.id.txtviewUsername);
        TextView txtCurrentDate = header.findViewById(R.id.tv_currentdate);
        ImageView avatar = header.findViewById(R.id.imageAvatar);

        SharedPreferences sP = getSharedPreferences("Account_File", MODE_PRIVATE);
        String email = sP.getString("Email", "");
        String password = sP.getString("Password", "");
        String role = sP.getString("Role", "");
        String name = sP.getString("Fullname", "");
        String phone = sP.getString("Phone", "");

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.equalsIgnoreCase("")){
                    startActivity(new Intent(MainActivity.this, SignActivity.class));
                }else{

                }
            }
        });

        if(name.equalsIgnoreCase("")){
            txtUsernameHeader.setText("HELLO\nGuest");

        }else{
            txtUsernameHeader.setText("HELLO\n" + name);
        }

        Log.d("",email + " + " + name);

        String dataFo1 = dateFormat.format(date);
        txtCurrentDate.setText(dataFo1);

        replaceFragment(new HomeFragment());

        //cái menu trái
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.dHome);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

//        animateNavigationDrawer();




    }

    private void anhXa(){
        menuIcon = findViewById(R.id.menu_icon);
        mainView = findViewById(R.id.mainView);
        relativeLayout = findViewById(R.id.information);
        txtSearchBar = findViewById(R.id.hSearchBar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        final Fragment blankFragment = new BlankFragment();
        final Fragment homeFragment = new HomeFragment();
        final Fragment propertyFragment = new PropertyFragment();
        final Fragment reservationFragment = new ReservationFragment();
        final Fragment favoriteFragment = new FavoriteFragment();

        switch (item.getItemId()){
            case R.id.dHome:
                replaceFragment(homeFragment);
                txtSearchBar.setVisibility(View.VISIBLE);
                Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                startActivity(refresh);
                overridePendingTransition(0, 0);
                finish();
                break;

            case R.id.dFavorite:
                navigationView.setCheckedItem(R.id.dFavorite);
                replaceFragment(favoriteFragment);
                txtSearchBar.setVisibility(View.GONE);
                drawerLayout.close();
                break;

            case R.id.dReservation:
                navigationView.setCheckedItem(R.id.dReservation);
                replaceFragment(reservationFragment);
                txtSearchBar.setVisibility(View.GONE);
                drawerLayout.close();
                break;

            case R.id.dProperty:
                navigationView.setCheckedItem(R.id.dProperty);
                replaceFragment(propertyFragment);
                txtSearchBar.setVisibility(View.GONE);
                drawerLayout.close();
                break;

            case R.id.dOut:
                startActivity(new Intent(MainActivity.this, SignActivity.class));
                txtSearchBar.setVisibility(View.GONE);
                break;

        }

        return false;
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainView,fragment)
                .commit();
    }


// load ảnh
//    @Override
//    public void onClick(View v) {
//        new SyncImg(this,this)
//                .execute("https://images.gog-statics.com/c75e674590b8947542c809924df30bbef2190341163dd08668e243c266be70c5.jpg");
//    }
//
//    public void onLoadBitmap(Bitmap bitmap) {
//        imgView.setImageBitmap(bitmap);
//        Toast.makeText(this, "Load thanh cong", Toast.LENGTH_SHORT);
//    }
//
//    public void onError() {
//        Toast.makeText(this, "Load that bai", Toast.LENGTH_SHORT);
//    }

}
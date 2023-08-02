package com.united.homebooklite.propertyActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.united.homebooklite.R;
import com.united.homebooklite.adapter.PropertyShowAdapter;
import com.united.homebooklite.adapter.RoomShowAdapter;
import com.united.homebooklite.adapter.SingleAdapter;
import com.united.homebooklite.api.InterfacePhp;
import com.united.homebooklite.api.SvrResponse;
import com.united.homebooklite.models.Property;
import com.united.homebooklite.models.Room;
import com.united.homebooklite.roomActivity.RoomActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PropertyShowActivity extends AppCompatActivity {

    ImageView img;
    TextView name,province,address,checkIn,checkOut,description,price,txtNothing;
    Button type,select;
    RatingBar rating;
    RecyclerView amenitiesRV,roomRV;
    String nameS,typeS,provinceS,districtS,amenitiesS,addressS,checkS,descriptionS;
    int ratingS,priceS,idS;
    ScrollView scrollView;
    LinearLayout bookingLL,roomLL;
    ProgressBar progress;
    TextInputEditText checkInDate,checkOutDate;
    int dD,mM,yY;
    Date date;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    List<Room> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propertyshow);

        SharedPreferences sP = getSharedPreferences("Property_View_File", MODE_PRIVATE);

        nameS = sP.getString("Fullname","");
        typeS = sP.getString("Type","");
        provinceS = sP.getString("Province","");
        districtS = sP.getString("District","");
        amenitiesS = sP.getString("Amenities","");
        addressS = sP.getString("Address","");
        checkS = sP.getString("Check","");
        descriptionS = sP.getString("Description","");
        ratingS = sP.getInt("Rating",0);
        priceS = sP.getInt("Price",0);
        idS = sP.getInt("Id",0);

        mapping();

        String[] checkTime = checkS.split(";");

        String[] amenitiesList = amenitiesS.split(";");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        amenitiesRV.setLayoutManager(linearLayoutManager);

        SingleAdapter adapter = new SingleAdapter(this, Arrays.asList(amenitiesList));

        getRooms();

        name.setText(nameS);
        type.setText(typeS);
        rating.setRating(ratingS);
        province.setText(districtS + ", " + provinceS);
        address.setText(addressS);
        checkIn.setText("After " + checkTime[0]);
        checkOut.setText("Before " + checkTime[1]);
        description.setText(descriptionS);
        price.setText(priceS+" VNĐ");
        amenitiesRV.setAdapter(adapter);

        Log.d("Time",checkTime[0] + checkTime[1]);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.smoothScrollTo(0,roomLL.getTop());
            }
        });

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if(scrollView.getScrollY() >= roomLL.getTop() - scrollView.getHeight()){
                    bookingLL.setVisibility(View.GONE);
                }else{
                    bookingLL.setVisibility(View.VISIBLE);
                }

            }
        });

        checkInDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDate(checkInDate,"CheckIn");
            }
        });

        checkOutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDate(checkOutDate,"CheckOut");
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
        checkInDate = findViewById(R.id.txtCheckInDatePropertyShowA);
        checkOutDate = findViewById(R.id.txtCheckOutDatePropertyShowA);
        description = findViewById(R.id.txtDescriptionPropertyShowA);
        price = findViewById(R.id.txtPricePropertyShowA);
        select = findViewById(R.id.selectButton);
        scrollView = findViewById(R.id.scrollViewPropertyShowA);
        roomLL = findViewById(R.id.roomPropertyShowA);
        bookingLL = findViewById(R.id.bookingPropertyShowA);
        progress = findViewById(R.id.progressPRoom);
        roomRV = findViewById(R.id.roomPRView);
        txtNothing = findViewById(R.id.txtNothingRoomP);
    }

    private void loading(){
        if(list == null){

        }else{
            progress.setVisibility(View.INVISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            roomRV.setLayoutManager(linearLayoutManager);
            roomRV.setHasFixedSize(true);
            RoomShowAdapter adapter = new RoomShowAdapter(this, (ArrayList<Room>) list);
            roomRV.setAdapter(adapter);

            if(list.size() <= 0){

            }
        }
    }

    private void getRooms() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/room/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfacePhp interfaceSelect = retrofit.create(InterfacePhp.class);
        Call<SvrResponse> call = interfaceSelect.getRooms(idS);
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
                Toast.makeText(PropertyShowActivity.this, t.getMessage(), Toast.LENGTH_SHORT);
                Log.d("Message: ", t.getMessage());
            }
        });
    }

    private void checkDate(TextInputEditText editText, String txt){
        SharedPreferences cK = getSharedPreferences("Reservation_View_File", MODE_PRIVATE);
        SharedPreferences.Editor editor = cK.edit();
        DatePickerDialog.OnDateSetListener chonDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                yY = year; mM = month; dD = dayOfMonth;
                GregorianCalendar gC = new GregorianCalendar(yY,mM,dD);
                editText.setText(format.format(gC.getTime()));
                editor.putString( txt + " Date",format.format(gC.getTime()));
                date = gC.getTime();
            }
        };

        Calendar calendar = Calendar.getInstance();
        yY = calendar.get(Calendar.YEAR);
        mM = calendar.get(Calendar.MONTH);
        dD = calendar.get(Calendar.DATE);

        DatePickerDialog d = new DatePickerDialog(PropertyShowActivity.this,0,chonDate,yY,mM,dD);
        d.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
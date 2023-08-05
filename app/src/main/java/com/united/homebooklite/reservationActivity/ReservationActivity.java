package com.united.homebooklite.reservationActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.united.homebooklite.R;
import com.united.homebooklite.SharedClass;

public class ReservationActivity extends AppCompatActivity {

    TextInputEditText nameCustomer,phoneCustomer,emailCustomer,IdCustomer,
            nameProperty,addressProperty,typeRoom,numberRoom,numberCustomer,
            checkInDate,checkOutDate,numberNight,totalCost;
    Button booking;
    String nameCustomerS,phoneCustomerS,emailCustomerS,namePropertyS,addressPropertyS,typeRoomS,checkInDateS,checkOutDateS;
    int idCustomerS,idPropertyS,idRoomS,numberNightS,priceRoomS;
    SharedClass sharedClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        mapping();
        transfer();

        numberRoom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(TextUtils.isEmpty(numberRoom.getText().toString().trim())){
                    totalCost.setText("0 VNĐ");
                }else{
                    totalCost.setText((priceRoomS * numberNightS * Integer.valueOf(numberRoom.getText().toString())) + " VNĐ");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmAlert();
            }
        });

    }

    private void mapping(){
        nameCustomer = findViewById(R.id.txtNameCustomerReservationUp);
        phoneCustomer = findViewById(R.id.txtPhoneCustomerReservationUp);
        emailCustomer = findViewById(R.id.txtEmailCustomerReservationUp);
        IdCustomer = findViewById(R.id.txtIDCustomerReservationUp);
        nameProperty = findViewById(R.id.txtNamePropertyReservationUp);
        addressProperty = findViewById(R.id.txtAddressPropertyReservationUp);
        typeRoom = findViewById(R.id.txtTypeRoomReservationUp);
        numberRoom = findViewById(R.id.txtNumberRoomReservationUp);
        numberCustomer = findViewById(R.id.txtNumberCustomerReservationUp);
        checkInDate = findViewById(R.id.txtCheckInDateReservationUp);
        checkOutDate = findViewById(R.id.txtCheckOutDateReservationUp);
        numberNight = findViewById(R.id.txtNumberNightReservationUp);
        totalCost = findViewById(R.id.txtPriceReservationUp);
        booking = findViewById(R.id.bookingReservationUp);
        sharedClass = new SharedClass();
    }

    private void transfer(){
        SharedPreferences cK = getSharedPreferences("Reservation_View_File", MODE_PRIVATE);
        SharedPreferences dT = getSharedPreferences("Date_View_File", MODE_PRIVATE);
        nameCustomerS = cK.getString("NameCustomer","");
        phoneCustomerS = cK.getString("PhoneCustomer","");
        emailCustomerS = cK.getString("EmailCustomer","");
        idCustomerS = cK.getInt("IdCustomer",0);

        namePropertyS = cK.getString("NameProperty","");
        addressPropertyS = cK.getString("AddressProperty","");
        idPropertyS = cK.getInt("IdProperty",0);

        typeRoomS = cK.getString("TypeRoom","");
        priceRoomS = cK.getInt("PriceRoom",0);
        idRoomS = cK.getInt("IdRoom",0);

        checkInDateS = dT.getString("CheckIn Date", "");
        checkOutDateS = dT.getString("CheckOut Date", "");

        try{
            numberNightS = SharedClass.calculate(checkInDateS,checkOutDateS);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        nameCustomer.setText(nameCustomerS);
        phoneCustomer.setText(phoneCustomerS);
        emailCustomer.setText(emailCustomerS);
        IdCustomer.setText(idCustomerS+"");
        nameProperty.setText(namePropertyS);
        addressProperty.setText(addressPropertyS);
        typeRoom.setText(typeRoomS);
        numberRoom.setText("1");
        numberCustomer.setText("1");
        checkInDate.setText(checkInDateS);
        checkOutDate.setText(checkOutDateS);
        numberNight.setText(numberNightS+"");
        totalCost.setText((priceRoomS * numberNightS) + " VNĐ");

    }

    private void confirmAlert(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Confirm?");
        dialog.setMessage("Are you sure?");

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sharedClass.insertVolleyReservation(
                        ReservationActivity.this,
                        idCustomerS+"",
                        idRoomS+"",
                        checkInDateS,
                        checkOutDateS,
                        numberRoom.getText().toString(),
                        numberCustomer+"",
                        totalCost.getText().toString(),
                        "0");
            }
        });

        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.create().dismiss();
            }
        });

        AlertDialog alert = dialog.create();
        alert.show();
    }
}
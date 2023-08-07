package com.united.homebooklite.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.united.homebooklite.R;
import com.united.homebooklite.SharedClass;
import com.united.homebooklite.api.InterfacePhp;
import com.united.homebooklite.api.PropertyInterface;
import com.united.homebooklite.api.RoomInterface;
import com.united.homebooklite.api.SvrResponse;
import com.united.homebooklite.models.Property;
import com.united.homebooklite.models.Reservation;
import com.united.homebooklite.models.Room;
import com.united.homebooklite.roomActivity.RoomActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    Context context;
    ArrayList<Reservation> list;
    List<Room> listRoom;
    List<Property> listProperty;
    int night;
    //    PropertyFragment fragment;
    LayoutInflater inflater;
    String namePropertyX, addressPropertyX, typeRoomX;
    int roomP_IdX;


    public ReservationAdapter(Context context, ArrayList<Reservation> list, LayoutInflater inflater) {
        this.context = context;
        this.list = list;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ReservationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item, parent, false);
        View v = inflater.inflate(R.layout.reservation_item, parent, false);
        return new ReservationAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationAdapter.ViewHolder holder, int position) {
        SharedClass dao = new SharedClass();
        listRoom = new ArrayList<>();

        Reservation r = list.get(position);

        getRoom(r.getRoom_id());


        //typeRoomX = room.getQuality() + " " + room.getType();
        //roomP_IdX = room.getProperty_id();
        //namePropertyX = property.getName();
        //addressPropertyX = property.getAddress() + ", " + property.getDistrict() + ", " + property.getProvince();

        SharedPreferences sP = context.getSharedPreferences("Account_File", MODE_PRIVATE);
        String accEmail = sP.getString("Email", "");
        int accId = sP.getInt("Id", 0);
        String accName = sP.getString("Fullname", "");
        String accPhone = sP.getString("Phone", "");
        night = 0;

        try {
            night = dao.calculate(r.getCheckin_date(), r.getCheckout_date());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        switch (r.getStatus()) {
            case 0:
                holder.status.setText("Prepare");
                holder.status.setBackgroundResource(R.color.orange);
                break;
            case 1:
                holder.status.setText("In Progress");
                holder.status.setBackgroundResource(R.color.green);
                break;
            case 2:
                holder.status.setText("Done");
                holder.status.setBackgroundResource(R.color.white);
                break;
            case 3:
                holder.status.setText("Cancel");
                holder.status.setBackgroundResource(R.color.gray);
                break;
        }

        holder.typeroom.setText(typeRoomX);
        holder.property.setText(namePropertyX);
        holder.checkindate.setText(r.getCheckin_date());
        holder.numbernight.setText(night + "");
        holder.cost.setText(r.getCost() + " VNĐ");

        holder.seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                View v = inflater.inflate(R.layout.reservation_show_item, null);
                dialog.setView(v);
                TextInputEditText nameCustomer = v.findViewById(R.id.txtNameCustomerReservationShow);
                TextInputEditText phoneCustomer = v.findViewById(R.id.txtPhoneCustomerReservationShow);
                TextInputEditText emailCustomer = v.findViewById(R.id.txtEmailCustomerReservationShow);
                TextInputEditText IdCustomer = v.findViewById(R.id.txtIDCustomerReservationShow);
                TextInputEditText nameProperty = v.findViewById(R.id.txtNamePropertyReservationShow);
                TextInputEditText addressProperty = v.findViewById(R.id.txtAddressPropertyReservationShow);
                TextInputEditText typeRoom = v.findViewById(R.id.txtTypeRoomReservationShow);
                TextInputEditText numberRoom = v.findViewById(R.id.txtNumberRoomReservationShow);
                TextInputEditText numberCustomer = v.findViewById(R.id.txtNumberCustomerReservationShow);
                TextInputEditText checkInDate = v.findViewById(R.id.txtCheckInDateReservationShow);
                TextInputEditText checkOutDate = v.findViewById(R.id.txtCheckOutDateReservationShow);
                TextInputEditText numberNight = v.findViewById(R.id.txtNumberNightReservationShow);
                TextInputEditText totalCost = v.findViewById(R.id.txtPriceReservationShow);

                nameCustomer.setText(accName);
                phoneCustomer.setText(accPhone);
                emailCustomer.setText(accEmail);
                IdCustomer.setText(accId + "");
                nameProperty.setText(namePropertyX);
                addressProperty.setText(addressPropertyX);
                typeRoom.setText(typeRoomX);
                numberRoom.setText(r.getRoom() + "");
                numberCustomer.setText(r.getPeople() + "");
                checkInDate.setText(r.getCheckin_date());
                checkOutDate.setText(r.getCheckout_date());
                numberNight.setText(night + "");
                totalCost.setText(r.getCost() + " VNĐ");

                dialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.create().dismiss();
                        AlertDialog.Builder dialog1 = new AlertDialog.Builder(context);
                        dialog1.setTitle("Confirm?");
                        dialog1.setMessage("Are you sure?");

                        dialog1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Update Reservation
                            }
                        });

                        dialog1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog1.create().dismiss();
                            }
                        });

                        AlertDialog alert1 = dialog1.create();
                        alert1.show();
                    }
                });

                AlertDialog alert = dialog.create();
                alert.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView status, typeroom, property, checkindate, numbernight, cost, seemore;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            status = itemView.findViewById(R.id.txtStatusReservationItem);
            typeroom = itemView.findViewById(R.id.txtTypeRoomReservationItem);
            property = itemView.findViewById(R.id.txtPropertyReservationItem);
            checkindate = itemView.findViewById(R.id.txtCheckInReservationItem);
            numbernight = itemView.findViewById(R.id.txtNumberNightReservationItem);
            cost = itemView.findViewById(R.id.txtCostReservationItem);
            seemore = itemView.findViewById(R.id.seemoreReservationItem);
            layout = itemView.findViewById(R.id.reservationItem);
        }
    }

    private void getRoom(int pId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/room/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfacePhp interfaceSelect = retrofit.create(InterfacePhp.class);
        Call<SvrResponse> call = interfaceSelect.get1Room(pId);
        call.enqueue(new Callback<SvrResponse>() {

            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseSelect = response.body();
                listRoom = new ArrayList<>(Arrays.asList(svrResponseSelect.getRooms()));
                loading();
            }

            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT);
                Log.d("Message: ", t.getMessage());
                Log.e("Message: ", t.getMessage());
                getRoom(pId);
            }
        });
    }

    private void getProperty(int pId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/property/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfacePhp interfaceSelect = retrofit.create(InterfacePhp.class);
        Call<SvrResponse> call = interfaceSelect.get1Room(pId);
        call.enqueue(new Callback<SvrResponse>() {

            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseSelect = response.body();
                listProperty = new ArrayList<>(Arrays.asList(svrResponseSelect.getProperties()));
            }

            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT);
                Log.d("Message: ", t.getMessage());
                Log.e("Message: ", t.getMessage());
                getProperty(pId);
            }
        });
    }

    private void loading() {
        if (listRoom == null && listProperty == null) {

        } else if (listRoom != null) {
            getProperty(listRoom.get(0).getProperty_id());
        }
    }
}

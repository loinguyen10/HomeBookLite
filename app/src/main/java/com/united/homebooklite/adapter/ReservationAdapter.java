package com.united.homebooklite.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.united.homebooklite.R;
import com.united.homebooklite.SharedClass;
import com.united.homebooklite.models.Account;
import com.united.homebooklite.models.Property;
import com.united.homebooklite.models.Reservation;
import com.united.homebooklite.models.Room;

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    Context context;
    ArrayList<Reservation> list;
    int night;
    //    PropertyFragment fragment;
    LayoutInflater inflater;


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

        Reservation r = list.get(position);
        Room room = dao.get1Room(context, r.getRoom_id());
        Property property = dao.get1Property(context, room.getProperty_id());

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

        holder.status.setText(r.getStatus());
        holder.typeroom.setText(room.getQuality() + " " + room.getType());
        holder.property.setText(property.getName());
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
                nameProperty.setText(property.getName());
                addressProperty.setText(property.getAddress() + ", " + property.getDistrict() + ", " + property.getProvince());
                typeRoom.setText(room.getQuality() + " " + room.getType());
                numberRoom.setText(r.getRoom() + "");
                numberCustomer.setText(r.getPeople());
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
}

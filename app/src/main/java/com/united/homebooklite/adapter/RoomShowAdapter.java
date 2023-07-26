package com.united.homebooklite.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.united.homebooklite.R;
import com.united.homebooklite.models.Room;

import java.util.ArrayList;
import java.util.Arrays;

public class RoomShowAdapter extends RecyclerView.Adapter<RoomShowAdapter.ViewHolder> {

    Context context;
    ArrayList<Room> list;
//    PropertyFragment fragment;


    public RoomShowAdapter(Context context, ArrayList<Room> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RoomShowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_show_item, parent, false);
        return new RoomShowAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomShowAdapter.ViewHolder holder, int position) {
        Room r = list.get(position);
        holder.type.setText(r.getQuality() + " " + r.getType());
        holder.size.setText(r.getSize() +" m2");
        holder.bed.setText(r.getBed() + " giường");
        holder.people.setText(r.getPeople() + " người");
        holder.price.setText(r.getPrice() + " VNĐ");
        holder.oldPrice.setText(r.getPrice() + " VNĐ");

        String[] amenitiesList = r.getAmenities().split(";");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        holder.amenitiesRV.setLayoutManager(linearLayoutManager);

        SingleAdapter adapter = new SingleAdapter(context, Arrays.asList(amenitiesList));
        holder.amenitiesRV.setAdapter(adapter);

        if(holder.oldPrice.getText().equals(holder.price.getText())){
            Log.d("price","true");
            holder.oldPrice.setVisibility(View.INVISIBLE);
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView type, size, bed, people, price, oldPrice;
        ImageView img;
        RecyclerView amenitiesRV;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgRoomShowItem);
            type = itemView.findViewById(R.id.txtTypeRoomShowItem);
            bed = itemView.findViewById(R.id.txtBedRoomShowItem);
            size = itemView.findViewById(R.id.txtSizeRoomShowItem);
            people = itemView.findViewById(R.id.txtPeopleRoomShowItem);
            price = itemView.findViewById(R.id.txtPriceRoomShowItem);
            amenitiesRV = itemView.findViewById(R.id.rvAmenitiesRoomShowItem);
            oldPrice = itemView.findViewById(R.id.txtOldPriceRoomShowItem);
            layout = itemView.findViewById(R.id.roomShowItem);
        }
    }
}

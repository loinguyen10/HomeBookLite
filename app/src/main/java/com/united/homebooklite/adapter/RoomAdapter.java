package com.united.homebooklite.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.united.homebooklite.R;
import com.united.homebooklite.models.Property;
import com.united.homebooklite.models.Room;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    Context context;
    ArrayList<Room> list;
//    PropertyFragment fragment;


    public RoomAdapter(Context context, ArrayList<Room> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RoomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item, parent, false);
        return new RoomAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAdapter.ViewHolder holder, int position) {
        Room r = list.get(position);
        holder.type.setText(r.getQuality() + " " + r.getType());
        holder.bed.setText(r.getBed() + " giường");
        holder.people.setText(r.getPeople() + " người");
        holder.price.setText(r.getPrice() + " VNĐ");
        holder.oldPrice.setText(r.getPrice() + " VNĐ");

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
        TextView type, bed, people, price, oldPrice;
        ImageView img;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgRoomItem);
            type = itemView.findViewById(R.id.txtTypeRoomItem);
            bed = itemView.findViewById(R.id.txtBedRoomItem);
            people = itemView.findViewById(R.id.txtPeopleRoomItem);
            price = itemView.findViewById(R.id.txtPriceRoomItem);
            oldPrice = itemView.findViewById(R.id.txtOldPriceRoomItem);
            layout = itemView.findViewById(R.id.roomItem);
        }
    }
}

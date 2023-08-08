package com.united.homebooklite.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.united.homebooklite.BGActivity;
import com.united.homebooklite.R;
import com.united.homebooklite.models.Property;
import com.united.homebooklite.models.Room;
import com.united.homebooklite.roomActivity.RoomShowActivity;

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
                Intent intent = new Intent(context, BGActivity.class);
                intent.putExtra("screen","editHotel");
                context.startActivity(intent);
                SharedPreferences sP = context.getSharedPreferences("Room_Update_File", MODE_PRIVATE);
                SharedPreferences.Editor editor = sP.edit();
                editor.putString("Type", r.getType());
                editor.putString("Quality", r.getQuality());
                editor.putInt("Property Id", r.getProperty_id());
                editor.putInt("Size", r.getSize());
                editor.putInt("Bed", r.getBed());
                editor.putInt("People", r.getPeople());
                editor.putInt("Room", r.getRoom());
                editor.putString("Amenities",r.getAmenities());
                editor.putInt("Price", r.getPrice());
                editor.putInt("Id", r.getId());
                editor.putInt("Available", r.getAvailable());
                editor.commit();
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

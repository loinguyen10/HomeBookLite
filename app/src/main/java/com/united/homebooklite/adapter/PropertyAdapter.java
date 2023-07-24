package com.united.homebooklite.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.united.homebooklite.R;
import com.united.homebooklite.models.Property;
import com.united.homebooklite.propertyActivity.PropertyFragment;
import com.united.homebooklite.roomActivity.RoomActivity;

import java.util.ArrayList;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {

    Context context;
    ArrayList<Property> list;
//    PropertyFragment fragment;


    public PropertyAdapter(Context context, ArrayList<Property> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PropertyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_item, parent, false);
        return new PropertyAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyAdapter.ViewHolder holder, int position) {
        Property p = list.get(position);
        holder.name.setText(p.getName());
        holder.type.setText(p.getType());
        holder.address.setText(p.getAddress() + ",\n" + p.getDistrict() + ", " + p.getProvince());
        holder.ratingBar.setRating(p.getRating());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, RoomActivity.class));
                SharedPreferences sP = context.getSharedPreferences("Property_File", MODE_PRIVATE);
                SharedPreferences.Editor editor = sP.edit();
                editor.putString("Fullname", p.getName());
                editor.putInt("Id", p.getId());
                editor.putString("Type", p.getType());
                editor.commit();
            }
        });

//        int rate = p.getRating();

//        for (int i = 0; i < holder.star.length; i++) {
//            holder.star[i].setVisibility(View.GONE);
//        }
//
//        for (int i = 0; i < holder.star.length; i++) {
//            if (rate == 0) {
//                holder.star[i].setVisibility(View.GONE);
//            } else {
//                holder.star[i].setVisibility(View.VISIBLE);
//                if (i == rate - 1) {
//                    break;
//                }
//            }
//        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, address;
        Button type;
        ImageView img;
//        ImageView[] star = new ImageView[5];
        RatingBar ratingBar;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgPropertyItem);
            name = itemView.findViewById(R.id.txtNamePropertyItem);
            type = itemView.findViewById(R.id.txtTypePropertyItem);
            address = itemView.findViewById(R.id.txtAddressPropertyItem);
            ratingBar = itemView.findViewById(R.id.txtRatingPropertyItem);
            layout = itemView.findViewById(R.id.propertyItem);
//            star[0] = itemView.findViewById(R.id.starPropertyItem1);
//            star[1] = itemView.findViewById(R.id.starPropertyItem2);
//            star[2] = itemView.findViewById(R.id.starPropertyItem3);
//            star[3] = itemView.findViewById(R.id.starPropertyItem4);
//            star[4] = itemView.findViewById(R.id.starPropertyItem5);

        }
    }
}

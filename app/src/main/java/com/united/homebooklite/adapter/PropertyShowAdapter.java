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
import androidx.recyclerview.widget.RecyclerView;

import com.united.homebooklite.R;
import com.united.homebooklite.models.Property;
import com.united.homebooklite.propertyActivity.PropertyShowActivity;
import com.united.homebooklite.roomActivity.RoomActivity;

import java.util.ArrayList;

public class PropertyShowAdapter extends RecyclerView.Adapter<PropertyShowAdapter.ViewHolder> {

    Context context;
    ArrayList<Property> list;


    public PropertyShowAdapter(Context context, ArrayList<Property> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PropertyShowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_show_item, parent, false);
        return new PropertyShowAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyShowAdapter.ViewHolder holder, int position) {
        Property p = list.get(position);
        holder.name.setText(p.getName());
        holder.type.setText(p.getType());
        holder.address.setText(p.getAddress() + ",\n" + p.getDistrict() + ", " + p.getProvince());
        holder.price.setText(p.getPrice() + " VNĐ");
        holder.ratingBar.setRating(p.getRating());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, PropertyShowActivity.class));
                SharedPreferences sP = context.getSharedPreferences("Property_View_File", MODE_PRIVATE);
                SharedPreferences.Editor editor = sP.edit();
                editor.putString("Fullname", p.getName());
                editor.putString("Type", p.getType());
                editor.putString("District", p.getDistrict());
                editor.putString("Province", p.getProvince());
                editor.putString("Description", p.getDescription());
                editor.putString("Amenities",p.getAmenities());
                editor.putString("Address", p.getAddress());
                editor.putInt("Price", p.getPrice());
                editor.putString("Check", p.getCheck());
                editor.putInt("Id", p.getId());
                editor.putInt("Rating", p.getRating());
                editor.commit();

                SharedPreferences cK = context.getSharedPreferences("Reservation_View_File", MODE_PRIVATE);
                SharedPreferences.Editor editorCK = cK.edit();

                editorCK.putString("NameProperty",p.getName());
                editorCK.putString("AddressProperty",p.getAddress() + ", " + p.getDistrict() + ", " + p.getProvince());
                editorCK.putInt("IdProperty",p.getId());

                editorCK.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, address,price;
        Button type;
        ImageView img;
        RatingBar ratingBar;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgPropertyShowItem);
            name = itemView.findViewById(R.id.txtNamePropertyShowItem);
            type = itemView.findViewById(R.id.txtTypePropertyShowItem);
            address = itemView.findViewById(R.id.txtAddressPropertyShowItem);
            ratingBar = itemView.findViewById(R.id.txtRatingPropertyShowItem);
            price = itemView.findViewById(R.id.txtPricePropertyShowItem);
            layout = itemView.findViewById(R.id.propertyShowItem);

        }
    }
}

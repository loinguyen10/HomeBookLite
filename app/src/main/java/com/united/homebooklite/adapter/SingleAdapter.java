package com.united.homebooklite.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.united.homebooklite.R;
import com.united.homebooklite.models.District;
import com.united.homebooklite.models.Room;

import java.util.ArrayList;
import java.util.List;

public class SingleAdapter extends RecyclerView.Adapter<SingleAdapter.ViewHolder> {

    Context context;
    List<String> list;


    public SingleAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SingleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        return new SingleAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleAdapter.ViewHolder holder, int position) {
        holder.txt.setText(list.get(position));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.text1);
        }
    }
}

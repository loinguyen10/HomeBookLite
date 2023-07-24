package com.united.homebooklite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.united.homebooklite.R;
import com.united.homebooklite.models.Country;
import com.united.homebooklite.models.District;

import java.util.List;

public class DistrictAdapter extends ArrayAdapter<District> {
    private LayoutInflater inflater;
    private List<District> districtList;

    public DistrictAdapter(Context context, List<District> districtList) {
        super(context, R.layout.single_item, districtList);
        this.inflater = LayoutInflater.from(context);
        this.districtList = districtList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.single_item, parent, false);
        TextView textView = view.findViewById(R.id.text1);
        textView.setText(districtList.get(position).getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

}

package com.united.homebooklite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.united.homebooklite.R;
import com.united.homebooklite.models.Province;

import java.util.List;

public class ProvinceAdapter extends ArrayAdapter<Province> {
    private LayoutInflater inflater;
    private List<Province> provinceList;

    public ProvinceAdapter(Context context, List<Province> provinceList) {
        super(context, R.layout.single_item, provinceList);
        this.inflater = LayoutInflater.from(context);
        this.provinceList = provinceList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.single_item, parent, false);
        TextView textView = view.findViewById(R.id.text1);
        textView.setText(provinceList.get(position).getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

}

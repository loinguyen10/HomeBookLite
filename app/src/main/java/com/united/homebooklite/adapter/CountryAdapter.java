package com.united.homebooklite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.united.homebooklite.R;
import com.united.homebooklite.models.Country;

import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {
    private LayoutInflater inflater;
    private List<Country> countryList;

    public CountryAdapter(Context context, List<Country> countryList) {
        super(context, R.layout.single_item, countryList);
        this.inflater = LayoutInflater.from(context);
        this.countryList = countryList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        TextView textView = view.findViewById(R.id.text1);
        textView.setText(countryList.get(position).getNameVN());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

}

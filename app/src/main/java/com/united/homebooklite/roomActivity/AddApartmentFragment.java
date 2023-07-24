package com.united.homebooklite.roomActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.united.homebooklite.R;

import java.util.ArrayList;

public class AddApartmentFragment extends Fragment {

    Spinner typeS,qualityS;
    TextInputEditText property,size,bed,room,people,amenities,price;
    Button create;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addapartment, container, false);

        mapping(view);

        ArrayList<String> category = new ArrayList<>();
        category.add("Single");
        category.add("Double");
        category.add("Twin");
        category.add("Triple");
        category.add("Quad");
        category.add("Resort");
        ArrayAdapter adapterCategory = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, category);
        typeS.setAdapter(adapterCategory);

        return view;
    }

    private void mapping(View view){
        typeS = view.findViewById(R.id.typeRoomUp);
        qualityS = view.findViewById(R.id.qualityRoomUp);
        property = view.findViewById(R.id.propertyRoomUp);
        size = view.findViewById(R.id.sizeRoomUp);
        bed = view.findViewById(R.id.bedRoomUp);
        room = view.findViewById(R.id.roomRoomUp);
        people = view.findViewById(R.id.peopleRoomUp);
        amenities = view.findViewById(R.id.amenitiesRoomUp);
        price = view.findViewById(R.id.priceRoomUp);
        create = view.findViewById(R.id.createRoomUp);
    }
}
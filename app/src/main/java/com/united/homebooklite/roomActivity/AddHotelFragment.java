package com.united.homebooklite.roomActivity;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.united.homebooklite.R;
import com.united.homebooklite.api.InterfaceInsertProperty;
import com.united.homebooklite.api.InterfaceInsertRoom;
import com.united.homebooklite.api.SvrResponse;
import com.united.homebooklite.models.District;
import com.united.homebooklite.models.Property;
import com.united.homebooklite.models.Province;
import com.united.homebooklite.models.Room;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddHotelFragment extends Fragment {

    Spinner typeS,qualityS;
    TextInputEditText property,size,bed,room,people,amenities,price;
    Button create;
    String pName;int pId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addhotel, container, false);

        mapping(view);

        SharedPreferences sP = getActivity().getSharedPreferences("Property_File", MODE_PRIVATE);
        pName = sP.getString("Fullname", "");
        pId = sP.getInt("Id", 0);

        property.setText(pName);

        ArrayList<String> category = new ArrayList<>();
        category.add("Single");
        category.add("Double");
        category.add("Twin");
        category.add("Triple");
        category.add("Quad");
        ArrayAdapter adapterCategory = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, category);
        typeS.setAdapter(adapterCategory);

        ArrayList<String> qualityA = new ArrayList<>();
        qualityA.add("Standard");
        qualityA.add("Superior");
        qualityA.add("Deluxe");
        qualityA.add("Executive");
        qualityA.add("Luxury");
        qualityA.add("Premium");
        ArrayAdapter adapterQuality = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, qualityA);
        qualityS.setAdapter(adapterQuality);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRoom();
            }
        });

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

    private void addRoom() {
        Room room = new Room();

        room.setQuality(((String) qualityS.getSelectedItem()));
        room.setProperty_id(pId);
        room.setAmenities(amenities.getText().toString());
        room.setSize(Integer.parseInt(size.getText().toString()));
        room.setType(((String) typeS.getSelectedItem()));
        room.setBed(Integer.parseInt(bed.getText().toString()));
        room.setPeople(Integer.parseInt(people.getText().toString()));
        room.setPrice(Integer.parseInt(price.getText().toString()));
        room.setRoom(1);
        room.setAvailable(0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/room/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceInsertRoom interfaceInsert = retrofit.create(InterfaceInsertRoom.class);
        Call<SvrResponse> call =
                interfaceInsert.insertRoom(
                        room.getQuality(),
                        room.getType(),
                        room.getSize(),
                        room.getBed(),
                        room.getPeople(),
                        room.getRoom(),
                        room.getAmenities(),
                        room.getPrice(),
                        room.getProperty_id(),
                        room.getAvailable()
                );
        call.enqueue(new Callback<SvrResponse>() {
            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseInsert = response.body();
                Toast.makeText(getActivity(), "Thành công.", Toast.LENGTH_SHORT);
                Log.d("Message: ", svrResponseInsert.getMessage());
                getActivity().finish();
            }

            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Thất bại", Toast.LENGTH_SHORT);
                Log.d("Message: ", t.getMessage());
                Log.e("Message: ", t.getMessage());
            }
        });
    }
}
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
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.united.homebooklite.R;
import com.united.homebooklite.api.InterfacePhp;
import com.united.homebooklite.api.SvrResponse;
import com.united.homebooklite.models.Room;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditHotelFragment extends Fragment {
    Spinner typeS, qualityS;
    Switch availableS;
    TextInputEditText property, size, bed, room, people, amenities, price;
    Button update;
    String pName;
    int pId,idX;
    String typeX,qualityX,propertyX,amenitiesX;
    int sizeX,bedX,peopleX,priceX,availableX;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_hotel, container, false);

        mapping(view);
        transfer();

        SharedPreferences sP = getActivity().getSharedPreferences("Property_File", MODE_PRIVATE);
        pName = sP.getString("Fullname", "");
        pId = sP.getInt("Id", 0);

        property.setText(pName);

        ArrayList<String> typeA = new ArrayList<>();
        typeA.add("Single");
        typeA.add("Double");
        typeA.add("Twin");
        typeA.add("Triple");
        typeA.add("Quad");
        ArrayAdapter adapterCategory = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, typeA);
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

        for(int i = 0; i< typeA.size();i++){
            if(typeX.equals(typeA.get(i))){
                typeS.setSelection(i);
            }
        }

        for(int i = 0; i< qualityA.size();i++){
            if(qualityX.equals(qualityA.get(i))){
                qualityS.setSelection(i);
            }
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRoom();
            }
        });

        return view;
    }

    private void mapping(View view) {
        typeS = view.findViewById(R.id.typeEditRoom);
        qualityS = view.findViewById(R.id.qualityEditRoom);
        property = view.findViewById(R.id.propertyEditRoom);
        size = view.findViewById(R.id.sizeEditRoom);
        bed = view.findViewById(R.id.bedEditRoom);
        //room = view.findViewById(R.id.roomEditRoom);
        availableS = view.findViewById(R.id.availableEditRoom);
        people = view.findViewById(R.id.peopleEditRoom);
        amenities = view.findViewById(R.id.amenitiesEditRoom);
        price = view.findViewById(R.id.priceEditRoom);
        update = view.findViewById(R.id.updateEditRoom);
    }

    private void transfer(){
        SharedPreferences sP = getActivity().getSharedPreferences("Room_Update_File", MODE_PRIVATE);
        idX = sP.getInt("Id",0);
        typeX = sP.getString("Type","");
        qualityX = sP.getString("Quality","");
        sizeX = sP.getInt("Size",0);
        bedX = sP.getInt("Bed",0);
        peopleX = sP.getInt("People",0);
        amenitiesX = sP.getString("Amenities","");
        priceX = sP.getInt("Price",0);
        availableX = sP.getInt("Available",0);

        size.setText(sizeX+"");
        bed.setText(bedX+"");
        people.setText(peopleX+"");
        amenities.setText(amenitiesX);
        price.setText(priceX+"");
        if(availableX == 0){
            availableS.setChecked(false);
        }else{
            availableS.setChecked(true);
        }
    }
    private void updateRoom() {
        int available = 0;

        if(availableS.isChecked()){
            available = 1;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/room/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfacePhp interfaceUpdate = retrofit.create(InterfacePhp.class);

        Call<SvrResponse> call =
                interfaceUpdate.updateRoom(
                        idX,
                        (String) qualityS.getSelectedItem(),
                        (String) typeS.getSelectedItem(),
                        Integer.parseInt(size.getText().toString()),
                        Integer.parseInt(bed.getText().toString()),
                        Integer.parseInt(people.getText().toString()),
                        1,
                        amenities.getText().toString(),
                        Integer.parseInt(price.getText().toString()),
                        available
                );

        call.enqueue(new Callback<SvrResponse>() {

            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseUpdate = response.body();//lay noi dung server tra ve
                Toast.makeText(getActivity(), "Thành công.", Toast.LENGTH_SHORT).show();
                Log.d("Message: ", svrResponseUpdate.getMessage());
                getActivity().finish();
            }

            //that bai
            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Log.d("Message: ", t.getMessage());
            }
        });
    }
}
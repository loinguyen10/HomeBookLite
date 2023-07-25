package com.united.homebooklite.propertyActivity;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.united.homebooklite.R;
import com.united.homebooklite.adapter.CountryAdapter;
import com.united.homebooklite.adapter.DistrictAdapter;
import com.united.homebooklite.adapter.ProvinceAdapter;
import com.united.homebooklite.api.InterfacePhp;
import com.united.homebooklite.api.SvrResponse;
import com.united.homebooklite.models.Country;
import com.united.homebooklite.models.District;
import com.united.homebooklite.models.Property;
import com.united.homebooklite.models.Province;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddPropertyFragment extends Fragment {

    TextInputEditText name, address, description, amenities, owner;
    Button createP;
    Spinner countryS, provinceS, districtS, typeS;
    RatingBar rating;
//    List<Country> countryList = new ArrayList<>();
//    List<Country> countryList2 = new ArrayList<>();
    List<Province> provinceList = new ArrayList<>();
    List<Province> provinceList2 = new ArrayList<>();
    List<District> districtList = new ArrayList<>();
    List<District> districtList2 = new ArrayList<>();

    CountryAdapter countryAdapter;
    ProvinceAdapter provinceAdapter;
    DistrictAdapter districtAdapter;
    String accName;
    int accId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addproperty, container, false);

        SharedPreferences sP = getActivity().getSharedPreferences("Account_File", MODE_PRIVATE);
        accName = sP.getString("Fullname", "");
        accId = sP.getInt("Id", 0);

//        getCountry();
        getProvince();
        getDistrict();

        mapping(view);

        Log.d("acc", accId + "-" + accName);
        if (accName.equals("")) {

        } else {
            owner.setText(accName);
        }

        ArrayList<String> category = new ArrayList<>();
        category.add("Hotel");
        category.add("Motel");
        category.add("Homestay");
        category.add("Apartment");
        category.add("Villa");
        category.add("Resort");
        ArrayAdapter adapterCategory = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, category);
        typeS.setAdapter(adapterCategory);



//        countryS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Country country = countryList.get(i);
//                provinceList2.clear();
//                for (Province province : provinceList) {
//                    if (province.getCountry_id().equals(country.getId())) {
//                        provinceList2.add(province);
//                    }
//                }
////                Collections.sort(provinceList2, new Comparator<Province>() {
////                    @Override
////                    public int compare(Province p1, Province p2) {
////                        return p1.getName().compareTo(p2.getName());
////                    }
////                });
//                provinceAdapter = new ProvinceAdapter(getActivity(), provinceList2);
//                provinceS.setAdapter(provinceAdapter);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        provinceS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Province province = provinceList.get(i);
                districtList2.clear();
                for (District district : districtList) {
                    if (district.getProvince_id().equals(province.getId())) {
                        districtList2.add(district);
                    }
                }

//                Collections.sort(districtList2, new Comparator<District>() {
//                    @Override
//                    public int compare(District d1, District d2) {
//                        return d1.getName().compareTo(d2.getName());
//                    }
//                });

                districtAdapter = new DistrictAdapter(getActivity(), districtList2);
                districtS.setAdapter(districtAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        createP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProperty();
            }
        });

        return view;
    }

    private void mapping(View view) {
        name = view.findViewById(R.id.namePropertyUp);
        address = view.findViewById(R.id.addressPropertyUp);
        description = view.findViewById(R.id.descriptionPropertyUp);
        amenities = view.findViewById(R.id.amenitiesPropertyUp);
        owner = view.findViewById(R.id.ownerPropertyUp);
        createP = view.findViewById(R.id.createPropertyUp);
//        countryS = view.findViewById(R.id.countryPropertyUp);
        provinceS = view.findViewById(R.id.provincePropertyUp);
        districtS = view.findViewById(R.id.districtPropertyUp);
        rating = view.findViewById(R.id.starPropertyUp);
        typeS = view.findViewById(R.id.typePropertyUp);
    }

    private void getCountry() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/address/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        InterfaceSelectCountry interfaceSelect = retrofit.create(InterfaceSelectCountry.class);
//        Call<SvrResponse> call = interfaceSelect.getCountries();
//        call.enqueue(new Callback<SvrResponse>() {
//            @Override
//            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
//                SvrResponse svrResponseSelect = response.body();
//                Log.d("Country", svrResponseSelect + "");
//                countryList = new ArrayList<>(Arrays.asList(svrResponseSelect.getCountries()));
//                loading();
//            }
//
//            @Override
//            public void onFailure(Call<SvrResponse> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT);
//                Log.d("Message: ", t.getMessage());
//                Log.e("Message: ", t.getMessage());
//            }
//        });
    }

    private void getDistrict() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/address/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfacePhp interfaceSelect = retrofit.create(InterfacePhp.class);
        Call<SvrResponse> call = interfaceSelect.getDistricts();
        call.enqueue(new Callback<SvrResponse>() {
            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseSelect = response.body();
                Log.d("District", svrResponseSelect + "");
                districtList = new ArrayList<>(Arrays.asList(svrResponseSelect.getDistricts()));
                loading();
            }

            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT);
                Log.d("Message: ", t.getMessage());
                Log.e("Message: ", t.getMessage());
            }
        });
    }

    private void getProvince() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/address/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfacePhp interfaceSelect = retrofit.create(InterfacePhp.class);
        Call<SvrResponse> call = interfaceSelect.getProvinces();
        call.enqueue(new Callback<SvrResponse>() {

            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseSelect = response.body();
                Log.d("Province", svrResponseSelect + "");
                provinceList = new ArrayList<>(Arrays.asList(svrResponseSelect.getProvinces()));
                loading();
            }

            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT);
                Log.d("Message: ", t.getMessage());
                Log.e("Message: ", t.getMessage());
            }
        });
    }

    private void loading() {
        if (provinceList == null || districtList == null) {
//            loading();
        } else {
            provinceAdapter = new ProvinceAdapter(getActivity(), provinceList);
            districtAdapter = new DistrictAdapter(getActivity(), districtList);
            provinceS.setAdapter(provinceAdapter);
            districtS.setAdapter(districtAdapter);
        }
    }

    private void addProperty() {
        Property property = new Property();
//        Country country = (Country) countryS.getSelectedItem();
        Province province = (Province) provinceS.getSelectedItem();
        District district = (District) districtS.getSelectedItem();

        property.setName(name.getText().toString());
        property.setAddress(address.getText().toString());
        property.setAmenities(amenities.getText().toString());
        property.setDescription(description.getText().toString());
        property.setType(((String) typeS.getSelectedItem()).toLowerCase());
        property.setRating((int) rating.getRating());
        property.setOwner_id(accId);
        property.setCountry("VN");
        property.setProvince(province.getName());
        property.setDistrict(district.getName());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/property/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfacePhp interfaceInsert = retrofit.create(InterfacePhp.class);
        Call<SvrResponse> call =
                interfaceInsert.insertProperty(
                        property.getName(),
                        property.getDescription(),
                        property.getType(),
                        property.getAddress(),
                        property.getAmenities(),
                        property.getCountry(),
                        property.getProvince(),
                        property.getDistrict(),
                        property.getRating(),
                        property.getOwner_id()
                );
        call.enqueue(new Callback<SvrResponse>() {
            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseInsert = response.body();
                Toast.makeText(getActivity(), "Thành công.", Toast.LENGTH_SHORT);
                Log.d("Message: ", svrResponseInsert.getMessage());
                Intent intent = new Intent();
                getActivity().setResult(Activity.RESULT_OK, intent);
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
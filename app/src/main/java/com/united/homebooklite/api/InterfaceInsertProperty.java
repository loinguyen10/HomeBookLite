package com.united.homebooklite.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceInsertProperty {
    @FormUrlEncoded
    @POST("api_createProperty.php")
    Call<SvrResponse> insertProperty(
            @Field("name") String name,
            @Field("description") String description,
            @Field("type") String type,
            @Field("address") String address,
            @Field("amenities") String amenities,
            @Field("country") String country,
            @Field("province") String province,
            @Field("district") String district,
            @Field("rating") int rating,
            @Field("owner_id") int owner_id
    );
}

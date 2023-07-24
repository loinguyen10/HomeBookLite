package com.united.homebooklite.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceInsertRoom {
    @FormUrlEncoded
    @POST("api_createRoom.php")
    Call<SvrResponse> insertRoom(
            @Field("quality") String quality,
            @Field("type") String type,
            @Field("size") int size,
            @Field("bed") int bed,
            @Field("people") int people,
            @Field("room") int room,
            @Field("amenities") String amenities,
            @Field("price") int price,
            @Field("property_id") int property_id,
            @Field("available") int available
    );
}

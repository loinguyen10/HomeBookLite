package com.united.homebooklite.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfacePhp {

    //account
    @GET("api_loginAccount.php")
    Call<SvrResponse> getAccounts(
            @Query("email") String email,
            @Query("password") String password
    );

    @FormUrlEncoded
    @POST("api_insertAccount.php")
    Call<SvrResponse> insertAccount(
            @Field("email") String email,
            @Field("password") String password,
            @Field("role") String role,
            @Field("name") String name,
            @Field("phone") String phone
    );

    //room
    @GET("api_getRoom.php")
    Call<SvrResponse> getRooms(
            @Query("property_id") int property_id
    );

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

    //properties
    @GET("api_getProperty.php")
    Call<SvrResponse> getProperties(
    );

    @GET("api_getPropertyShow.php")
    Call<SvrResponse> getPropertiesShow(
    );

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
            @Field("check_time") String check,
            @Field("owner_id") int owner_id
    );

    @GET("api_getCountry.php")
    Call<SvrResponse> getCountries(
    );

    @GET("api_getProvince.php")
    Call<SvrResponse> getProvinces(
    );

    @GET("api_getDistrict.php")
    Call<SvrResponse> getDistricts(
    );

    @FormUrlEncoded
    @POST("api_createReservation.php")
    Call<SvrResponse> insertReservation(
            @Field("account_id") int account_id,
            @Field("room_id") int room_id,
            @Field("checkin_date") String checkin_date,
            @Field("checkout_date") String checkout_date,
            @Field("people") int people,
            @Field("room") int room,
            @Field("cost") int cost,
            @Field("status") int status
    );

}

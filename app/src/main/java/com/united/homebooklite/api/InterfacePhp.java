package com.united.homebooklite.api;

import com.united.homebooklite.models.Property;
import com.united.homebooklite.models.Reservation;
import com.united.homebooklite.models.Room;

import java.util.List;

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

    @FormUrlEncoded
    @POST("api_updateRoom.php")
    Call<SvrResponse> updateRoom(
            @Field("id") int id,
            @Field("quality") String quality,
            @Field("type") String type,
            @Field("size") int size,
            @Field("bed") int bed,
            @Field("people") int people,
            @Field("room") int room,
            @Field("amenities") String amenities,
            @Field("price") int price,
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

    @GET("api_get1Room.php")
    Call<SvrResponse> get1Room(
            @Query("id") int id
    );

    @GET("api_get1Property.php")
    Call<SvrResponse> get1Property(
            @Query("id") int id
    );

    @FormUrlEncoded
    @POST("api_createFavorite.php")
    Call<SvrResponse> insertFavorite(
            @Field("room_id") int room_id,
            @Field("account_id") int account_id
    );

    @FormUrlEncoded
    @POST("api_deleteFavorite.php")
    Call<SvrResponse> deleteFavorite(
            @Field("account_id") int account_id,
            @Field("room_id") int room_id
    );

}

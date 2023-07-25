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


    //properties
    @GET("api_getProperty.php")
    Call<SvrResponse> getProperties(
    );

    @GET("api_getPropertyShow.php")
    Call<SvrResponse> getPropertiesShow(
    );

}

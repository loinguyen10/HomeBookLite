package com.united.homebooklite.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceInsertAccount {
    @FormUrlEncoded
    @POST("api_insertAccount.php")
    Call<SvrResponse> insertAccount(
            @Field("email") String email,
            @Field("password") String password,
            @Field("role") String role,
            @Field("name") String name,
            @Field("phone") String phone
    );
}

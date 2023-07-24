package com.united.homebooklite.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceSelectAccount {
    @GET("api_loginAccount.php")
    Call<SvrResponse> getAccounts(
            @Query("email") String email,
            @Query("password") String password
    );

}

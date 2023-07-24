package com.united.homebooklite.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceSelectCountry {
    @GET("api_getCountry.php")
    Call<SvrResponse> getCountries(
    );

}

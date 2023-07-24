package com.united.homebooklite.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceSelectProvince {
    @GET("api_getProvince.php")
    Call<SvrResponse> getProvinces(
    );

}

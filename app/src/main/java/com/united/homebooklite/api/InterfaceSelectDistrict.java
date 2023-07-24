package com.united.homebooklite.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceSelectDistrict {
    @GET("api_getDistrict.php")
    Call<SvrResponse> getDistricts(
    );

}

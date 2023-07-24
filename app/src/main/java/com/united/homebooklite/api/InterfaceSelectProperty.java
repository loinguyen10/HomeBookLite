package com.united.homebooklite.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceSelectProperty {
    @GET("api_getProperty.php")
    Call<SvrResponse> getProperties(
    );

}

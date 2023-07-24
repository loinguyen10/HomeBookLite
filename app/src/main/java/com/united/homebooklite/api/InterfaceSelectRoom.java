package com.united.homebooklite.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceSelectRoom {
    @GET("api_getRoom.php")
    Call<SvrResponse> getRooms(
            @Query("property_id") int property_id
    );

}

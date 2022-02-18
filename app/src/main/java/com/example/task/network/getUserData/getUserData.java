package com.example.task.network.getUserData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface getUserData {
    @GET("users")
    Call<ApiResponse> get(@Query("page") int page);
}

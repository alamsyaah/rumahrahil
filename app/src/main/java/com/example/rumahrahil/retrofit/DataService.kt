package com.example.rumahrahil.retrofit

import com.example.rumahrahil.retrofit.responses.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface DataService {

    @FormUrlEncoded
    @POST("auth/process")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>
}
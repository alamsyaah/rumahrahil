package com.example.rumahrahil.retrofit

import com.example.rumahrahil.retrofit.responses.*
import retrofit2.Call
import retrofit2.http.*

interface DataService {

    @FormUrlEncoded
    @POST("api/auth/process")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("api/kelas")
    fun getKelas(
        @Query("id_kelas") id_kelas: String,
        @Header("Authorization") token: String
    ): Call<ClassResponse>

    @GET("api/bab")
    fun getBab(
        @Query("mapel_id") mapel_id: String,
        @Header("Authorization") token: String
    ): Call<BabResponse>

    @GET("api/mapel")
    fun getMapel(
        @Query("id") id: String,
        @Header("Authorization") token: String
    ): Call<MapelResponse>

    @GET("api/soal")
    fun getSoal(
        @Query("mapel_id") mapel_id: String,
        @Query("paket_id") paket_id: String,
        @Header("Authorization") token: String
    ): Call<QuestionResponse>

}
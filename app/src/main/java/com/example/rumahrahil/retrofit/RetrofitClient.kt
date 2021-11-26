package com.example.rumahrahil.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    fun apiRequest(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rumahrahileducation.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
package com.example.fetchexercise.Helpers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//API helper
object RetrofitHelper {
    val baseUrl = "https://fetch-hiring.s3.amazonaws.com/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
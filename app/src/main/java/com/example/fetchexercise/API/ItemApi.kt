package com.example.fetchexercise.API

import com.example.fetchexercise.Models.ItemModel
import retrofit2.http.GET

//Interface to handle API calls
interface ItemApi {
    @GET("/hiring.json")
    suspend fun getItems(): List<ItemModel>
}
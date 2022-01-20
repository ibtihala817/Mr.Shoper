package com.example.mrshopercapstone.Api

import com.example.mrshopercapstone.models.items.ItemModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


// this is the function for the Api that I use in the App
// Interfaces that define the possible HTTP operations
interface ShoppingApi {
   @GET("/products")
       suspend fun getItem():Response<List<ItemModel>>
}
package com.example.mrshopercapstone.Api

import com.example.mrshopercapstone.models.items.ItemModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ShoppingApi {
   @GET("/products")
       suspend fun getItem():Response<List<ItemModel>>
}
package com.example.mrshopercapstone.Api

import com.example.mrshopercapstone.models.items.ItemModel
import retrofit2.Response
import retrofit2.http.GET

interface ShoppingApi {
   @GET("/fakestoreapi.com")
       suspend fun getItem() : Response<ItemModel>
}
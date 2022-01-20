package com.example.mrshopercapstone.Api

import com.example.mrshopercapstone.models.items.CartModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

// this is the functions that I use for geting the Api in the app
interface CartApi {
    @GET("/ItemModel5")
    suspend fun getMyCart(
    ):Response<List<CartModel>>

    @POST("/ItemModel5")
    suspend fun addMyCart(
        @Body MyCartBody: CartModel):Response<ResponseBody>

    @PUT("/ItemModel5/{id}")
    suspend fun editMyCart(@Path("id")Id : String,
    @Body MyCartBody: CartModel): Response<CartModel>

    @DELETE("/ItemModel5/{id}")
    suspend fun deleteCart(@Path("id") Id: String): Response<ResponseBody>
}
package com.example.mrshopercapstone.Repository

import com.example.mrshopercapstone.Api.CartApi
import com.example.mrshopercapstone.models.items.CartModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


private const val BASE_URL = "https://61af599e3e2aba0017c491f6.mockapi.io"
class ApiCartRepositoryService {
    //  Builder API
    private val retrofitService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitApi = retrofitService.create(CartApi::class.java)

    suspend fun getMyCart() = retrofitApi.getMyCart()
    suspend fun addMyCart(MyCartBody: CartModel) = retrofitApi.addMyCart(MyCartBody)
    suspend fun editMyCart(id:String,MyCartBody: CartModel) = retrofitApi.editMyCart(id,MyCartBody)
    suspend fun deleteCart(id: String) = retrofitApi.deleteCart(id)
}
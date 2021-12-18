package com.example.mrshopercapstone.Repository

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

private const val BASE_URL = "https://fakestoreapi.com"

// repository allow to access to multiple data sources
class ApiRepositoryService (val context: Context){

    // this is to use the Gson library
    private val retrofitService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    // this is to coordinate actions across the system and it is for restricts the instantiation of a class to one single instance
    companion object {
        private var instance: ApiRepositoryService?= null
        fun init(context: Context){
            if (instance == null)
                instance = ApiRepositoryService(context)
        }

        fun get(): ApiRepositoryService{
            return instance?: throw Exception("ApiRepositoryService must be initialized")
        }
    }
}

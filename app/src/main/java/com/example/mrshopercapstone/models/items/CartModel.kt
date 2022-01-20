package com.example.mrshopercapstone.models.items


import com.google.gson.annotations.SerializedName

data class CartModel(
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    var price: Int,
    @SerializedName("count")
    var count: Int,
    @SerializedName("title")
    val title: String
)
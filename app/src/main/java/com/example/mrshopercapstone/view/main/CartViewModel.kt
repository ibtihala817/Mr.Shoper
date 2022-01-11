package com.example.mrshopercapstone.view.main

import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mrshopercapstone.Repository.ApiCartRepositoryService
import com.example.mrshopercapstone.models.items.CartModel
import com.example.mrshopercapstone.models.items.ItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "CartViewModel"
class CartViewModel(): ViewModel() {
    private val apiRepo = ApiCartRepositoryService()
    val myCartLiveData = MutableLiveData<List<CartModel>>()

    val editLiveData = MutableLiveData<String>()
    val deleteLiveData = MutableLiveData<String>()




    fun callMyCart(){

        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = apiRepo.getMyCart()
                if (response.isSuccessful){
                    response.body()?.run {
                        Log.d(TAG, this.toString())
                        myCartLiveData.postValue(this)
                    }
                } else{
                    Log.d(TAG, response.message())
                }
            } catch (e: Exception){
                Log.d(TAG, e.message.toString())
            }
        }
    }
    fun editMyCart(MyCartBody : CartModel){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = apiRepo.editMyCart(MyCartBody.id , MyCartBody)
                if (response.isSuccessful){
                    response.body()?.run {
                        Log.d(TAG,this.toString())
                        editLiveData.postValue("successful")
                    }
                }
            }catch (e: Exception){
                Log.d(TAG, e.message.toString())
            }
        }

    }
    fun deleteMyCart(cartModel: CartModel){
        viewModelScope.launch (Dispatchers.IO){
            val response = apiRepo.deleteCart(cartModel.id)
            if (response.isSuccessful){
                response.body()?.run {
                    Log.d(TAG, this.toString())
                    deleteLiveData.postValue("successful")
                }
            }
        }
    }
    fun addMyCart(myCartBody : ItemModel){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = apiRepo.addMyCart(CartModel(myCartBody.id.toString(),myCartBody.image,myCartBody.price.toInt(),1,myCartBody.title))
                if (response.isSuccessful){
                    response.body()?.run {
                        Log.d(TAG,this.toString())

                    }
                }else{
                    Log.d(TAG,response.message())

                }
            }catch (e: java.lang.Exception){
                Log.d(TAG, e.message.toString())

            }
        }
    }
}
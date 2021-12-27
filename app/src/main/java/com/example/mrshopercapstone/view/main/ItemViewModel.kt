package com.example.mrshopercapstone.view.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mrshopercapstone.Repository.ApiCartRepositoryService
import com.example.mrshopercapstone.Repository.ApiRepositoryService
import com.example.mrshopercapstone.models.items.CartModel
import com.example.mrshopercapstone.models.items.ItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


private const val TAG = "ItemViewModel"
class ItemViewModel: ViewModel() {
    // getting instance from api service repository with companion object function
    private val apiRepo = ApiRepositoryService.get()
    private val cartApiRepo = ApiCartRepositoryService()
    val itemLiveData = MutableLiveData<List<ItemModel>>()
    val itemErrorLiveData = MutableLiveData<String>()
    val deleteErrorLiveData = MutableLiveData<String>()
    val addLiveData = MutableLiveData<String>()
    var selectedItemMutableLiveData = MutableLiveData<ItemModel>()

    fun callItems(){
        viewModelScope.launch(Dispatchers.IO){
           // use try and catch for handling http exceptions
            try {
                //calling the API methods and handle the result
                val response= apiRepo.getItem()
                if (response.isSuccessful){
                    response.body()?.run {
                        Log.d(TAG,this.toString())
                        // send response to view
                        //TODO
                        itemLiveData.postValue(this)
                        Log.d(TAG,"response success ${response.message()}")
                    }
                } else{
                    Log.d(TAG,response.message())
                    // send error response to view
                    itemErrorLiveData.postValue(response.message())


                }
            } catch (e: Exception){
                Log.d(TAG, e.message.toString())
                itemErrorLiveData.postValue(e.message.toString())
            }
        }
    }
    fun addMyCart(MyCartBody : CartModel){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = cartApiRepo.addMyCart(MyCartBody)
                if (response.isSuccessful){
                    response.body()?.run {
                        Log.d(TAG,this.toString())
                        addLiveData.postValue(this.toString())
                        Log.d(TAG,"response success ${response.message()}")
                    }
                }else{
                    Log.d(TAG,response.message())
                    deleteErrorLiveData.postValue(response.message())
                }
            }catch (e: Exception){
                Log.d(TAG, e.message.toString())
                deleteErrorLiveData.postValue(e.message.toString())
            }
        }
    }
}
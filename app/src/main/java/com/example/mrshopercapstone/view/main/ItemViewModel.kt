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
    val itemLiveData = MutableLiveData<List<ItemModel>>()
    val itemErrorLiveData = MutableLiveData<String>()
    var selectedItemMutableLiveData = MutableLiveData<ItemModel>()

    var id = -1
    var image = ""
    var price = 0
    var title = ""


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
}
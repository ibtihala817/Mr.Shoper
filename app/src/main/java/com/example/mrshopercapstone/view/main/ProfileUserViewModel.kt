package com.example.mrshopercapstone.view.main

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mrshopercapstone.Repository.UserProfileRepositoryService
import com.example.mrshopercapstone.models.items.UserProfile
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "ProfileUserViewModel"
class ProfileUserViewModel: ViewModel() {
    val saveUserLiveData = MutableLiveData<UserProfile>()
    val getUserLiveData = MutableLiveData<UserProfile>()
    val deleteUserLiveData = MutableLiveData<String>()
    val userErrorLiveData = MutableLiveData<String>()


    private val apiRepo = UserProfileRepositoryService.get()
    private var firestore: FirebaseFirestore

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

    }

    /////////////////////////////////////////////////////
    ////for saving user data////
    fun save(userProfile: UserProfile){
        viewModelScope.launch(Dispatchers.IO){
            try {
             apiRepo.saveProfile(userProfile).addOnSuccessListener {
                 Log.d(TAG, "document saved")
                 saveUserLiveData.postValue(userProfile)
             }.addOnFailureListener {
                 userErrorLiveData.postValue("")
                 Log.d(TAG, it.message.toString())


             }

            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                userErrorLiveData.postValue(e.message.toString())
            }
        }
    }

    /////////////////////////////////////////////////////
    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                apiRepo.getUser().addOnSuccessListener { documentSnapshot ->
                    val user = documentSnapshot.toObject<UserProfile>(UserProfile::class.java)
                    getUserLiveData.postValue(user!!)
                    Log.d("Firebase", "document saved")
                }.addOnFailureListener {
                    userErrorLiveData.postValue("")

                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                userErrorLiveData.postValue(e.message.toString())
            }
        }
    }
    /////////////////////////////////////////////////////////
}
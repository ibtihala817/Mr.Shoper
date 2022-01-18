package com.example.mrshopercapstone.view.main

import android.content.ContentValues
import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mrshopercapstone.R
import com.example.mrshopercapstone.databinding.FragmentUserProfileBinding
import com.example.mrshopercapstone.models.items.UserProfile

private const val TAG = "UserProfileFragment"
class UserProfileFragment : Fragment() {
    private val profileUserViewModel: ProfileUserViewModel by activityViewModels()
    private lateinit var binding: FragmentUserProfileBinding
    private var userProfile = UserProfile()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
//        observer()
//        Log.d(TAG,"UserProfileFragment")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.EditImageButton.setOnClickListener {
            if (binding.EditImageButton.isChecked) {
//
                binding.FirstNameEditText.isEnabled = true
                binding.LastNameEditText.isEnabled = true
                binding.EmailIdEditText.isEnabled = true
                binding.PhoneEditText.isEnabled = true

            } else {
//                binding.deleteaccount.isVisible = false
//                binding.profilPicture.isEnabled = false
                binding.FirstNameEditText.isEnabled = false
                binding.LastNameEditText.isEnabled = false
                binding.EmailIdEditText.isEnabled = false
                binding.PhoneEditText.isEnabled = false
              saveEdit() // save change
            }
//            binding.saveProfileButton.setOnClickListener {
//            saveEdit() // save change
////            }
        }
        observer()
        profileUserViewModel.getUser()
    }


//            binding.logoutButton.setOnClickListener {
//                findNavController().navigate(R.id.action_userProfileFragment_to_itemFragment4)
//            }
//        }
//    }

    fun saveEdit() {
        userProfile.apply {
            FirstName = binding.FirstNameEditText.text.toString()
            LastName = binding.LastNameEditText.text.toString()
            Email = binding.EmailIdEditText.text.toString()
            PhoneNun = binding.PhoneEditText.text.toString()
            profileUserViewModel.save(this)
//        profileUserViewModel.getUser(userProfile = UserProfile())
        }
    }
    fun observer(){
        profileUserViewModel.getUserLiveData.observe(viewLifecycleOwner, {
            binding.FirstNameEditText.setText(it.FirstName)
            binding.LastNameEditText.setText(it.LastName)
            binding.EmailIdEditText.setText(it.Email)
            binding.PhoneEditText.setText(it.PhoneNun)
            Log.d(ContentValues.TAG, it.toString())
        })
        profileUserViewModel.saveUserLiveData.observe(viewLifecycleOwner,{

        })
        profileUserViewModel.deleteUserLiveData.observe(viewLifecycleOwner,{

        })
    }
}


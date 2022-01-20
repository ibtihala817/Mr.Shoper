package com.example.mrshopercapstone.view.main

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate
import com.example.mrshopercapstone.R
import com.example.mrshopercapstone.databinding.FragmentUserProfileBinding
import com.example.mrshopercapstone.models.identity.LoginActivity
import com.example.mrshopercapstone.models.identity.shareEditor
import com.example.mrshopercapstone.models.identity.sharePref
import com.example.mrshopercapstone.models.items.UserProfile
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth

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

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val localizationDelegate = LocalizationActivityDelegate(requireActivity())
        binding.EditImageButton.setOnClickListener {
            if (binding.EditImageButton.isChecked) {
                binding.FirstNameEditText.isEnabled = true
                binding.LastNameEditText.isEnabled = true
                binding.EmailIdEditText.isEnabled = true
                binding.PhoneEditText.isEnabled = true

            } else {
                binding.FirstNameEditText.isEnabled = false
                binding.LastNameEditText.isEnabled = false
                binding.EmailIdEditText.isEnabled = false
                binding.PhoneEditText.isEnabled = false
              saveEdit() // save change
            }

////            binding  binding.engButton.setOnClickListener{
////                localizationDelegate.setLanguage(requireContext(),"en")
////
////            }
//            binding.ar.setOnClickListener {
//                localizationDelegate.setLanguage(requireContext(),"ar")
//            }
        }

        binding.logoutButton.setOnClickListener {
            Log.d(TAG,"Click")
            // for the dialog
            MaterialAlertDialogBuilder(
                requireActivity(),android.R.style.ThemeOverlay_Material_Dialog_Alert
            )
                .setMessage("Are you sure you want to logout?")
                .setNegativeButton("No"){
                        _,_ ->
                }
                .setPositiveButton("yes"){
                        _,_ ->

                    FirebaseAuth.getInstance().signOut()
                    sharePref = requireActivity().getSharedPreferences(SHARED_PREF_FILE,Context.MODE_PRIVATE)
                    shareEditor = sharePref.edit()
                    shareEditor.clear()
                    shareEditor.commit()
                    val intent = Intent(requireActivity(),LoginActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }.show()

//                FirebaseAuth.getInstance().signOut()
//                sharePref = requireActivity().getSharedPreferences(SHARED_PREF_FILE,Context.MODE_PRIVATE)
//                shareEditor = sharePref.edit()
//                shareEditor.clear()
//                shareEditor.commit()
//                val intent = Intent(requireActivity(),LoginActivity::class.java)
//                startActivity(intent)
//                requireActivity().finish()


        }
        binding.EnglishradioButton.setOnClickListener {
            localizationDelegate.setLanguage(requireContext(),"en")
        }
        binding.ArabicRadioButton.setOnClickListener {
            localizationDelegate.setLanguage(requireContext(),"ar")
        }

        observer()
        profileUserViewModel.getUser()
    }

    /////////////////////////////////
    fun saveEdit() {
        userProfile.apply {
            FirstName = binding.FirstNameEditText.text.toString()
            LastName = binding.LastNameEditText.text.toString()
            Email = binding.EmailIdEditText.text.toString()
            PhoneNun = binding.PhoneEditText.text.toString()
            profileUserViewModel.save(this)

        }
    }
    ///////////////////////////////
    fun observer(){
        profileUserViewModel.getUserLiveData.observe(viewLifecycleOwner, {
            binding.FirstNameEditText.setText(it.FirstName)
            binding.LastNameEditText.setText(it.LastName)
            binding.EmailIdEditText.setText(it.Email)
            binding.PhoneEditText.setText(it.PhoneNun)
            Log.d(ContentValues.TAG, it.toString())
        })
//        profileUserViewModel.saveUserLiveData.observe(viewLifecycleOwner,{
//
//        })
//        profileUserViewModel.deleteUserLiveData.observe(viewLifecycleOwner,{
//
//        })
    }
    ///////////////////////////
}


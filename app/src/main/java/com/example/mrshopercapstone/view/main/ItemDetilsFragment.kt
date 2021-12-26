package com.example.mrshopercapstone.view.main

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mrshopercapstone.R
import com.example.mrshopercapstone.databinding.FragmentItemDetilsBinding
import com.squareup.picasso.Picasso

private const val TAG = "ItemDetilsFragment"
class ItemDetilsFragment : Fragment() {
    private lateinit var binding: FragmentItemDetilsBinding
    private val itemViewModel: ItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemDetilsBinding.inflate(layoutInflater ,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
    binding.registerButton.setOnClickListener{
     findNavController().navigate(R.id.action_itemDetilsFragment3_to_cartFragment3)
    }
    }
    fun observers(){
        itemViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner,{

            binding.CatogoryTextView.text = it.category
            binding.TitleTextView.text = it.title
            binding.descripitionTextView.text = it.description
            binding.countTextView.text = it.rating.count.toString()
//          binding.ratingBar.rating = it.rating.rate.toFloat()
            binding.ratingBar.rating = it.rating.rate.toFloat()
            Log.d(TAG, it.rating.rate.toString())
            Picasso.get().load(it.image).into(binding.itemImageView)
        })
    }
    }

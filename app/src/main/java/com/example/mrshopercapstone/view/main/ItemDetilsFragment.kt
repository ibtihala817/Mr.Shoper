package com.example.mrshopercapstone.view.main

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.mrshopercapstone.R
import com.example.mrshopercapstone.databinding.FragmentItemDetilsBinding
import com.example.mrshopercapstone.models.items.CartModel
import com.example.mrshopercapstone.models.items.ItemModel
import com.example.mrshopercapstone.models.items.Rating
import com.squareup.picasso.Picasso

private const val TAG = "ItemDetilsFragment"
class ItemDetilsFragment : Fragment() {
    private lateinit var binding: FragmentItemDetilsBinding
    private val itemViewModel: ItemViewModel by activityViewModels()
    // private lateinit var  itemModelCart: ItemModel
    //private lateinit var ratting:
    private val cartViewModel: CartViewModel by activityViewModels()
    lateinit var itemModel: MutableLiveData<ItemModel>

    lateinit var cartItem: ItemModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemDetilsBinding.inflate(layoutInflater ,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "ID: ${itemViewModel.id}")
         val cart = ItemModel(
             "true",
             "true",
             itemViewModel.id,
             itemViewModel.image,
             itemViewModel.price.toDouble(),
             itemViewModel.title

         )

        //val ItemCart : ItemModel()
         observers()
//        addObserver()
        //val model =  ItemModel(itemViewModel.selectedItemMutableLiveData.toString(),)
    binding.registerButton.setOnClickListener(){

        observers()
        cartViewModel.addMyCart(cartItem)
//        CartViewModel.

//        itemViewModel.addMyCart(ItemModel*//*("String",
//            "String",0,"String",0.0,rate,"String")*//*
//        )*/
        //itemViewModel.addMyCart()
     findNavController().navigate(R.id.action_itemDetilsFragment3_to_cartFragment3)
    }
    }
    fun observers(){
         itemViewModel.selectedItemMutableLiveData
            .observe(viewLifecycleOwner,{
                cartItem = it
//            itemModelCart = it
            binding.CatogoryTextView.text = it.category
            binding.TitleTextView.text = it.title
            binding.descripitionTextView.text = it.description
//            binding.countTextView.text = it.rating.count.toString()
//          binding.ratingBar.rating = it.rating.rate.toFloat()
//            binding.ratingBar.rating = ratting.rate.toFloat()
//            Log.d(TAG, it.rating.rate.toString())
            Picasso.get().load(it.image).into(binding.itemImageView)
        })

    }

//    fun addObserver(){
//        itemViewModel.addLiveData.observe(viewLifecycleOwner,{
//            itemModel = itemViewModel.selectedItemMutableLiveData
//            //Toast.makeText(requireActivity(), "your order has been added", Toast.LENGTH_SHORT).show()
//        })
//
//    }
    }

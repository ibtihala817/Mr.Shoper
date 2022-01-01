package com.example.mrshopercapstone.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.mrshopercapstone.R
import com.example.mrshopercapstone.databinding.FragmentCartBinding
import com.example.mrshopercapstone.models.items.CartModel
import com.example.mrshopercapstone.view.adaptersimport.CartAdapter

class CartFragment : Fragment() {


    private lateinit var binding: FragmentCartBinding
//    private var allcart = listOf<CartModel>()
    private lateinit var cartAdapter : CartAdapter
    private val cartViewModel : CartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartAdapter = CartAdapter(cartViewModel)
        binding.cartRecyclerView.adapter = cartAdapter
        cartViewModel.callMyCart()
        observers()
    }

    fun observers(){
    cartViewModel.myCartLiveData.observe(viewLifecycleOwner,{
        cartAdapter.sumbitList(it)
//        allcart = it
        binding.cartRecyclerView.animate().alpha(1f)
    })

    }
}


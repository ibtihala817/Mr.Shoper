package com.example.mrshopercapstone.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mrshopercapstone.Util.SwipeToDelete
import com.example.mrshopercapstone.databinding.FragmentCartBinding
import com.example.mrshopercapstone.view.adaptersimport.CartAdapter
class CartFragment : Fragment() {


    private lateinit var binding: FragmentCartBinding

    private lateinit var cartAdapter : CartAdapter
    private val cartViewModel : CartViewModel by activityViewModels()

    private var userId : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
       /// for no item found////
        binding.NoItemFoundtextView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartAdapter = CartAdapter(cartViewModel)
        binding.cartRecyclerView.adapter = cartAdapter
        cartViewModel.callMyCart()



        // for swipe delete
        val swipeDelete = object : SwipeToDelete(this.requireContext()){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                cartAdapter.deleteItem(viewHolder.adapterPosition)

            }
        }
        val touchHelper = ItemTouchHelper(swipeDelete)
        touchHelper.attachToRecyclerView(binding.cartRecyclerView)
        observers()
    }

    fun observers(){
        cartViewModel.myCartLiveData.observe(viewLifecycleOwner,{
            cartAdapter.sumbitList(it)

            binding.cartRecyclerView.animate().alpha(1f)

            if (it.isNotEmpty()){
                binding.NoItemFoundtextView.isVisible = false

            }else{
                (it.isEmpty())
                binding.NoItemFoundtextView.isVisible = true
            }
        })

    }
}


package com.example.mrshopercapstone.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mrshopercapstone.R
import com.example.mrshopercapstone.databinding.FragmentItemBinding

import com.example.mrshopercapstone.models.items.ItemModel
import com.example.mrshopercapstone.view.adapters.ItemAdapter

class ItemFragment : Fragment() {
    private lateinit var binding: FragmentItemBinding
    private var allItems = listOf<ItemModel>()
    private lateinit var itemAdapter: ItemAdapter
    private val itemsViewModel: ItemViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemAdapter = ItemAdapter(itemsViewModel)
       binding.itemRecyclerView.adapter = itemAdapter
        observer()

        itemsViewModel.callItems()
    }
    fun observer(){
        itemsViewModel.itemLiveData.observe(viewLifecycleOwner,{
            binding.itemProgressBar.animate().alpha(0f).setDuration(1000)
            itemAdapter.submitList(it)
            allItems = it
          binding.itemRecyclerView.animate().alpha(1f)

        })
    }
}
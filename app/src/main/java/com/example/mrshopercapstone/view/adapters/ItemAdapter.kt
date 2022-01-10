package com.example.mrshopercapstone.view.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.mrshopercapstone.models.items.ItemModel


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.mrshopercapstone.R
import com.example.mrshopercapstone.view.main.ItemViewModel
import com.squareup.picasso.Picasso

class ItemAdapter(val viewModel: ItemViewModel) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemModel>(){
        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return  oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this,DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
       val item = differ.currentList[position]
       holder.itemNameTextView.text = item.title
       holder.priceTextview.text = "${item.price} SAR"
        Picasso.get().load(item.image).into(holder.itemImageView)

        holder.itemView.setOnClickListener {
            viewModel.selectedItemMutableLiveData.postValue(item)
        holder.itemView.findNavController().navigate(R.id.action_itemFragment4_to_itemDetilsFragment3)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun submitList(list: List<ItemModel>){
        differ.submitList(list)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTextView: TextView = itemView.findViewById(R.id.cartItemTextView)
        val priceTextview: TextView = itemView.findViewById(R.id.qunitiy_TextView)
        val itemImageView: ImageView = itemView.findViewById(R.id.cartImage)
    }
}
package com.example.mrshopercapstone.view.main

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mrshopercapstone.R
import com.example.mrshopercapstone.databinding.FragmentCartDatilsBinding
import com.example.mrshopercapstone.databinding.FragmentItemDetilsBinding
import com.example.mrshopercapstone.models.items.CartModel
import com.example.mrshopercapstone.models.items.ItemModel
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream

private const val TAG = "CartDatilsFragment"
class CartDatilsFragment : Fragment() {
    private lateinit var binding: FragmentCartDatilsBinding
    private val itemViewModel: ItemViewModel by activityViewModels()
    private val cartViewModel: CartViewModel by activityViewModels()
    lateinit var cartItem: ItemModel
    lateinit var cartDatilsItem : CartModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartDatilsBinding.inflate(layoutInflater ,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observers()
        cartObserver()
        Log.d(TAG, "ID: ${itemViewModel.id}")

        // for the sharing the image
        binding.shareImageButton.setOnClickListener {
            val image:Bitmap?= getBitmapFromView(binding.itemImageView)
            val url = Uri.parse(image.toString())
            val share= Intent(Intent.ACTION_SEND)
            share.type="image/*"
            share.putExtra(Intent.EXTRA_STREAM,getImageUri(requireActivity(),image!!))
            startActivity(Intent.createChooser(share, "Share Via:"))

        }

    }
    private fun getBitmapFromView(view: ImageView):Bitmap?{
        val bitmap= Bitmap.createBitmap(view.width,view.height,Bitmap.Config.ARGB_8888)
        val paint= Canvas(bitmap)
        view.draw(paint)
        return bitmap

    }
    private fun getImageUri(inContext: Context, inImage:Bitmap): Uri?{
        val byte= ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG,100,byte)
        val path= MediaStore.Images.Media.insertImage(inContext.contentResolver,inImage,"Title",null)
        return Uri.parse(path)

    }
    ///////////////////////////////////////////////////////


    fun observers(){
        itemViewModel.selectedItemMutableLiveData
            .observe(viewLifecycleOwner,{
                it?.let {
                    cartItem = it
                    binding.CatogoryTextView.text = it.category
                    binding.TitleTextView.text = it.title
                    binding.descripitionTextView.text = it.description
                    Picasso.get().load(it.image).into(binding.itemImageView)
                    itemViewModel.selectedItemMutableLiveData.postValue(null)

                }
            })

    }
    //////////////////////////////////////////////////////
    fun cartObserver(){
        cartViewModel.selcetedItemMutableLiveData.observe(
            viewLifecycleOwner,{
                it?.let {
                    cartDatilsItem = it
                    binding.CatogoryTextView.text = it.category
                    binding.TitleTextView.text = it.title
                    binding.descripitionTextView.text = it.description

                    Picasso.get().load(it.image).into(binding.itemImageView)
                    cartViewModel.selcetedItemMutableLiveData.postValue(null)

                }
            }
        )
    }
}
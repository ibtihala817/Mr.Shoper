package com.example.mrshopercapstone.models.dashboard

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.mrshopercapstone.FireStore.FireStore
import com.example.mrshopercapstone.R

class DashboardFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // this is used to display the menu in the fragment
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard,container,false)
        return root
    }
    // this function for dashboard menu to display in the dashboard
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dashboard_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        getDashboardItemsList()
    }

    // this is a function to get the dashboard items list from firestore

    private fun getDashboardItemsList(){
        FireStore().getDashboardItemsList(this@DashboardFragment)
    }
}


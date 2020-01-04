package com.andreyplis.recipecounter.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andreyplis.recipecounter.R
import com.andreyplis.recipecounter.viewmodel.ProductsViewModel

/**
 * A simple [Fragment] subclass.
 */
class ProductsFragment : Fragment() {

    lateinit var viewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_products, container, false)
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        val adapter = ProductsAdapter()
        recyclerView.adapter = adapter
        viewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        viewModel.getProducts().observe(this, Observer {
            adapter.products = it
        })
        return view
    }


}

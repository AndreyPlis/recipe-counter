package com.andreyplis.recipecounter.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andreyplis.recipecounter.R
import com.andreyplis.recipecounter.viewmodel.ProductsViewModel

/**
 * A simple [Fragment] subclass.
 */
class GoodsFragment : Fragment() {

    lateinit var viewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        viewModel.getProducts().observe(this, Observer {
            Toast.makeText(this@GoodsFragment.context, it.size.toString(), Toast.LENGTH_SHORT)
                .show()
        })
        return inflater.inflate(R.layout.fragment_goods, container, false)
    }


}

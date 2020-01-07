package com.andreyplis.recipecounter.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andreyplis.recipecounter.R
import com.andreyplis.recipecounter.db.entity.ProductEntity
import com.andreyplis.recipecounter.viewmodel.ProductsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass.
 */
class ProductsFragment : Fragment() {

    lateinit var viewModel: ProductsViewModel
    lateinit var navController: NavController

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
        viewModel.getProductsWithMeasure().observe(this, Observer {
            adapter.products = it
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.delete(adapter.getProduct(viewHolder.adapterPosition))
                Toast.makeText(this@ProductsFragment.context, "Product deleted", Toast.LENGTH_SHORT)
                    .show()
            }

        }).attachToRecyclerView(recyclerView)
        adapter.listener = object : ProductsAdapter.ClickListener {
            override fun onItemClick(productEntity: ProductEntity) {
                val action = ProductsFragmentDirections.actionProductsFragmentToSaveOrUpdateProductFragment(productEntity)
                navController.navigate(action)
            }
        }




        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<FloatingActionButton>(R.id.floatingButtonAddProduct).setOnClickListener {
            navController.navigate(
                R.id.action_productsFragment_to_saveOrUpdateProductFragment
            )
        }
    }


}

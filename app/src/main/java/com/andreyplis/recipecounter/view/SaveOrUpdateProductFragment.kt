package com.andreyplis.recipecounter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.andreyplis.recipecounter.R
import com.andreyplis.recipecounter.db.entity.ProductEntity
import com.andreyplis.recipecounter.viewmodel.ProductsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SaveOrUpdateProductFragment : Fragment() {


    private lateinit var viewModel: ProductsViewModel

    val args: SaveOrUpdateProductFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.save_or_update_product_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        val textDescription = view.findViewById<EditText>(R.id.editTextDescription)
        val textCount = view.findViewById<EditText>(R.id.editTextCount)

        val product = args.product
        if (product != null) {
            textDescription.text.append(product.name)
            textCount.text.append(product.count.toString())
        }


        view.findViewById<FloatingActionButton>(R.id.floatingActionButtonApplyNewProduct)
            .setOnClickListener {

                if (product == null) {
                    viewModel.insert(
                        ProductEntity(
                            30,
                            textDescription.text.toString(),
                            1,
                            Integer.valueOf(textCount.text.toString()),
                            1.0f
                        )
                    )
                } else {
                    val newProduct = product.copy(
                        name = textDescription.text.toString(),
                        count = Integer.valueOf(textCount.text.toString())
                    )

                    viewModel.update(newProduct)
                }
                navController.navigate(
                    R.id.action_saveOrUpdateProductFragment_to_productsFragment
                )
            }
    }

}

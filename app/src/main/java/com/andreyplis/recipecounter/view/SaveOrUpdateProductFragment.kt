package com.andreyplis.recipecounter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.andreyplis.recipecounter.R
import com.andreyplis.recipecounter.db.entity.MeasureEntity
import com.andreyplis.recipecounter.db.entity.ProductEntity
import com.andreyplis.recipecounter.viewmodel.ProductsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SaveOrUpdateProductFragment : Fragment() {


    private lateinit var viewModel: ProductsViewModel

    private val args: SaveOrUpdateProductFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.save_or_update_product_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        val textDescription = view.findViewById<EditText>(R.id.editTextDescription)
        val textCount = view.findViewById<EditText>(R.id.editTextCount)
        val textPrice = view.findViewById<EditText>(R.id.editTextPrice)
        viewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        val spinnerMeasure = view.findViewById<Spinner>(R.id.spinnerMeasure)

        val product = args.product
        if (product != null) {
            textDescription.text.append(product.name)
            textCount.text.append(product.count.toString())
            textPrice.text.append(product.price.toString())
        }

        viewModel.getMeasures().observe(this, Observer {
            val adapter =
                ArrayAdapter<MeasureEntity>(this.context!!, android.R.layout.simple_spinner_item)
            adapter.addAll(it)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerMeasure.adapter = adapter
            if (product != null)
                spinnerMeasure.setSelection(it.indexOfFirst { m -> m.id == product.measureId })
        })

        view.findViewById<FloatingActionButton>(R.id.floatingActionButtonApplyNewProduct)
            .setOnClickListener {
                val id = (spinnerMeasure.selectedItem as MeasureEntity).id
                if (product == null) {
                    viewModel.insert(
                        ProductEntity(
                            0,
                            textDescription.text.toString(),
                            id,
                            textCount.text.toString().toInt(),
                            textPrice.text.toString().toFloat()
                        )
                    )
                } else {
                    val newProduct = product.copy(
                        name = textDescription.text.toString(),
                        count = textCount.text.toString().toInt(),
                        measureId = id,
                        price = textPrice.text.toString().toFloat()
                    )

                    viewModel.update(newProduct)
                }
                navController.navigate(
                    R.id.action_saveOrUpdateProductFragment_to_productsFragment
                )
            }
    }

}

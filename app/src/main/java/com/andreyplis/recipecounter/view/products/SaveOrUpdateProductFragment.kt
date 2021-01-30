package com.andreyplis.recipecounter.view.products

import android.os.*
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.navigation.*
import androidx.navigation.fragment.*
import com.andreyplis.recipecounter.R
import com.andreyplis.recipecounter.db.entity.*
import com.andreyplis.recipecounter.viewmodel.*
import com.google.android.material.floatingactionbutton.*


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

        viewModel.getMeasures().observe(viewLifecycleOwner, Observer {
            val adapter =
                ArrayAdapter<MeasureEntity>(
                    this.requireContext(),
                    android.R.layout.simple_spinner_item
                )
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

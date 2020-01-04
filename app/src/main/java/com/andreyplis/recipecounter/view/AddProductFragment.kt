package com.andreyplis.recipecounter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.andreyplis.recipecounter.R
import com.andreyplis.recipecounter.db.entity.ProductEntity
import com.andreyplis.recipecounter.viewmodel.AddProductViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AddProductFragment : Fragment() {

    companion object {
        fun newInstance() =
            AddProductFragment()
    }

    private lateinit var viewModel: AddProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_product_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddProductViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        val textDescrpition = view.findViewById<EditText>(R.id.editTextDescription)
        val textCount = view.findViewById<EditText>(R.id.editTextCount)
        view.findViewById<FloatingActionButton>(R.id.floatingActionButtonApplyNewProduct)
            .setOnClickListener {


                viewModel.insert(
                    ProductEntity(
                        30,
                        textDescrpition.text.toString(),
                        "kg",
                        Integer.valueOf(textCount.text.toString()),
                        1.0f
                    )
                )
                navController.navigate(
                    R.id.action_addProductFragment_to_productsFragment
                )
            }
    }

}

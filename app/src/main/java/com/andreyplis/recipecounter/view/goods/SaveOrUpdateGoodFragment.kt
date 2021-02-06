package com.andreyplis.recipecounter.view.goods

import android.os.*
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.navigation.*
import androidx.navigation.fragment.*
import com.andreyplis.recipecounter.R
import com.andreyplis.recipecounter.db.entity.*
import com.andreyplis.recipecounter.model.*
import com.andreyplis.recipecounter.viewmodel.*
import com.google.android.material.floatingactionbutton.*


class SaveOrUpdateGoodFragment : Fragment() {


    private lateinit var viewModel: GoodsViewModel

    private val args: SaveOrUpdateGoodFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.save_or_update_good_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        val textDescription = view.findViewById<EditText>(R.id.editTextDescription)
        val textCount = view.findViewById<EditText>(R.id.editTextCount)
        val textPrice = view.findViewById<EditText>(R.id.editTextPrice)
        viewModel = ViewModelProviders.of(this).get(GoodsViewModel::class.java)
        val spinnerMeasure = view.findViewById<Spinner>(R.id.spinnerMeasure)

        val good = args.good
        if (good != null) {
            textDescription.text.append(good.name)
            textCount.text.append(good.count.toString())
            textPrice.text.append(good.price.toString())
        }


        val adapter =
            ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item)
        adapter.addAll(Good.MEASURES.values)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMeasure.adapter = adapter
        if (good != null)
            spinnerMeasure.setSelection(Good.MEASURES.values.indexOfFirst { Good.MEASURES[good.measure] == it })

        view.findViewById<FloatingActionButton>(R.id.floatingActionButtonApplyNewProduct)
            .setOnClickListener {
                val id =
                    Good.MEASURES.entries.find { it.value == (spinnerMeasure.selectedItem as String) }!!.key
                if (good == null) {
                    viewModel.insert(
                        GoodEntity(
                            0,
                            textDescription.text.toString(),
                            id,
                            textCount.text.toString().toInt(),
                            textPrice.text.toString().toFloat()
                        )
                    )
                } else {
                    val newProduct = good.copy(
                        name = textDescription.text.toString(),
                        count = textCount.text.toString().toInt(),
                        measure = id,
                        price = textPrice.text.toString().toFloat()
                    )

                    viewModel.update(newProduct)
                }
                navController.navigate(
                    R.id.action_saveOrUpdateGoodFragment_to_goodsFragment
                )
            }
    }

}

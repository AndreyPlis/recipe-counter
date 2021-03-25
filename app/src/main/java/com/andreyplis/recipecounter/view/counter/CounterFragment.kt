package com.andreyplis.recipecounter.view.counter


import android.os.*
import android.util.*
import android.view.*
import android.widget.*
import android.widget.AdapterView.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.navigation.fragment.*
import com.andreyplis.recipecounter.R
import com.andreyplis.recipecounter.model.*
import com.andreyplis.recipecounter.viewmodel.*

/**
 * A simple [Fragment] subclass.
 */
class CounterFragment : Fragment() {

    private val args: CounterFragmentArgs by navArgs()
    private lateinit var goods: List<RecipeGood>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_counter, container, false)
        val recipe = args.recipe

        val priceView = view.findViewById<TextView>(R.id.textView9)
        val recipeDescription = view.findViewById<TextView>(R.id.textView2)
        recipeDescription.text = recipe.description

        val recipeMeasure = view.findViewById<TextView>(R.id.textView5)

        var listDiam: List<Int>
        if (recipe.type == Recipe.TYPE.CAKE) {
            listDiam = mutableListOf(16, 18, 20, 22, 24, 26, 28, 30)
            recipeMeasure.text = "Diameter"
        } else {
            listDiam = generateSequence(3, { i -> (i + 1).takeIf { it <= 50 } }).toList()
            recipeMeasure.text = "Count"
        }

        val diam = view.findViewById<Spinner>(R.id.spinnerDiam)
        val ad = ArrayAdapter(this.requireContext(), android.R.layout.simple_list_item_1, listDiam)

        diam.adapter = ad
        if (recipe.type == Recipe.TYPE.CAKE) {
            diam.setSelection(ad.getPosition(recipe.diameter))
        } else {
            diam.setSelection(ad.getPosition(recipe.count))
        }


        val viewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)
        viewModel.getGoods(recipe.id).observe(viewLifecycleOwner, Observer {
            goods = it
        })

        diam.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                priceView.text = calculatePrice(parent.getItemAtPosition(position) as Int, recipe).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        return view
    }

    fun calculatePrice(value: Int, recipe: Recipe): Double {
        val k: Float
        if (recipe.type == Recipe.TYPE.CAKE) {
            k = (value * value) / ((recipe.diameter * recipe.diameter).toFloat())
        } else {
            k = value / recipe.count.toFloat()
        }
        Log.i(null, "k = $k")

        val newGoods = goods.map {
            val newRecipeCount = (it.recipeCount * k).toInt()
            val newPrice = (it.price / it.count) * newRecipeCount
            it.copy(recipeCount = newRecipeCount, price = newPrice)
        }

        return newGoods.sumOf { it.price.toDouble() }
    }
}

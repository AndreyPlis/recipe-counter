package com.andreyplis.recipecounter.view.recipes

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

class SaveOrUpdateRecipeCakeFragment : Fragment() {

    private lateinit var viewModel: RecipesViewModel

    private val args: SaveOrUpdateRecipeCakeFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_cake, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        val textDescription = view.findViewById<EditText>(R.id.editTextDescription)
        val textCount = view.findViewById<EditText>(R.id.editTextCount)
        val textPrice = view.findViewById<EditText>(R.id.editTextPrice)
        val textDiameter = view.findViewById<EditText>(R.id.editTextDiameter)
        viewModel = ViewModelProviders.of(this).get(RecipesViewModel::class.java)

        val recipe = args.recipe
        if (recipe != null) {
            textDescription.text.append(recipe.description)
            textCount.text.append(recipe.count.toString())
            textPrice.text.append(recipe.price.toString())
            textDiameter.text.append(recipe.diameter.toString())
        }

        view.findViewById<FloatingActionButton>(R.id.floatingActionButtonApplyNewCake)
            .setOnClickListener {
                if (recipe == null) {
                    viewModel.insert(
                        RecipeEntity(0, textDescription.text.toString(), Recipe.TYPE.DESERT, textCount.text.toString().toInt(), textDiameter.text.toString().toInt(), textPrice.text.toString().toFloat())
                    )
                } else {
                    val newProduct = recipe.copy(
                        description = textDescription.text.toString(),
                        count = textCount.text.toString().toInt(),
                        diameter = textDiameter.text.toString().toInt(),
                        price = textPrice.text.toString().toFloat()
                    )

                    viewModel.update(newProduct)
                }
                navController.navigate(
                    R.id.action_saveOrUpdateRecipeCakeFragment_to_recipesFragment
                )
            }
    }

}

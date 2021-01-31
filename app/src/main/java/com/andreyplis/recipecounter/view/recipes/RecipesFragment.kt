package com.andreyplis.recipecounter.view.recipes


import android.os.*
import android.view.*
import android.view.animation.*
import android.widget.*
import androidx.fragment.app.*
import androidx.navigation.*
import com.andreyplis.recipecounter.R
import com.google.android.material.floatingactionbutton.*

/**
 * A simple [Fragment] subclass.
 */
class RecipesFragment : Fragment() {

    lateinit var navController: NavController

    var isOpen = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val fabAdd = view.findViewById<FloatingActionButton>(R.id.floatingButtonAddRecipe)
        val fabCake = view.findViewById<FloatingActionButton>(R.id.floatingButtonAddCake)
        val fabDesert = view.findViewById<FloatingActionButton>(R.id.floatingButtonAddDesert)

        val animClose = AnimationUtils.loadAnimation(view.context, R.anim.fab_close)
        val animOpen = AnimationUtils.loadAnimation(view.context, R.anim.fab_open)
        val animClock = AnimationUtils.loadAnimation(view.context, R.anim.fab_rotate_clock)
        val animAnticlock = AnimationUtils.loadAnimation(view.context, R.anim.fab_rotate_anticlock)

        val viewCake = view.findViewById<TextView>(R.id.textViewAddCake)
        val viewDesert = view.findViewById<TextView>(R.id.textViewAddDesert)


        fabAdd.setOnClickListener {

            if (isOpen) {
                viewCake.startAnimation(animClose)
                viewDesert.startAnimation(animClose)
                fabCake.startAnimation(animClose)
                fabDesert.startAnimation(animClose)
                fabAdd.startAnimation(animAnticlock)
                fabCake.isClickable = false
                fabDesert.isClickable = false
                isOpen = false
            } else {
                viewCake.startAnimation(animOpen)
                viewDesert.startAnimation(animOpen)
                fabCake.startAnimation(animOpen)
                fabDesert.startAnimation(animOpen)
                fabAdd.startAnimation(animClock)
                fabCake.isClickable = true
                fabDesert.isClickable = true
                isOpen = true
            }

        }
        fabCake.setOnClickListener {
            val action =
                RecipesFragmentDirections.actionRecipesFragmentToSaveOrUpdateRecipeDesertFragment()
            navController.navigate(action)
        }

        fabDesert.setOnClickListener {
            val action =
                RecipesFragmentDirections.actionRecipesFragmentToSaveOrUpdateRecipeCakeFragment()
            navController.navigate(action)
        }

    }

}

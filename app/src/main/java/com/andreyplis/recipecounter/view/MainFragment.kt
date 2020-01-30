package com.andreyplis.recipecounter.view


import android.os.*
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import androidx.navigation.*
import com.andreyplis.recipecounter.R

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.buttonGoods).setOnClickListener {
            navController.navigate(
            R.id.action_mainFragment_to_productsFragment
        ) }

        view.findViewById<Button>(R.id.buttonRecipes).setOnClickListener {
            navController.navigate(
                R.id.action_mainFragment_to_recipesFragment
            ) }

        view.findViewById<Button>(R.id.buttonCalculator).setOnClickListener {
            navController.navigate(
                R.id.action_mainFragment_to_counterFragment
            ) }
    }


}

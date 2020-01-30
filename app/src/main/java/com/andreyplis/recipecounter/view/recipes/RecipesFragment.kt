package com.andreyplis.recipecounter.view.recipes


import android.os.*
import android.view.*
import androidx.fragment.app.*
import com.andreyplis.recipecounter.*

/**
 * A simple [Fragment] subclass.
 */
class RecipesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }


}

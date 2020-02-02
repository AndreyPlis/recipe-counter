package com.andreyplis.recipecounter.view.recipes


import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.navigation.fragment.*
import com.andreyplis.recipecounter.R
import com.andreyplis.recipecounter.model.*

/**
 * A simple [Fragment] subclass.
 */
class SaveOrUpdateRecipeFragment : Fragment() {

    private val args: SaveOrUpdateRecipeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save_or_update_recipe, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val type = Recipe.TYPE.valueOf(args.type)
    }

}

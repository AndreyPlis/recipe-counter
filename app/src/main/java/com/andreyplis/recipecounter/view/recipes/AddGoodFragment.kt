package com.andreyplis.recipecounter.view.recipes

import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.navigation.*
import androidx.navigation.fragment.*
import com.andreyplis.recipecounter.R

class AddGoodFragment : Fragment() {

    lateinit var navController: NavController
    lateinit var adapter: RecipeGoodsAdapter

    private val args: AddGoodFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_good, container, false)



        return view
    }
}
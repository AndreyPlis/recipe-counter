package com.andreyplis.recipecounter.view.recipes


import android.os.*
import android.view.*
import androidx.fragment.app.*
import com.andreyplis.recipecounter.*
import com.andreyplis.recipecounter.view.goods.*

/**
 * A simple [Fragment] subclass.
 */
class RecipeGoodsFragment : GoodsFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goods, container, false)
    }


}

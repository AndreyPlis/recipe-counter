package com.andreyplis.recipecounter.view.counter


import android.os.*
import android.view.*
import androidx.fragment.app.*
import com.andreyplis.recipecounter.*

/**
 * A simple [Fragment] subclass.
 */
class CounterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_counter, container, false)
    }


}

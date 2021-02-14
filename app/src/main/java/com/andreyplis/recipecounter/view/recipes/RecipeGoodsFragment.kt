package com.andreyplis.recipecounter.view.recipes


import android.os.*
import android.util.*
import android.util.Log.*
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.navigation.*
import androidx.navigation.fragment.*
import androidx.recyclerview.widget.*
import com.andreyplis.recipecounter.*
import com.andreyplis.recipecounter.R
import com.andreyplis.recipecounter.db.entity.*
import com.andreyplis.recipecounter.model.*
import com.andreyplis.recipecounter.view.goods.*
import com.andreyplis.recipecounter.viewmodel.*
import com.google.android.material.floatingactionbutton.*

/**
 * A simple [Fragment] subclass.
 */
class RecipeGoodsFragment : Fragment() {

    lateinit var navController: NavController
    lateinit var adapter: RecipeGoodsAdapter

    private val args: RecipeGoodsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipe_goods, container, false)
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = RecipeGoodsAdapter()
        recyclerView.adapter = adapter

        val recipe = args.recipe


        val viewModel = ViewModelProviders.of(this).get(RecipeGoodsViewModel::class.java)
        viewModel.getRecipeWithGoods(recipe.id).observe(viewLifecycleOwner, Observer {
            adapter.products = it
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.getProduct(viewHolder.adapterPosition)
                val entity = RecipeGoodEntity(recipe.id, item.id, item.count)
                viewModel.delete(entity)
                Toast.makeText(this@RecipeGoodsFragment.context, "Good deleted", Toast.LENGTH_SHORT)
                    .show()
            }

        }).attachToRecyclerView(recyclerView)

        setHasOptionsMenu(true)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<FloatingActionButton>(R.id.floatingButtonAddProduct).setOnClickListener {
            /*navController.navigate(
                R.id.action_goodsFragment_to_saveOrUpdateGoodFragment
            )*/
        }
    }


}

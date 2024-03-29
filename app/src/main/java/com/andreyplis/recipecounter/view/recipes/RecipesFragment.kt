package com.andreyplis.recipecounter.view.recipes


import android.os.*
import android.util.*
import android.view.*
import android.view.animation.*
import android.widget.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.navigation.*
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
class RecipesFragment : Fragment() {

    lateinit var navController: NavController
    lateinit var adapter: RecipesAdapter
    var isOpen = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerViewRecipes)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = RecipesAdapter()
        recyclerView.adapter = adapter
        val viewModel = ViewModelProviders.of(this).get(RecipesViewModel::class.java)
        viewModel.getRecipes().observe(viewLifecycleOwner, Observer {
            adapter.recipes = it
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.LEFT) {
                    viewModel.delete(adapter.getRecipe(viewHolder.adapterPosition))
                    Toast.makeText(this@RecipesFragment.context, "Recipe deleted", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val recipe = adapter.getRecipe(viewHolder.adapterPosition)
                    val action = RecipesFragmentDirections.actionRecipesFragmentToCounterFragment(recipe)
                    navController.navigate(action)
                }
            }

        }).attachToRecyclerView(recyclerView)
        adapter.listener = object : RecipesAdapter.ClickListener {
            override fun onItemClick(recipeEntity: RecipeEntity) {
                val action = RecipesFragmentDirections.actionRecipesFragmentToRecipeGoodsFragment(recipeEntity)
                navController.navigate(action)

            }

            override fun onLongItemClick(recipeEntity: RecipeEntity): Boolean {
                val action: NavDirections
                if (recipeEntity.type == Recipe.TYPE.DESERT)
                    action = RecipesFragmentDirections.actionRecipesFragmentToSaveOrUpdateRecipeDesertFragment(recipeEntity)
                else
                    action = RecipesFragmentDirections.actionRecipesFragmentToSaveOrUpdateRecipeCakeFragment(recipeEntity)
                navController.navigate(action)
                return true
            }
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        (searchItem.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
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

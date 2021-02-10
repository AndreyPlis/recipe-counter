package com.andreyplis.recipecounter.view.goods


import android.os.*
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.navigation.*
import androidx.recyclerview.widget.*
import com.andreyplis.recipecounter.R
import com.andreyplis.recipecounter.db.entity.*
import com.andreyplis.recipecounter.viewmodel.*
import com.google.android.material.floatingactionbutton.*

/**
 * A simple [Fragment] subclass.
 */
open class GoodsFragment : Fragment() {

    lateinit var navController: NavController
    lateinit var adapter: GoodsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_goods, container, false)
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = GoodsAdapter()
        recyclerView.adapter = adapter
        val viewModel = ViewModelProviders.of(this).get(GoodsViewModel::class.java)
        viewModel.getProducts().observe(viewLifecycleOwner, Observer {
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
                viewModel.delete(adapter.getProduct(viewHolder.adapterPosition))
                Toast.makeText(this@GoodsFragment.context, "Good deleted", Toast.LENGTH_SHORT)
                    .show()
            }

        }).attachToRecyclerView(recyclerView)
        adapter.listener = object : GoodsAdapter.ClickListener {
            override fun onItemClick(goodEntity: GoodEntity) {
                val action =
                    GoodsFragmentDirections.actionGoodsFragmentToSaveOrUpdateGoodFragment(
                        goodEntity
                    )
                navController.navigate(action)
            }
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val searchItem = menu.findItem(R.id.action_search)
        (searchItem.actionView as SearchView).setOnQueryTextListener(object:SearchView.OnQueryTextListener{
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
        view.findViewById<FloatingActionButton>(R.id.floatingButtonAddProduct).setOnClickListener {
            navController.navigate(
                R.id.action_goodsFragment_to_saveOrUpdateGoodFragment
            )
        }
    }


}

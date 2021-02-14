package com.andreyplis.recipecounter.view.recipes

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.*
import com.andreyplis.recipecounter.*
import com.andreyplis.recipecounter.model.*

class RecipeGoodsAdapter : RecyclerView.Adapter<RecipeGoodsAdapter.ProductHolder>(), Filterable {

    var products = listOf<RecipeGood>()
        set(value) {
            field = value
            resultProducts = value
        }

    private var resultProducts = listOf<RecipeGood>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        val textViewCount: TextView = itemView.findViewById(R.id.textViewType)
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_good_item, parent, false)
        return ProductHolder(itemView)

    }

    override fun getItemCount(): Int = resultProducts.size

    fun getProduct(position: Int): RecipeGood = resultProducts[position]


    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = getProduct(position)
        val measure = Good.MEASURES[resultProducts[position].measure]
        holder.textViewDescription.text = product.name
        holder.textViewCount.text = "${product.recipeCount} x $measure"

        val price = product.price / product.count * product.recipeCount

        holder.textViewPrice.text = "$price P"

    }

    override fun getFilter(): Filter {
        return productsFilter
    }

    private val productsFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val filtered: List<RecipeGood>
            if (constraint == null || constraint.isEmpty()) {
                filtered = products.toList()
            } else {
                val search = constraint.toString().toLowerCase().trim()
                filtered = products.filter { it.name.toLowerCase().contains(search) }
            }

            val results = FilterResults()
            results.values = filtered

            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            resultProducts = results?.values as List<RecipeGood>
        }

    }
}
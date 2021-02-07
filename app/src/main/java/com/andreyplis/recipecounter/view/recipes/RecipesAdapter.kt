package com.andreyplis.recipecounter.view.recipes

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.*
import com.andreyplis.recipecounter.*
import com.andreyplis.recipecounter.db.entity.*
import com.andreyplis.recipecounter.model.*

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.RecipeHolder>(), Filterable {

    lateinit var listener: ClickListener

    var recipes = listOf<RecipeEntity>()
        set(value) {
            field = value
            resultRecipes = value
        }

    private var resultRecipes = listOf<RecipeEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface ClickListener {
        fun onItemClick(recipeEntity: RecipeEntity)
    }

    inner class RecipeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        val textViewType: TextView = itemView.findViewById(R.id.textViewType)
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)
        val textViewInfo: TextView = itemView.findViewById(R.id.textViewInfo)

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION)
                    listener.onItemClick(getRecipe(adapterPosition))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        val recipe = getRecipe(position)
        holder.textViewDescription.text = recipe.description
        holder.textViewType.text = recipe.type.name
        holder.textViewPrice.text = "${recipe.price} P"
        if (recipe.type == Recipe.TYPE.DESERT)
            holder.textViewInfo.text = "${recipe.count} шт"
        else
            holder.textViewInfo.text = "${recipe.count} г диаметр ${recipe.diameter}"
    }

    override fun getItemCount(): Int = resultRecipes.size

    fun getRecipe(position: Int): RecipeEntity = resultRecipes[position]

    override fun getFilter(): Filter {
        return recipeFilter
    }

    private val recipeFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val filtered: List<RecipeEntity>
            if (constraint == null || constraint.isEmpty()) {
                filtered = recipes.toList()
            } else {
                val search = constraint.toString().toLowerCase().trim()
                filtered = recipes.filter { it.description.toLowerCase().contains(search) }
            }

            val results = FilterResults()
            results.values = filtered

            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            resultRecipes = results?.values as List<RecipeEntity>
        }

    }
}
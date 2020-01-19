package com.andreyplis.recipecounter.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andreyplis.recipecounter.R
import com.andreyplis.recipecounter.db.ProductWithMeasure
import com.andreyplis.recipecounter.db.entity.ProductEntity



class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductHolder>(), Filterable {

    lateinit var listener: ClickListener


    var products = listOf<ProductWithMeasure>()
        set(value) {
            field = value
            resultProducts = value
        }

    private var resultProducts = listOf<ProductWithMeasure>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        val textViewCount: TextView = itemView.findViewById(R.id.textViewCount)
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION)
                    listener.onItemClick(getProduct(adapterPosition))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductHolder(itemView)

    }

    override fun getItemCount(): Int = resultProducts.size

    fun getProduct(position: Int): ProductEntity = resultProducts[position].productEntity


    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = getProduct(position)
        val measure = resultProducts[position].measure
        holder.textViewDescription.text = product.name
        holder.textViewCount.text = "${product.count} x $measure"
        holder.textViewPrice.text = "${product.price} P"

    }


    interface ClickListener {
        fun onItemClick(productEntity: ProductEntity)
    }

    override fun getFilter(): Filter {
        return productsFilter
    }

    private val productsFilter: Filter = object :Filter()
    {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val filtered:List<ProductWithMeasure>
            if (constraint == null || constraint.isEmpty()) {
                filtered = products.toList()
            } else {
                val search = constraint.toString().toLowerCase().trim()
                filtered = products.filter { it.productEntity.name.toLowerCase().contains(search) }
            }

            val results = FilterResults()
            results.values = filtered

            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            resultProducts = results?.values as List<ProductWithMeasure>
        }

    }
}
package com.andreyplis.recipecounter.view.goods


import android.view.*
import android.widget.*
import androidx.recyclerview.widget.*
import com.andreyplis.recipecounter.*
import com.andreyplis.recipecounter.db.entity.*
import com.andreyplis.recipecounter.model.*


class GoodsAdapter : RecyclerView.Adapter<GoodsAdapter.ProductHolder>(), Filterable {

    lateinit var listener: ClickListener


    var products = listOf<GoodEntity>()
        set(value) {
            field = value
            resultProducts = value
        }

    private var resultProducts = listOf<GoodEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        val textViewCount: TextView = itemView.findViewById(R.id.textViewType)
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
            LayoutInflater.from(parent.context).inflate(R.layout.good_item, parent, false)
        return ProductHolder(itemView)

    }

    override fun getItemCount(): Int = resultProducts.size

    fun getProduct(position: Int): GoodEntity = resultProducts[position]


    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = getProduct(position)
        val measure = Good.MEASURES[resultProducts[position].measure]
        holder.textViewDescription.text = product.name
        holder.textViewCount.text = "${product.count} x $measure"
        holder.textViewPrice.text = "${product.price} P"

    }


    interface ClickListener {
        fun onItemClick(goodEntity: GoodEntity)
    }

    override fun getFilter(): Filter {
        return productsFilter
    }

    private val productsFilter: Filter = object :Filter()
    {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val filtered: List<GoodEntity>
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
            resultProducts = results?.values as List<GoodEntity>
        }

    }
}
package id.co.ptn.tesesqgroup.ui.drink.filter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.ptn.tesesqgroup.R
import id.co.ptn.tesesqgroup.databinding.ItemFilterDrinkBinding
import id.co.ptn.tesesqgroup.databinding.ItemPopularDrinkBinding
import id.co.ptn.tesesqgroup.models.Category

class ItemCategoryAdapter(
    private val categories: MutableList<Category>,
    private val listener: ItemDrinkListener): RecyclerView.Adapter<ItemCategoryAdapter.ViewHolder>() {
    private lateinit var context: Context
    class ViewHolder(private val binding: ItemFilterDrinkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.cb.text = category.strCategory
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding: ItemFilterDrinkBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_filter_drink, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val element = categories[position]
        holder.bind(element)
        holder.itemView.setOnClickListener { listener.onItemPressed(element.strCategory) }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    public interface ItemDrinkListener {
        fun onItemPressed(value: String)
    }
}
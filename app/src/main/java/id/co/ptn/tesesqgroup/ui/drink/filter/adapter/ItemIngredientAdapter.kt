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
import id.co.ptn.tesesqgroup.models.Ingredient

class ItemIngredientAdapter(
    private val ingredients: MutableList<Ingredient>,
    private val listener: ItemDrinkListener): RecyclerView.Adapter<ItemIngredientAdapter.ViewHolder>() {
    private lateinit var context: Context
    class ViewHolder(private val binding: ItemFilterDrinkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: Ingredient) {
            binding.cb.text = ingredient.strIngredient1
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
        val element = ingredients[position]
        holder.bind(element)
        holder.itemView.setOnClickListener { listener.onItemPressed(element.strIngredient1) }
    }

    override fun getItemCount(): Int {
        return if (ingredients.size > 15) 15 else ingredients.size
    }

    public interface ItemDrinkListener {
        fun onItemPressed(value: String)
    }
}
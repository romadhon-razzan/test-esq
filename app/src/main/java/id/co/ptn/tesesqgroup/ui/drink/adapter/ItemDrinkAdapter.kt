package id.co.ptn.tesesqgroup.ui.drink.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.ptn.tesesqgroup.R
import id.co.ptn.tesesqgroup.databinding.ItemDrinkBinding
import id.co.ptn.tesesqgroup.models.Drinks

class ItemDrinkAdapter(
    private val drinks: MutableList<Drinks>,
    private val listener: ItemDrinkListener): RecyclerView.Adapter<ItemDrinkAdapter.ViewHolder>() {
    private lateinit var context: Context
    class ViewHolder(private val binding: ItemDrinkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(drink: Drinks, context: Context) {
            binding.tvDrinkName.text = drink.strDrink
            Glide.with(context).load(drink.strDrinkThumb).into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding: ItemDrinkBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_drink, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val element = drinks[position]
        holder.bind(element, context)
        holder.itemView.setOnClickListener { listener.onItemPressed(drink = element) }
    }

    override fun getItemCount(): Int {
        return drinks.size
    }

    public interface ItemDrinkListener {
        fun onItemPressed(drink: Drinks)
    }
}
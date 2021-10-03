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
import id.co.ptn.tesesqgroup.models.Glasses

class ItemGlassAdapter(
    private val glasses: MutableList<Glasses>,
    private val listener: ItemDrinkListener): RecyclerView.Adapter<ItemGlassAdapter.ViewHolder>() {
    private lateinit var context: Context
    class ViewHolder(private val binding: ItemFilterDrinkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(glasses: Glasses) {
            binding.cb.text = glasses.strGlass
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
        val element = glasses[position]
        holder.bind(element)
        holder.itemView.setOnClickListener { listener.onItemPressed(element.strGlass) }
    }

    override fun getItemCount(): Int {
        return if (glasses.size > 15) 15 else glasses.size
    }

    public interface ItemDrinkListener {
        fun onItemPressed(value: String)
    }
}
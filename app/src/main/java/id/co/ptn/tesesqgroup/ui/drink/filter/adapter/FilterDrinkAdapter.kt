package id.co.ptn.tesesqgroup.ui.drink.filter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.ptn.tesesqgroup.R
import id.co.ptn.tesesqgroup.bases.BaseViewHolder
import id.co.ptn.tesesqgroup.databinding.ContainerHomeItemDrinkBinding
import id.co.ptn.tesesqgroup.models.*
import id.co.ptn.tesesqgroup.ui.drink.filter.holder.AlcoholicHolder
import id.co.ptn.tesesqgroup.ui.drink.filter.holder.CategoryHolder
import id.co.ptn.tesesqgroup.ui.drink.filter.holder.GlassHolder
import id.co.ptn.tesesqgroup.ui.drink.filter.holder.IngredientHolder
import id.co.ptn.tesesqgroup.ui.drink.holder.DrinksHolder
import id.co.ptn.tesesqgroup.ui.drink.holder.PopularHolder
import id.co.ptn.tesesqgroup.ui.drink.holder.RandomHolder

class FilterDrinkAdapter(
    private val items: MutableList<FilterDrink>,
    private val listener: FilterDrinkListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    companion object {
        const val VIEW_CATEGORY = 0
        const val VIEW_GLASS = 1
        const val VIEW_INGREDIENT = 2
        const val VIEW_ALCOHOLIC = 3
    }
    private lateinit var context: Context

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            FilterDrinkType.CATEGORY -> VIEW_CATEGORY
            FilterDrinkType.GLASSES -> VIEW_GLASS
            FilterDrinkType.INGREDIENT -> VIEW_INGREDIENT
            FilterDrinkType.ALCOHOLIC -> VIEW_ALCOHOLIC
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        context = parent.context
        return when (viewType) {
            VIEW_CATEGORY -> {
                val binding: ContainerHomeItemDrinkBinding = DataBindingUtil.inflate( LayoutInflater.from(context), R.layout.container_home_item_drink, parent, false)
                CategoryHolder(binding)
            }
            VIEW_GLASS -> {
                val binding: ContainerHomeItemDrinkBinding = DataBindingUtil.inflate( LayoutInflater.from(context), R.layout.container_home_item_drink, parent, false)
                GlassHolder(binding)
            }
            VIEW_INGREDIENT -> {
                val binding: ContainerHomeItemDrinkBinding = DataBindingUtil.inflate( LayoutInflater.from(context), R.layout.container_home_item_drink, parent, false)
                IngredientHolder(binding)
            }
            VIEW_ALCOHOLIC -> {
                val binding: ContainerHomeItemDrinkBinding = DataBindingUtil.inflate( LayoutInflater.from(context), R.layout.container_home_item_drink, parent, false)
                AlcoholicHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = items[position]
        when(holder) {
            is CategoryHolder -> {
                holder.setView(context, object : ItemCategoryAdapter.ItemDrinkListener{
                    override fun onItemPressed(value: String) {
                        listener.onItemCategoryPressed(value)
                    }
                }, element)
            }
            is GlassHolder -> {
                holder.setView(context, object : ItemGlassAdapter.ItemDrinkListener{
                    override fun onItemPressed(value: String) {
                        listener.onItemGlassPressed(value)
                    }
                }, element)
            }
            is IngredientHolder -> {
                holder.setView(context, object : ItemIngredientAdapter.ItemDrinkListener{
                    override fun onItemPressed(value: String) {
                        listener.onItemIngredientPressed(value)
                    }
                }, element)
            }
            is AlcoholicHolder -> {
                holder.setView(context, object : ItemAlcoholicAdapter.ItemDrinkListener{
                    override fun onItemPressed(value: String) {
                        listener.onItemAlcoholicPressed(value)
                    }
                }, element)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    public interface FilterDrinkListener{
        fun onItemCategoryPressed(string: String)
        fun onItemGlassPressed(string: String)
        fun onItemIngredientPressed(string: String)
        fun onItemAlcoholicPressed(string: String)
    }

}
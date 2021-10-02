package id.co.ptn.tesesqgroup.ui.drink.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.ptn.tesesqgroup.R
import id.co.ptn.tesesqgroup.bases.BaseViewHolder
import id.co.ptn.tesesqgroup.databinding.ContainerHomeItemDrinkBinding
import id.co.ptn.tesesqgroup.models.HomeDrink
import id.co.ptn.tesesqgroup.ui.drink.adapter.HomeDrinkAdapter.HomeViewType.POPULAR
import id.co.ptn.tesesqgroup.ui.drink.adapter.HomeDrinkAdapter.HomeViewType.DRINKS
import id.co.ptn.tesesqgroup.ui.drink.adapter.HomeDrinkAdapter.HomeViewType.RANDOM
import id.co.ptn.tesesqgroup.ui.drink.holder.DrinksHolder
import id.co.ptn.tesesqgroup.ui.drink.holder.PopularHolder
import id.co.ptn.tesesqgroup.ui.drink.holder.RandomHolder

class HomeDrinkAdapter(
    private val items: MutableList<HomeDrink>,
    private val listener: HomeDrinkListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    companion object {
        const val VIEW_POPULAR = 0
        const val VIEW_DRINKS = 1
        const val VIEW_RANDOM = 2
    }
    private lateinit var context: Context

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            POPULAR.name -> VIEW_POPULAR
            DRINKS.name -> VIEW_DRINKS
            RANDOM.name -> VIEW_RANDOM
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        context = parent.context
        return when (viewType) {
            VIEW_POPULAR -> {
                val binding: ContainerHomeItemDrinkBinding = DataBindingUtil.inflate( LayoutInflater.from(context), R.layout.container_home_item_drink, parent, false)
                PopularHolder(binding)
            }
            VIEW_DRINKS -> {
                val binding: ContainerHomeItemDrinkBinding = DataBindingUtil.inflate( LayoutInflater.from(context), R.layout.container_home_item_drink, parent, false)
                DrinksHolder(binding)
            }
            VIEW_RANDOM -> {
                val binding: ContainerHomeItemDrinkBinding = DataBindingUtil.inflate( LayoutInflater.from(context), R.layout.container_home_item_drink, parent, false)
                RandomHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = items[position]
        when(holder) {
            is PopularHolder -> {
                holder.setView(context,object : HomeDrinkListener {
                    override fun onMorePressed() {

                    }

                    override fun onItemPressed() {

                    }

                }, element)
            }
            is DrinksHolder -> {
                holder.setView(context,object : HomeDrinkListener {
                    override fun onMorePressed() {

                    }

                    override fun onItemPressed() {

                    }

                }, element)
            }
            is RandomHolder -> {
                holder.setView(context,object : HomeDrinkListener {
                    override fun onMorePressed() {

                    }

                    override fun onItemPressed() {

                    }

                }, element)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    public interface HomeDrinkListener{
        fun onMorePressed()
        fun onItemPressed()
    }

    enum class HomeViewType {
        POPULAR,
        DRINKS,
        RANDOM
    }

}
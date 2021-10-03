package id.co.ptn.tesesqgroup.ui.drink.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.ptn.tesesqgroup.R
import id.co.ptn.tesesqgroup.bases.BaseViewHolder
import id.co.ptn.tesesqgroup.databinding.ContainerHomeItemDrinkBinding
import id.co.ptn.tesesqgroup.models.*
import id.co.ptn.tesesqgroup.ui.drink.holder.*

class SearchDrinkAdapter(
    private val items: MutableList<SearchDrink>,
    private val listener: SearchDrinkListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    companion object {
        const val VIEW_RESULT = 0
        const val VIEW_SUGGESTIONS = 1
    }
    private lateinit var context: Context

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            SearchDrinkType.RESULT -> VIEW_RESULT
            SearchDrinkType.SUGGESTIONS -> VIEW_SUGGESTIONS
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        context = parent.context
        return when (viewType) {
            VIEW_RESULT -> {
                val binding: ContainerHomeItemDrinkBinding = DataBindingUtil.inflate( LayoutInflater.from(context), R.layout.container_home_item_drink, parent, false)
                SearchResultDrinksHolder(binding)
            }
            VIEW_SUGGESTIONS -> {
                val binding: ContainerHomeItemDrinkBinding = DataBindingUtil.inflate( LayoutInflater.from(context), R.layout.container_home_item_drink, parent, false)
                SuggestionsDrinkHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = items[position]
        when(holder) {
            is SearchResultDrinksHolder -> {
                holder.setView(context, object : SearchDrinkListener{
                    override fun onItemPressed(drink: Drinks) {
                        listener.onItemPressed(drink)
                    }

                }, element)
            }
            is SuggestionsDrinkHolder -> {
                holder.setView(context, object : SearchDrinkListener{
                    override fun onItemPressed(drink: Drinks) {
                        listener.onItemPressed(drink)
                    }

                }, element)
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    public interface SearchDrinkListener{
        fun onItemPressed(drink: Drinks)
    }

}
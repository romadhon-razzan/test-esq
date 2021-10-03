package id.co.ptn.tesesqgroup.ui.drink.filter.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import id.co.ptn.tesesqgroup.bases.BaseViewHolder
import id.co.ptn.tesesqgroup.databinding.ContainerHomeItemDrinkBinding
import id.co.ptn.tesesqgroup.models.FilterDrink
import id.co.ptn.tesesqgroup.models.Glasses
import id.co.ptn.tesesqgroup.ui.drink.filter.adapter.ItemGlassAdapter

class GlassHolder(private val binding: ContainerHomeItemDrinkBinding) : BaseViewHolder<MutableList<Glasses>>(binding.root) {
    private lateinit var itemGlassAdapter: ItemGlassAdapter
    fun setView(context: Context, listener: ItemGlassAdapter.ItemDrinkListener, filterDrink: FilterDrink) {
        binding.lblTitle.text = filterDrink.title
        binding.btMore.visibility = View.GONE
        setAdapter(context, listener, filterDrink.glasses)
    }

    private fun setAdapter(context: Context, listener: ItemGlassAdapter.ItemDrinkListener, drinks: MutableList<Glasses>) {
        if (drinks.size > 0) binding.progressBar.visibility = View.GONE
        else binding.progressBar.visibility = View.VISIBLE

        itemGlassAdapter = ItemGlassAdapter(drinks, object: ItemGlassAdapter.ItemDrinkListener{
            override fun onItemPressed(value: String) {
                listener.onItemPressed(value)
            }
        } )
        binding.recyclerView.apply {
            hasFixedSize()
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = itemGlassAdapter
        }
    }

    override fun bind(item: MutableList<Glasses>) {

    }
}
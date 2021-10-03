package id.co.ptn.tesesqgroup.ui.drink.filter.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import id.co.ptn.tesesqgroup.bases.BaseViewHolder
import id.co.ptn.tesesqgroup.databinding.ContainerHomeItemDrinkBinding
import id.co.ptn.tesesqgroup.models.Category
import id.co.ptn.tesesqgroup.models.FilterDrink
import id.co.ptn.tesesqgroup.ui.drink.adapter.HomeDrinkAdapter
import id.co.ptn.tesesqgroup.ui.drink.filter.adapter.ItemCategoryAdapter

class CategoryHolder(private val binding: ContainerHomeItemDrinkBinding) : BaseViewHolder<MutableList<Category>>(binding.root) {
    private lateinit var itemCategoryAdapter: ItemCategoryAdapter
    fun setView(context: Context, listener: ItemCategoryAdapter.ItemDrinkListener, filterDrink: FilterDrink) {
        binding.lblTitle.text = filterDrink.title
        binding.btMore.visibility = View.GONE
        setAdapter(context, listener, filterDrink.categories)
    }

    private fun setAdapter(context: Context, listener: ItemCategoryAdapter.ItemDrinkListener, categories: MutableList<Category>) {
        if (categories.size > 0) binding.progressBar.visibility = View.GONE
        else binding.progressBar.visibility = View.VISIBLE

        itemCategoryAdapter = ItemCategoryAdapter(categories, object: ItemCategoryAdapter.ItemDrinkListener{
            override fun onItemPressed(value: String) {
                listener.onItemPressed(value)
            }
        } )
        binding.recyclerView.apply {
            hasFixedSize()
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = itemCategoryAdapter
        }
    }

    override fun bind(item: MutableList<Category>) {

    }
}
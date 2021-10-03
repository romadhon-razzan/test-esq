package id.co.ptn.tesesqgroup.ui.drink.filter.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import id.co.ptn.tesesqgroup.bases.BaseViewHolder
import id.co.ptn.tesesqgroup.databinding.ContainerHomeItemDrinkBinding
import id.co.ptn.tesesqgroup.models.FilterDrink
import id.co.ptn.tesesqgroup.models.Ingredient
import id.co.ptn.tesesqgroup.ui.drink.filter.adapter.ItemIngredientAdapter

class IngredientHolder(private val binding: ContainerHomeItemDrinkBinding) : BaseViewHolder<MutableList<Ingredient>>(binding.root) {
    private lateinit var itemIngredientAdapter: ItemIngredientAdapter
    fun setView(context: Context, listener: ItemIngredientAdapter.ItemDrinkListener, filterDrink: FilterDrink) {
        binding.lblTitle.text = filterDrink.title
        binding.btMore.visibility = View.GONE
        setAdapter(context, listener, filterDrink.ingredients)
    }

    private fun setAdapter(context: Context, listener: ItemIngredientAdapter.ItemDrinkListener, ingredients: MutableList<Ingredient>) {
        if (ingredients.size > 0) binding.progressBar.visibility = View.GONE
        else binding.progressBar.visibility = View.VISIBLE

        itemIngredientAdapter = ItemIngredientAdapter(ingredients, object: ItemIngredientAdapter.ItemDrinkListener{
            override fun onItemPressed(value: String) {
                listener.onItemPressed(value)
            }
        } )
        binding.recyclerView.apply {
            hasFixedSize()
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = itemIngredientAdapter
        }
    }

    override fun bind(item: MutableList<Ingredient>) {

    }
}
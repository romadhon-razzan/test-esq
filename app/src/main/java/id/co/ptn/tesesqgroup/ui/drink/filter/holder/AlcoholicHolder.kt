package id.co.ptn.tesesqgroup.ui.drink.filter.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import id.co.ptn.tesesqgroup.bases.BaseViewHolder
import id.co.ptn.tesesqgroup.databinding.ContainerHomeItemDrinkBinding
import id.co.ptn.tesesqgroup.models.Alcoholic
import id.co.ptn.tesesqgroup.models.FilterDrink
import id.co.ptn.tesesqgroup.ui.drink.filter.adapter.FilterDrinkAdapter
import id.co.ptn.tesesqgroup.ui.drink.filter.adapter.ItemAlcoholicAdapter

class AlcoholicHolder(private val binding: ContainerHomeItemDrinkBinding) : BaseViewHolder<MutableList<Alcoholic>>(binding.root) {
    private lateinit var itemAlcoholicAdapter: ItemAlcoholicAdapter
    fun setView(context: Context, listener: ItemAlcoholicAdapter.ItemDrinkListener, filterDrink: FilterDrink) {
        binding.lblTitle.text = filterDrink.title
        binding.btMore.visibility = View.GONE
        setAdapter(context, listener, filterDrink.alcoholics)
    }

    private fun setAdapter(context: Context, listener: ItemAlcoholicAdapter.ItemDrinkListener, alcoholics: MutableList<Alcoholic>) {
        if (alcoholics.size > 0) binding.progressBar.visibility = View.GONE
        else binding.progressBar.visibility = View.VISIBLE

        itemAlcoholicAdapter = ItemAlcoholicAdapter(alcoholics, object: ItemAlcoholicAdapter.ItemDrinkListener{
            override fun onItemPressed(value: String) {
                listener.onItemPressed(value)
            }
        } )
        binding.recyclerView.apply {
            hasFixedSize()
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = itemAlcoholicAdapter
        }
    }

    override fun bind(item: MutableList<Alcoholic>) {

    }
}
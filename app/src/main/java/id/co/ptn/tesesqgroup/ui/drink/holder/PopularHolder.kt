package id.co.ptn.tesesqgroup.ui.drink.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.ptn.tesesqgroup.bases.BaseViewHolder
import id.co.ptn.tesesqgroup.databinding.ContainerHomeItemDrinkBinding
import id.co.ptn.tesesqgroup.models.Drinks
import id.co.ptn.tesesqgroup.models.HomeDrink
import id.co.ptn.tesesqgroup.ui.drink.adapter.HomeDrinkAdapter
import id.co.ptn.tesesqgroup.ui.drink.adapter.ItemDrinkAdapter

class PopularHolder(private val binding: ContainerHomeItemDrinkBinding) : BaseViewHolder<MutableList<Drinks>>(binding.root) {
    private lateinit var itemDrinkAdapter: ItemDrinkAdapter
    fun setView(context: Context, listener: HomeDrinkAdapter.HomeDrinkListener, homeDrink: HomeDrink) {
        binding.lblTitle.text = homeDrink.title
        binding.btMore.setOnClickListener { listener.onMorePressed() }
        setAdapter(context, listener, homeDrink.drinks)
    }

    private fun setAdapter(context: Context, listener: HomeDrinkAdapter.HomeDrinkListener, drinks: MutableList<Drinks>) {
        if (drinks.size > 0) binding.progressBar.visibility = View.GONE
        else binding.progressBar.visibility = View.VISIBLE

        itemDrinkAdapter = ItemDrinkAdapter(drinks, object: ItemDrinkAdapter.ItemDrinkListener{
            override fun onItemPressed(drink: Drinks) {
                listener.onItemPressed()
            }
        } )
        binding.recyclerView.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = itemDrinkAdapter
        }
    }

    override fun bind(item: MutableList<Drinks>) {

    }
}
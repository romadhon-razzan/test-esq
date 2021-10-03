package id.co.ptn.tesesqgroup.ui.drink

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import id.co.ptn.tesesqgroup.R
import id.co.ptn.tesesqgroup.bases.BaseActivity
import id.co.ptn.tesesqgroup.databinding.ActivityDrinksBinding
import id.co.ptn.tesesqgroup.models.Drinks
import id.co.ptn.tesesqgroup.ui.drink.adapter.ItemDrinkHorizontalAdapter
import id.co.ptn.tesesqgroup.ui.drink.viewmodel.HomeDrinkViewModel
import id.co.ptn.tesesqgroup.utils.Status

@AndroidEntryPoint
class DrinksActivity : BaseActivity() {
    private lateinit var binding: ActivityDrinksBinding
    private val viewModel: HomeDrinkViewModel by viewModels()
    private var itemDrinkHorizontalAdapter: ItemDrinkHorizontalAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var drinks: MutableList<Drinks> = mutableListOf()
    private var currentparam = "a"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_drinks)
        binding.viewModel = viewModel
        init()
    }

    private fun init() {
        initToolbar()
        initListener()
        initAdapter()
        setObserve()
        viewModel.loadMore = false
        viewModel.apiGetDrinkByFirstLetter("a")
    }

    private fun initToolbar() {
        binding.toolbar.tvTitle.text = getString(R.string.title_drinks)
    }

    private fun initListener() {
        binding.toolbar.btBack.setOnClickListener { onBackPressed() }
        binding.refresh.setOnRefreshListener {
            viewModel.loadMore = false
            viewModel.apiGetDrinkByFirstLetter("a")
        }
    }

    private fun initAdapter() {
        itemDrinkHorizontalAdapter = ItemDrinkHorizontalAdapter(drinks, object : ItemDrinkHorizontalAdapter.ItemDrinkListener{
            override fun onItemPressed(drink: Drinks) {
                toDrinkDetail(drink)
            }
        })
        linearLayoutManager = LinearLayoutManager(this@DrinksActivity)
        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = itemDrinkHorizontalAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val countItem = linearLayoutManager?.itemCount
                    val lastVisiblePosition = linearLayoutManager?.findLastCompletelyVisibleItemPosition()
                    val isLastPosition = countItem?.minus(1) == lastVisiblePosition
                    if (!viewModel.loadMore && isLastPosition) {
                        if (currentparam.isNotEmpty()){
                            currentparam = getNext(currentparam)
                            viewModel.loadMore = true
                            viewModel.apiGetDrinkByFirstLetter(currentparam)
                        }
                    }
                }
            })
        }
    }

    private fun updateAdapterData(drinks: MutableList<Drinks>?) {
        if (!viewModel.loadMore) this.drinks.clear()
        else viewModel.loadMore = false

        drinks?.let {
            this.drinks.addAll(drinks)
            itemDrinkHorizontalAdapter?.notifyDataSetChanged()
        }
    }

    private fun toDrinkDetail(drinks: Drinks) {
        val intent = Intent(this, DrinkDetailActivity::class.java)
        intent.putExtra("id", drinks.idDrink)
        startActivity(intent)
    }

    private fun setObserve() {
        viewModel.reqCocktail().observe(this, {
            setLoading(false)
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.drinks?.let { d -> updateAdapterData(d as MutableList<Drinks>) }
                }
                Status.LOADING -> { setLoading(true) }
                Status.ERROR -> {
                    setLoading(false)
                    showSnackBar(binding.container,"Error Apps")
                }
            }
        })
    }

    private fun setLoading(value: Boolean) {
        if (viewModel.loadMore)
            loading(binding.progressBarNext, value)
        else {
            binding.refresh.isRefreshing = false
            loading(binding.progressBar, value)
        }
    }

    private fun getNext(s: String) : String {
        var index = 0
        val source = "abcdefghijklmnopqrstuvwxyz"
        index  = source.indexOf(s)
        return if (index >= source.length) "" else source[index + 1].toString()
    }

}
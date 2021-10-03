package id.co.ptn.tesesqgroup.ui.drink.filter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import id.co.ptn.tesesqgroup.R
import id.co.ptn.tesesqgroup.bases.BaseActivity
import id.co.ptn.tesesqgroup.config.APPLY_FILTER
import id.co.ptn.tesesqgroup.databinding.ActivityFilterDrinkBinding
import id.co.ptn.tesesqgroup.models.*
import id.co.ptn.tesesqgroup.ui.drink.filter.adapter.FilterDrinkAdapter
import id.co.ptn.tesesqgroup.ui.drink.filter.viewmodel.ReferenceViewModel
import id.co.ptn.tesesqgroup.utils.Status

@AndroidEntryPoint
class FilterDrinkActivity : BaseActivity() {
    private lateinit var binding: ActivityFilterDrinkBinding
    private val referenceViewModel: ReferenceViewModel by viewModels()
    private var filterDrinkAdapter: FilterDrinkAdapter? = null
    private var filterDrinks: MutableList<FilterDrink> = mutableListOf()
    val query: MutableMap<String, String> = HashMap()

    companion object {
        const val ITEM_CATEGORY = 0
        const val ITEM_GLASS = 1
        const val ITEM_INGREDIENT = 2
        const val ITEM_ALCOHOLIC = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter_drink)
        init()
    }

    private fun init() {
        initToolbar()
        initListener()
        initApplyButton()
        initAdapter()
        setObserve()
        referenceViewModel.apiGetCategories()
    }

    private fun initToolbar() {
        binding.toolbar.tvTitle.text = getString(R.string.title_filter)
    }

    private fun initApplyButton() {
        binding.button.button.text = getString(R.string.button_apply)
    }

    private fun initListener() {
        binding.toolbar.btBack.setOnClickListener { onBackPressed() }
        binding.button.button.setOnClickListener { applyFilter() }
    }

    private fun initAdapter() {
        filterDrinks.add(FilterDrink(getString(R.string.label_by_category), FilterDrinkType.CATEGORY , mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf()))
        filterDrinks.add(FilterDrink(getString(R.string.label_by_glass), FilterDrinkType.GLASSES , mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf()))
        filterDrinks.add(FilterDrink(getString(R.string.label_by_ingredient), FilterDrinkType.INGREDIENT , mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf()))
        filterDrinks.add(FilterDrink(getString(R.string.label_by_alcoholic), FilterDrinkType.ALCOHOLIC , mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf()))

        filterDrinkAdapter = FilterDrinkAdapter(filterDrinks, object : FilterDrinkAdapter.FilterDrinkListener {
            override fun onItemCategoryPressed(string: String) {
                Log.d("c", string)
                query["c"] = string
            }

            override fun onItemGlassPressed(string: String) {
                Log.d("g", string)
                query["g"] = string
            }

            override fun onItemIngredientPressed(string: String) {
                Log.d("i", string)
                query["i"] = string
            }

            override fun onItemAlcoholicPressed(string: String) {
                Log.d("a", string)
                query["a"] = string
            }

        })
        binding.recyclerView.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this@FilterDrinkActivity)
            adapter = filterDrinkAdapter
        }
    }

    private fun updateCategoryData(data: MutableList<Category>? = mutableListOf()) {
        filterDrinks[ITEM_CATEGORY].categories.clear()
        data?.let { filterDrinks[ITEM_CATEGORY].categories.addAll(it) }
        filterDrinkAdapter?.notifyItemChanged(ITEM_CATEGORY)
    }

    private fun updateGlassData(data: MutableList<Glasses>? = mutableListOf()) {
        filterDrinks[ITEM_GLASS].glasses.clear()
        data?.let { filterDrinks[ITEM_GLASS].glasses.addAll(it) }
        filterDrinkAdapter?.notifyItemChanged(ITEM_GLASS)
    }

    private fun updateIngredientData(data: MutableList<Ingredient>? = mutableListOf()) {
        filterDrinks[ITEM_INGREDIENT].ingredients.clear()
        data?.let { filterDrinks[ITEM_INGREDIENT].ingredients.addAll(it) }
        filterDrinkAdapter?.notifyItemChanged(ITEM_INGREDIENT)
    }

    private fun updateAlcoholicData(data: MutableList<Alcoholic>? = mutableListOf()) {
        filterDrinks[ITEM_ALCOHOLIC].alcoholics.clear()
        data?.let { filterDrinks[ITEM_ALCOHOLIC].alcoholics.addAll(it) }
        filterDrinkAdapter?.notifyItemChanged(ITEM_ALCOHOLIC)
    }


    private fun applyFilter() {
        val intent = Intent()
        intent.putExtra("filter", Gson().toJson(query))
        setResult(APPLY_FILTER, intent)
        finish()
    }

    private fun setObserve() {
        referenceViewModel.reqCategories().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { d -> updateCategoryData(d.drinks as MutableList<Category>)}
                    referenceViewModel.apiGetGlasses()
                }
                Status.LOADING -> {  }
                Status.ERROR -> {
                    showSnackBar(binding.container,"Error Apps")
                }
            }
        })

        referenceViewModel.reqGlasses().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { d -> updateGlassData(d.drinks as MutableList<Glasses>)}
                    referenceViewModel.apiGetIngredients()
                }
                Status.LOADING -> {  }
                Status.ERROR -> {
                    showSnackBar(binding.container,"Error Apps")
                }
            }
        })

        referenceViewModel.reqIngredients().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { d -> updateIngredientData(d.drinks as MutableList<Ingredient>)}
                    referenceViewModel.apiGetAlcoholic()
                }
                Status.LOADING -> {  }
                Status.ERROR -> {
                    showSnackBar(binding.container,"Error Apps")
                }
            }
        })

        referenceViewModel.reqAlcoholic().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { d -> updateAlcoholicData(d.drinks as MutableList<Alcoholic>)}
                }
                Status.LOADING -> {  }
                Status.ERROR -> {
                    showSnackBar(binding.container,"Error Apps")
                }
            }
        })
    }
}
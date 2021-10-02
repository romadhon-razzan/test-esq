package id.co.ptn.tesesqgroup.ui.drink

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.ptn.tesesqgroup.R
import id.co.ptn.tesesqgroup.bases.BaseActivity
import id.co.ptn.tesesqgroup.databinding.ActivitySearchDrinkBinding
import id.co.ptn.tesesqgroup.models.Drinks
import id.co.ptn.tesesqgroup.models.SearchDrink
import id.co.ptn.tesesqgroup.models.SearchDrinkType
import id.co.ptn.tesesqgroup.ui.drink.adapter.SearchDrinkAdapter
import id.co.ptn.tesesqgroup.ui.drink.viewmodel.SearchDrinkViewModel
import id.co.ptn.tesesqgroup.utils.Status

@AndroidEntryPoint
class SearchDrinkActivity : BaseActivity() {
    private lateinit var binding: ActivitySearchDrinkBinding
    private val viewModel: SearchDrinkViewModel by viewModels()
    private var searchDrinkAdapter: SearchDrinkAdapter? = null
    private var searchDrinks: MutableList<SearchDrink> = mutableListOf()

    companion object {
        const val ITEM_RESULT = 0
        const val ITEM_SUGGESTIONS = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_drink)
        init()
    }

    private fun init() {
        initListener()
        initSearch()
        initAdapter()
        setObserve()
        setEmptyResult()
        viewModel.apiSuggestions()
    }

    private fun initListener() {
        binding.toolbar.btBack.setOnClickListener { onBackPressed() }
        binding.toolbar.btFilter.setOnClickListener {  }
    }

    private fun initSearch() {
        binding.toolbar.searchByName.requestFocus()
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.toolbar.searchByName, InputMethodManager.SHOW_IMPLICIT)

        binding.toolbar.searchByName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val value = p0.toString()
                if (value.isNotEmpty() && value.length > 2) {
                    binding.toolbar.searchByName.removeTextChangedListener(this)
                    Handler(Looper.getMainLooper()).postDelayed({
                        viewModel.apiSearchName(value)
                    }, 2000)
                    binding.toolbar.searchByName.addTextChangedListener(this)
                }
            }
            override fun afterTextChanged(p0: Editable?) {}

        })
    }

    private fun initAdapter() {
        searchDrinks.add(SearchDrink(getString(R.string.title_result), SearchDrinkType.RESULT ,mutableListOf()))
        searchDrinks.add(SearchDrink(getString(R.string.title_suggestions), SearchDrinkType.SUGGESTIONS ,mutableListOf()))
        searchDrinkAdapter = SearchDrinkAdapter(searchDrinks, object : SearchDrinkAdapter.SearchDrinkListener {
            override fun onItemPressed() {

            }
        })
        binding.recyclerView.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this@SearchDrinkActivity)
            adapter = searchDrinkAdapter
        }
    }

    private fun updateAdapterData(type: Int, data: MutableList<Drinks>? = mutableListOf()) {
        searchDrinks[type].drinks.clear()
        data?.let { searchDrinks[type].drinks.addAll(it) }
        searchDrinkAdapter?.notifyItemChanged(type)
    }

    private fun setEmptyResult() {
        binding.empty.container.visibility = View.GONE
    }

    private fun setObserve() {
        viewModel.reqSearch().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    loading(binding.toolbar.progressBarSearch, false)
                    it.data?.let { d -> updateAdapterData(ITEM_RESULT,
                        d.drinks as MutableList<Drinks>
                    ) }
                }
                Status.LOADING -> { loading(binding.toolbar.progressBarSearch, true) }
                Status.ERROR -> {
                    loading(binding.toolbar.progressBarSearch, false)
                    showSnackBar(binding.container,"Error Apps")
                }
            }
        })

        viewModel.reqSuggestions().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { d -> updateAdapterData(ITEM_SUGGESTIONS,
                        d.drinks as MutableList<Drinks>
                    ) }
                }
                Status.LOADING -> {  }
                Status.ERROR -> {
                    showSnackBar(binding.container,"Error Apps")
                }
            }
        })
    }
}
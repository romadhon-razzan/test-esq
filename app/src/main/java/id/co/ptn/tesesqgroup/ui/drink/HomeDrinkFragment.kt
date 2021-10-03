package id.co.ptn.tesesqgroup.ui.drink

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import id.co.ptn.tesesqgroup.R
import id.co.ptn.tesesqgroup.bases.BaseFragment
import id.co.ptn.tesesqgroup.databinding.HomeDrinkFragmentBinding
import id.co.ptn.tesesqgroup.models.Drinks
import id.co.ptn.tesesqgroup.models.HomeDrink
import id.co.ptn.tesesqgroup.models.HomeDrinkType
import id.co.ptn.tesesqgroup.ui.drink.adapter.HomeDrinkAdapter
import id.co.ptn.tesesqgroup.ui.drink.viewmodel.HomeDrinkViewModel
import id.co.ptn.tesesqgroup.utils.Status

@AndroidEntryPoint
class HomeDrinkFragment : BaseFragment() {

    companion object {
//        fun newInstance() = HomeDrinkFragment()
        const val ITEM_POPULAR = 0
        const val ITEM_DRINKS = 1
        const val ITEM_RANDOM = 2
    }

    private lateinit var binding: HomeDrinkFragmentBinding
    private val viewModel: HomeDrinkViewModel by viewModels()
    private var homeDrinkAdapter: HomeDrinkAdapter? =null
    private var homeDrinks: MutableList<HomeDrink> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.home_drink_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initListener()
        initAdapter()
        setObserve()
        viewModel.apiGetPopular()
    }

    private fun initListener() {
        binding.refresh.setOnRefreshListener { viewModel.apiGetPopular() }
        binding.containerSearch.searchByName.setOnClickListener {
            startActivity(Intent(context, SearchDrinkActivity::class.java))
        }
    }

    private fun initAdapter() {
        homeDrinks.add(HomeDrink(getString(R.string.title_popular_drink), HomeDrinkType.POPULAR, mutableListOf()))
        homeDrinks.add(HomeDrink(getString(R.string.title_drinks), HomeDrinkType.DRINKS, mutableListOf()))
        homeDrinks.add(HomeDrink(getString(R.string.title_random), HomeDrinkType.RANDOM, mutableListOf()))
        homeDrinkAdapter = HomeDrinkAdapter(homeDrinks, object : HomeDrinkAdapter.HomeDrinkListener {
            override fun onMorePressed() {
                showSnackBar(binding.container,"More Pressed")
            }

            override fun onItemPressed(drinks: Drinks) {
                toDrinkDetail(drinks)
            }
        })
        binding.recyclerView.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            adapter = homeDrinkAdapter
        }
    }

    private fun updateAdapterData(type: Int, data: MutableList<Drinks>? = mutableListOf()) {
        homeDrinks[type].drinks.clear()
        data?.let { homeDrinks[type].drinks = it }
        homeDrinkAdapter?.notifyItemChanged(type)
    }

    private fun toDrinkDetail(drinks: Drinks) {
        val intent = Intent(context, DrinkDetailActivity::class.java)
        intent.putExtra("id", drinks.idDrink)
        startActivity(intent)
    }

    private fun setObserve() {

        viewModel.reqPopular().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    updateAdapterData(ITEM_POPULAR, it.data?.drinks as MutableList<Drinks>)
                    viewModel.apiGetCocktails()
                }
                Status.LOADING -> { }
                Status.ERROR -> {
                    showSnackBar(binding.container,"Error Apps")
                }
            }
        })

        viewModel.reqCocktail().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    updateAdapterData(ITEM_DRINKS, it.data?.drinks as MutableList<Drinks>)
                    viewModel.apiGetRandom()
                }
                Status.LOADING -> { }
                Status.ERROR -> {
                    showSnackBar(binding.container,"Error Apps")
                }
            }
        })

        viewModel.reqRandom().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    updateAdapterData(ITEM_RANDOM, it.data?.drinks as MutableList<Drinks>)
                    binding.refresh.isRefreshing = false
                }
                Status.LOADING -> {  }
                Status.ERROR -> {
                    showSnackBar(binding.container,"Error Apps")
                }
            }
        })
    }
}
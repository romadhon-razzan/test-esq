package id.co.ptn.tesesqgroup.ui.drink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.ptn.tesesqgroup.R
import id.co.ptn.tesesqgroup.bases.BaseFragment
import id.co.ptn.tesesqgroup.databinding.HomeDrinkFragmentBinding
import id.co.ptn.tesesqgroup.models.Drinks
import id.co.ptn.tesesqgroup.models.HomeDrink
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
        initAdapter()
        setObserve()
        viewModel.apiGetPopular()
    }

    private fun initAdapter() {
        homeDrinks.add(HomeDrink("Popular Drink","POPULAR", mutableListOf()))
        homeDrinks.add(HomeDrink("Drinks", "DRINKS", mutableListOf()))
        homeDrinks.add(HomeDrink("Random", "RANDOM", mutableListOf()))
        homeDrinkAdapter = HomeDrinkAdapter(homeDrinks, object : HomeDrinkAdapter.HomeDrinkListener {
            override fun onMorePressed() {

            }

            override fun onItemPressed() {

            }
        })
        binding.recyclerView.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            adapter = homeDrinkAdapter
        }
    }

    private fun setObserve() {

        viewModel.reqPopular().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { d ->
                        homeDrinks[ITEM_POPULAR].drinks = d.drinks as MutableList<Drinks>
                        homeDrinkAdapter?.notifyItemChanged(0)
                    }
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
                    it.data?.let { d ->
                        homeDrinks[ITEM_DRINKS].drinks = d.drinks as MutableList<Drinks>
                        homeDrinkAdapter?.notifyItemChanged(1)
                    }

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
                    homeDrinks[ITEM_RANDOM].drinks = it.data?.drinks as MutableList<Drinks>
                    homeDrinkAdapter?.notifyItemChanged(2)
                }
                Status.LOADING -> { }
                Status.ERROR -> {
                    showSnackBar(binding.container,"Error Apps")
                }
            }
        })
    }
}
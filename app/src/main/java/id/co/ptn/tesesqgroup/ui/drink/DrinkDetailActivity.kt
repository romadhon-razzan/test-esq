package id.co.ptn.tesesqgroup.ui.drink

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.co.ptn.tesesqgroup.R
import id.co.ptn.tesesqgroup.bases.BaseActivity
import id.co.ptn.tesesqgroup.databinding.ActivityDrinkDetailBinding
import id.co.ptn.tesesqgroup.models.Drinks
import id.co.ptn.tesesqgroup.ui.drink.viewmodel.DrinkDetailViewModel
import id.co.ptn.tesesqgroup.utils.Status

@AndroidEntryPoint
class DrinkDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDrinkDetailBinding
    private val viewModel : DrinkDetailViewModel by viewModels()
    private var drinks: MutableList<Drinks> = mutableListOf()
    private var idDrink = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_drink_detail)
        binding.viewModel = viewModel
        init()
    }

    private fun init() {
        initToolbar()
        initListener()
        initIntent()
        setData()
        setObserve()
        viewModel.apiGetDetail(idDrink)
    }

    private fun initToolbar() {
        binding.toolbar.tvTitle.text = getString(R.string.title_detail)
    }

    private fun initListener() {
        binding.toolbar.btBack.setOnClickListener { onBackPressed() }
    }

    private fun initIntent() {
        intent.extras?.let {
            if (it.containsKey("id")) {
                idDrink = it.getString("id","")
            }
        }
    }

    private fun setData(drinks: Drinks? = null) {

        drinks?.let {
            Glide.with(this).load(it.strDrinkThumb).into(binding.image)
            viewModel.instruction = it.strInstructions
            viewModel.category = it.strCategory
            viewModel.alcoholic = it.strAlcoholic
            viewModel.drinkName = it.strDrink
            viewModel.glass = it.strGlass
        }
    }

    private fun setObserve() {
        viewModel.reqDetail().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    viewModel.loaded = true
                    loading(binding.progressBar, false)
                    drinks.addAll(it.data?.drinks as MutableList<Drinks>)
                    if (drinks.size > 0)
                        setData(drinks[0])
                }
                Status.LOADING -> { loading(binding.progressBar, true) }
                Status.ERROR -> { loading(binding.progressBar, false) }
            }
        })
    }
}
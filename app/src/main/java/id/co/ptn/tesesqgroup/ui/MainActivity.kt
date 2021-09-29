package id.co.ptn.tesesqgroup.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import id.co.ptn.tesesqgroup.R
import id.co.ptn.tesesqgroup.bases.BaseActivity
import id.co.ptn.tesesqgroup.databinding.ActivityMainBinding
import id.co.ptn.tesesqgroup.ui.cocktail.viewmodel.CocktailViewModel
import id.co.ptn.tesesqgroup.utils.Status

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CocktailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        setObserve()
        viewModel.apiGetCocktails()
    }

    private fun setObserve() {
        viewModel.reqCocktail().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {

                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    showSnackBar(binding.container,"Error Apps")
                }
            }
        })
    }
}
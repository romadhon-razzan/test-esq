package id.co.ptn.tesesqgroup.ui.cocktail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.co.ptn.tesesqgroup.R
import id.co.ptn.tesesqgroup.bases.BaseFragment
import id.co.ptn.tesesqgroup.databinding.CocktailListFragmentBinding
import id.co.ptn.tesesqgroup.ui.cocktail.viewmodel.CocktailViewModel
import id.co.ptn.tesesqgroup.utils.Status

@AndroidEntryPoint
class CocktailListFragment : BaseFragment() {

    companion object {
        fun newInstance() = CocktailListFragment()
    }

    private lateinit var binding: CocktailListFragmentBinding
    private val viewModel: CocktailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.cocktail_list_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setObserve()
        viewModel.apiGetCocktails()
    }

    private fun setObserve() {
        viewModel.reqCocktail().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    loading(binding.progressBar, false)
                }
                Status.LOADING -> {
                    loading(binding.progressBar, true)
                }
                Status.ERROR -> {
                    loading(binding.progressBar, false)
                    showSnackBar(binding.container,"Error Apps")
                }
            }
        })
    }
}
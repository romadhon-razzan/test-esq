package id.co.ptn.tesesqgroup.ui.cocktail.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.ptn.tesesqgroup.repositories.AppRepository
import javax.inject.Inject

@HiltViewModel
class CocktailViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    /** Api */
    fun apiGetCocktails() {

    }
}
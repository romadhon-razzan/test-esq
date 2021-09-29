package id.co.ptn.tesesqgroup.ui.cocktail.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.ptn.tesesqgroup.models.Cocktail
import id.co.ptn.tesesqgroup.repositories.AppRepository
import id.co.ptn.tesesqgroup.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    private var _reqCocktail: MutableLiveData<Resource<Cocktail>> = MutableLiveData()

    fun reqCocktail(): MutableLiveData<Resource<Cocktail>> = _reqCocktail

    /** Api */
    fun apiGetCocktails() {
        viewModelScope.launch {
            try {
                _reqCocktail.postValue(Resource.loading(null))
                repository.getCocktails().let {
                    if (it.isSuccessful) {
                        _reqCocktail.postValue(Resource.success(it.body()))
                    } else _reqCocktail.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }catch (e: Exception) {
                Log.e("NETWORK ERROR", e.printStackTrace().toString())
            }
        }
    }
}
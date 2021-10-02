package id.co.ptn.tesesqgroup.ui.drink.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.ptn.tesesqgroup.models.DrinkResponse
import id.co.ptn.tesesqgroup.repositories.AppRepository
import id.co.ptn.tesesqgroup.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeDrinkViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    private var _reqDrinkResponse: MutableLiveData<Resource<DrinkResponse>> = MutableLiveData()

    fun reqCocktail(): MutableLiveData<Resource<DrinkResponse>> = _reqDrinkResponse

    private var _reqRandomResponse: MutableLiveData<Resource<DrinkResponse>> = MutableLiveData()

    fun reqRandom(): MutableLiveData<Resource<DrinkResponse>> = _reqRandomResponse

    /** Api */
    fun apiGetCocktails() {
        viewModelScope.launch {
            try {
                _reqDrinkResponse.postValue(Resource.loading(null))
                repository.getCocktails().let {
                    if (it.isSuccessful) {
                        _reqDrinkResponse.postValue(Resource.success(it.body()))
                    } else _reqDrinkResponse.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }catch (e: Exception) {
                Log.e("NETWORK ERROR", e.printStackTrace().toString())
            }
        }
    }

    fun apiGetRandom() {
        viewModelScope.launch {
            try {
                _reqRandomResponse.postValue(Resource.loading(null))
                repository.getRandom().let {
                    if (it.isSuccessful) {
                        _reqRandomResponse.postValue(Resource.success(it.body()))
                    } else _reqRandomResponse.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }catch (e: Exception) {
                Log.e("NETWORK ERROR", e.printStackTrace().toString())
            }
        }
    }
}
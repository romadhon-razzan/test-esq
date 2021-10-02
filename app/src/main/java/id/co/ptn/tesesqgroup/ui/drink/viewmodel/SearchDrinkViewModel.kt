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
class SearchDrinkViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    private var _reqSearchResponse: MutableLiveData<Resource<DrinkResponse>> = MutableLiveData()

    fun reqSearch(): MutableLiveData<Resource<DrinkResponse>> = _reqSearchResponse

    private var _reqSuggestionsResponse: MutableLiveData<Resource<DrinkResponse>> = MutableLiveData()

    fun reqSuggestions(): MutableLiveData<Resource<DrinkResponse>> = _reqSuggestionsResponse

    /** Api */
    fun apiSearchName(s: String) {
        viewModelScope.launch {
            try {
                _reqSearchResponse.postValue(Resource.loading(null))
                repository.searchName(s).let {
                    if (it.isSuccessful) {
                        _reqSearchResponse.postValue(Resource.success(it.body()))
                    } else _reqSearchResponse.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } catch (e: Exception) {
                Log.e("NETWORK ERROR", e.printStackTrace().toString())
            }
        }
    }

    fun apiSuggestions() {
        viewModelScope.launch {
            try {
                _reqSuggestionsResponse.postValue(Resource.loading(null))
                repository.getRecent().let {
                    if (it.isSuccessful) {
                        _reqSuggestionsResponse.postValue(Resource.success(it.body()))
                    } else _reqSuggestionsResponse.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } catch (e: Exception) {
                Log.e("NETWORK ERROR", e.printStackTrace().toString())
            }
        }
    }
}
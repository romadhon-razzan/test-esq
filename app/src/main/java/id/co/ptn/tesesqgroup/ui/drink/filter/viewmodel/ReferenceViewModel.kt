package id.co.ptn.tesesqgroup.ui.drink.filter.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.ptn.tesesqgroup.models.*
import id.co.ptn.tesesqgroup.repositories.ReferenceRepository
import id.co.ptn.tesesqgroup.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReferenceViewModel @Inject constructor(private val repository: ReferenceRepository) : ViewModel() {

    private var _reqCategoryResponse: MutableLiveData<Resource<CategoryResponse>> = MutableLiveData()
    fun reqCategories(): MutableLiveData<Resource<CategoryResponse>> = _reqCategoryResponse

    private var _reqGlassesResponse: MutableLiveData<Resource<GlassesResponse>> = MutableLiveData()
    fun reqGlasses(): MutableLiveData<Resource<GlassesResponse>> = _reqGlassesResponse

    private var _reqIngredientResponse: MutableLiveData<Resource<IngredientResponse>> = MutableLiveData()
    fun reqIngredients(): MutableLiveData<Resource<IngredientResponse>> = _reqIngredientResponse

    private var _reqAlcoholicResponse: MutableLiveData<Resource<AlcoholicResponse>> = MutableLiveData()
    fun reqAlcoholic(): MutableLiveData<Resource<AlcoholicResponse>> = _reqAlcoholicResponse


    /** Api */
    fun apiGetCategories() {
        viewModelScope.launch {
            try {
                _reqCategoryResponse.postValue(Resource.loading(null))
                repository.getCategories().let {
                    if (it.isSuccessful) {
                        _reqCategoryResponse.postValue(Resource.success(it.body()))
                    } else _reqCategoryResponse.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } catch (e: Exception) {
                Log.e("NETWORK ERROR", e.printStackTrace().toString())
            }
        }
    }

    fun apiGetGlasses() {
        viewModelScope.launch {
            try {
                _reqGlassesResponse.postValue(Resource.loading(null))
                repository.getGlasses().let {
                    if (it.isSuccessful) {
                        _reqGlassesResponse.postValue(Resource.success(it.body()))
                    } else _reqGlassesResponse.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } catch (e: Exception) {
                Log.e("NETWORK ERROR", e.printStackTrace().toString())
            }
        }
    }

    fun apiGetIngredients() {
        viewModelScope.launch {
            try {
                _reqIngredientResponse.postValue(Resource.loading(null))
                repository.getIngredients().let {
                    if (it.isSuccessful) {
                        _reqIngredientResponse.postValue(Resource.success(it.body()))
                    } else _reqIngredientResponse.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } catch (e: Exception) {
                Log.e("NETWORK ERROR", e.printStackTrace().toString())
            }
        }
    }

    fun apiGetAlcoholic() {
        viewModelScope.launch {
            try {
                _reqAlcoholicResponse.postValue(Resource.loading(null))
                repository.getAlcoholic().let {
                    if (it.isSuccessful) {
                        _reqAlcoholicResponse.postValue(Resource.success(it.body()))
                    } else _reqAlcoholicResponse.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } catch (e: Exception) {
                Log.e("NETWORK ERROR", e.printStackTrace().toString())
            }
        }
    }
}
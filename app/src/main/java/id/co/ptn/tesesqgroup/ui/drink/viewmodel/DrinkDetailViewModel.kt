package id.co.ptn.tesesqgroup.ui.drink.viewmodel

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.ptn.tesesqgroup.bases.BaseViewModel
import id.co.ptn.tesesqgroup.models.DrinkResponse
import id.co.ptn.tesesqgroup.repositories.AppRepository
import id.co.ptn.tesesqgroup.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkDetailViewModel @Inject constructor(private val repository: AppRepository) : BaseViewModel() {
    private var _reqDetailResponse: MutableLiveData<Resource<DrinkResponse>> = MutableLiveData()
    fun reqDetail(): MutableLiveData<Resource<DrinkResponse>> = _reqDetailResponse

    @get:Bindable
    var loaded: Boolean? = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loaded)
        }

    @get:Bindable
    var drinkName: String? = "-"
        set(value) {
            field = value
            notifyPropertyChanged(BR.drinkName)
        }

    @get:Bindable
    var category: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.category)
        }

    @get:Bindable
    var alcoholic: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.alcoholic)
        }

    @get:Bindable
    var instruction: String? = "-"
        set(value) {
            field = value
            notifyPropertyChanged(BR.instruction)
        }

    @get:Bindable
    var glass: String? = "-"
        set(value) {
            field = value
            notifyPropertyChanged(BR.glass)
        }

    /** Api */
    fun apiGetDetail(s: String) {
        viewModelScope.launch {
            try {
                _reqDetailResponse.postValue(Resource.loading(null))
                repository.detail(s).let {
                    if (it.isSuccessful) {
                        _reqDetailResponse.postValue(Resource.success(it.body()))
                    } else _reqDetailResponse.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }catch (e: Exception) {
                Log.e("NETWORK ERROR", e.printStackTrace().toString())
            }
        }
    }

}
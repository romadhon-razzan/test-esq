package id.co.ptn.tesesqgroup.repositories

import id.co.ptn.tesesqgroup.network.ApiHelper
import javax.inject.Inject

class ReferenceRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getCategories() =  apiHelper.getCategories()
    suspend fun getGlasses() =  apiHelper.getGlasses()
    suspend fun getIngredients() =  apiHelper.getIngredients()
    suspend fun getAlcoholic() =  apiHelper.getAlcoholic()
}
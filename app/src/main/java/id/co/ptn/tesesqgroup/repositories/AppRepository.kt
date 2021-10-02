package id.co.ptn.tesesqgroup.repositories

import id.co.ptn.tesesqgroup.network.ApiHelper
import javax.inject.Inject

class AppRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getCocktails() =  apiHelper.getCocktails()
    suspend fun getPopular() =  apiHelper.getPopular()
    suspend fun getRandom() =  apiHelper.getRandom()
}
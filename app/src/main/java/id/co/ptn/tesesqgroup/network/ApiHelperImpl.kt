package id.co.ptn.tesesqgroup.network

import id.co.ptn.tesesqgroup.models.DrinkResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: Services) : ApiHelper {
    override suspend fun getCocktails(): Response<DrinkResponse> = apiService.getCocktails()
    override suspend fun getPopular(): Response<DrinkResponse> = apiService.getPopular()
    override suspend fun getRandom(): Response<DrinkResponse> = apiService.getRandom()
    override suspend fun searchName(s: String): Response<DrinkResponse> = apiService.searchName(s)
    override suspend fun getRecent(): Response<DrinkResponse> = apiService.getRecent()
}
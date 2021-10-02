package id.co.ptn.tesesqgroup.network

import id.co.ptn.tesesqgroup.models.DrinkResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getCocktails(): Response<DrinkResponse>
    suspend fun getPopular(): Response<DrinkResponse>
    suspend fun getRandom(): Response<DrinkResponse>
    suspend fun searchName(s: String): Response<DrinkResponse>
    suspend fun getRecent(): Response<DrinkResponse>
}
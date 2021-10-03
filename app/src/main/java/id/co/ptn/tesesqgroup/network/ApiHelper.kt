package id.co.ptn.tesesqgroup.network

import id.co.ptn.tesesqgroup.models.*
import retrofit2.Response

interface ApiHelper {
    suspend fun getCocktails(): Response<DrinkResponse>
    suspend fun getPopular(): Response<DrinkResponse>
    suspend fun getRandom(): Response<DrinkResponse>
    suspend fun searchName(s: String): Response<DrinkResponse>
    suspend fun getRecent(): Response<DrinkResponse>
    suspend fun getCategories(): Response<CategoryResponse>
    suspend fun getGlasses(): Response<GlassesResponse>
    suspend fun getIngredients(): Response<IngredientResponse>
    suspend fun getAlcoholic(): Response<AlcoholicResponse>
    suspend fun filter(params: Map<String, String>): Response<DrinkResponse>
}
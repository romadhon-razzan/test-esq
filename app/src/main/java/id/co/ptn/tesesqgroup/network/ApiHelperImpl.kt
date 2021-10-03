package id.co.ptn.tesesqgroup.network

import id.co.ptn.tesesqgroup.models.*
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: Services) : ApiHelper {
    override suspend fun getCocktails(): Response<DrinkResponse> = apiService.getCocktails()
    override suspend fun getPopular(): Response<DrinkResponse> = apiService.getPopular()
    override suspend fun getRandom(): Response<DrinkResponse> = apiService.getRandom()
    override suspend fun searchName(s: String): Response<DrinkResponse> = apiService.searchName(s)
    override suspend fun detail(s: String): Response<DrinkResponse> = apiService.detail(s)
    override suspend fun getRecent(): Response<DrinkResponse> = apiService.getRecent()
    override suspend fun getCategories(): Response<CategoryResponse> = apiService.getCategories()
    override suspend fun getGlasses(): Response<GlassesResponse> = apiService.getGlasses()
    override suspend fun getIngredients(): Response<IngredientResponse> = apiService.getIngredients()
    override suspend fun getAlcoholic(): Response<AlcoholicResponse> = apiService.getAlcoholic()
    override suspend fun filter(params: Map<String, String>): Response<DrinkResponse> = apiService.filter(params)
}
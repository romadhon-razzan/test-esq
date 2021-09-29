package id.co.ptn.tesesqgroup.network

import id.co.ptn.tesesqgroup.models.Cocktail
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: Services) : ApiHelper {
    override suspend fun getCocktails(): Response<Cocktail> = apiService.getCocktails()
}
package id.co.ptn.tesesqgroup.network

import id.co.ptn.tesesqgroup.models.Cocktail
import retrofit2.Response

interface ApiHelper {
    suspend fun getCocktails(): Response<Cocktail>
}
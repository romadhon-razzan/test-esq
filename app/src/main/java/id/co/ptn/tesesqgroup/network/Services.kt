package id.co.ptn.tesesqgroup.network

import id.co.ptn.tesesqgroup.models.Cocktail
import retrofit2.Response
import retrofit2.http.*

interface Services {
    @GET(GET_COCKTAIL)
    suspend fun getCocktails(): Response<Cocktail>
}
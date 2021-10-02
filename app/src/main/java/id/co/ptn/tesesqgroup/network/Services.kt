package id.co.ptn.tesesqgroup.network

import id.co.ptn.tesesqgroup.models.DrinkResponse
import retrofit2.Response
import retrofit2.http.*

interface Services {
    @GET(GET_COCKTAIL)
    suspend fun getCocktails(): Response<DrinkResponse>

    @GET(GET_POPULAR)
    suspend fun getPopular(): Response<DrinkResponse>

    @GET(GET_RANDOM)
    suspend fun getRandom(): Response<DrinkResponse>

    @GET(SEARCH_NAME)
    suspend fun searchName( @Query("s") s: String): Response<DrinkResponse>

    @GET(GET_RECENT)
    suspend fun getRecent(): Response<DrinkResponse>
}
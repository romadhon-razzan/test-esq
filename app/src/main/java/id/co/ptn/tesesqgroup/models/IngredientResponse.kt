package id.co.ptn.tesesqgroup.models

import com.google.gson.annotations.SerializedName

data class IngredientResponse (
    @SerializedName("drinks") var drinks : List<Ingredient>
    )

data class Ingredient (
    @SerializedName("strIngredient1") var strIngredient1 : String
)
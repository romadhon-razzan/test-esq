package id.co.ptn.tesesqgroup.models

import com.google.gson.annotations.SerializedName

data class Cocktail (
    @SerializedName("drinks") var drinks : List<Drinks>
    )
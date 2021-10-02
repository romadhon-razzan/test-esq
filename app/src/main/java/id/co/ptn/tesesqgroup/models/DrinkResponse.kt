package id.co.ptn.tesesqgroup.models

import com.google.gson.annotations.SerializedName

data class DrinkResponse (
    @SerializedName("drinks") var drinks : List<Drinks>
    )
package id.co.ptn.tesesqgroup.models

import com.google.gson.annotations.SerializedName

data class GlassesResponse (
    @SerializedName("drinks") var drinks : List<Glasses>
    )

data class Glasses (
    @SerializedName("strGlass") var strGlass : String
)
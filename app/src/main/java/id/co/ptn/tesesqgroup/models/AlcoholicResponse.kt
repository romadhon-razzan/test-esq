package id.co.ptn.tesesqgroup.models

import com.google.gson.annotations.SerializedName

data class AlcoholicResponse (
    @SerializedName("drinks") var drinks : List<Alcoholic>
    )

data class Alcoholic (
    @SerializedName("strAlcoholic") var strAlcoholic : String
)
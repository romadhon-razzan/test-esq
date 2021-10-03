package id.co.ptn.tesesqgroup.models

import com.google.gson.annotations.SerializedName

data class CategoryResponse (
    @SerializedName("drinks") var drinks : List<Category>
    )

data class Category (
    @SerializedName("strCategory") var strCategory : String
)
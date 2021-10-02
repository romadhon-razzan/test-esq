package id.co.ptn.tesesqgroup.models

data class HomeDrink (
    var title: String,
    var type: String,
    var drinks: MutableList<Drinks>)
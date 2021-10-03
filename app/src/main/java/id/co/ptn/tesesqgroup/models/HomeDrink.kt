package id.co.ptn.tesesqgroup.models

data class HomeDrink (
    var title: String,
    var type: HomeDrinkType,
    var drinks: MutableList<Drinks>)
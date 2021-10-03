package id.co.ptn.tesesqgroup.models

data class SearchDrink (
    var title: String,
    var type: SearchDrinkType,
    var drinks: MutableList<Drinks>)
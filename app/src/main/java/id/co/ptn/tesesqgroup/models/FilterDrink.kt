package id.co.ptn.tesesqgroup.models

data class FilterDrink (
    var title: String,
    var type: FilterDrinkType,
    var categories: MutableList<Category>,
    var glasses: MutableList<Glasses>,
    var ingredients: MutableList<Ingredient>,
    var alcoholics: MutableList<Alcoholic>)
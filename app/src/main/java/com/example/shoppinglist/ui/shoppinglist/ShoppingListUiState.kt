package com.example.shoppinglist.ui.shoppinglist

data class ShoppingListUiState(
    val isDialogDisplayed: Boolean = false,
    val isItemNameInputInvalid: Boolean = false,
    val isItemQtyInputInvalid: Boolean = false,
    val isItemNameEmpty: Boolean = false,
    val isItemAlreadyOnTheList: Boolean = false,
    val isItemQtyEmpty: Boolean = false,
    val shoppingListItems: List<ShoppingListItem> = listOf()
)
package com.example.shoppinglist.viewmodel

import com.example.shoppinglist.model.ShoppingListItem

data class ShoppingListUiState(
    val newItemDialogState: NewItemDialogState = NewItemDialogState(),
    val shoppingListItemsState: ShoppingListItemsState = ShoppingListItemsState()
)

data class NewItemDialogState(
    val isDialogDisplayed: Boolean = false,
    val isItemNameInputInvalid: Boolean = false,
    val isItemQtyInputInvalid: Boolean = false,
    val isItemNameEmpty: Boolean = false,
    val isItemAlreadyOnTheList: Boolean = false,
    val isItemQtyEmpty: Boolean = false,
)

data class ShoppingListItemsState(
    val shoppingListItems: List<ShoppingListItem> = listOf()
)

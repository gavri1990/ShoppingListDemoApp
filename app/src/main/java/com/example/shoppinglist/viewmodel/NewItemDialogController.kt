package com.example.shoppinglist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.shoppinglist.model.ShoppingListItem

class NewItemDialogController(
    private val updateShoppingListUiState: ((ShoppingListUiState) -> ShoppingListUiState) -> Unit) {
    var dialogItemName by mutableStateOf("")
        private set

    var dialogItemQty by mutableStateOf("")
        private set

    fun updateDialogItemName(itemName: String){
        dialogItemName = itemName
    }

    fun updateDialogItemQty(itemQty: String){
        dialogItemQty = if((itemQty.toIntOrNull() ?: 0) == 0) "" else itemQty   //characters or 0 not allowed. A completely empty value (user presses backspace key) will also give 0, so again alertDItemQty = ""
    }

    fun updateDialogDisplayed(isDisplayed: Boolean){
        updateShoppingListUiState{ current ->
            current.copy(newItemDialogState = current.newItemDialogState.copy(isDialogDisplayed = isDisplayed))
        }
    }

    fun checkFilledValues(){
        updateShoppingListUiState{ current ->
            val isNameEmpty = dialogItemName.isBlank()
            val isNameOnTheList = current.shoppingListItemsState.shoppingListItems.any {
                it.name.equals(dialogItemName, ignoreCase = true)}
            val isNameInputInvalid = isNameEmpty || isNameOnTheList
            val isQtyEmpty = dialogItemQty.isBlank()
            val isQtyInputInvalid = dialogItemQty.isBlank()
            val shouldAddItem = !isNameInputInvalid && !isQtyInputInvalid

            val newIDialogState = current.newItemDialogState.copy(
                isItemNameInputInvalid = isNameInputInvalid,
                isItemNameEmpty = isNameEmpty,
                isItemAlreadyOnTheList = isNameOnTheList,
                isItemQtyInputInvalid = isQtyInputInvalid,
                isItemQtyEmpty = isQtyEmpty
            )

            val newSListItemsState = if(shouldAddItem){
                current.shoppingListItemsState.copy(
                    shoppingListItems = current.shoppingListItemsState.shoppingListItems + ShoppingListItem(
                        id = (current.shoppingListItemsState.shoppingListItems.maxOfOrNull { it.id } ?: 0) + 1,
                        name = dialogItemName,
                        quantity = dialogItemQty))
            }
            else
                current.shoppingListItemsState

            if(shouldAddItem){
                updateDialogItemName("")    //we have copied these properties' values, so we can reset them now
                updateDialogItemQty("")
            }

            current.copy(
                newItemDialogState = newIDialogState,
                shoppingListItemsState = newSListItemsState
            )
        }
    }
}
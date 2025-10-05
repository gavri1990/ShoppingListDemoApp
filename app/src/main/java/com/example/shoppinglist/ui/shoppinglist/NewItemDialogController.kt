package com.example.shoppinglist.ui.shoppinglist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

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
        val isNameInputInvalid = (dialogItemName.isBlank())
        val isQtyInputInvalid = (dialogItemQty.isBlank())
        updateShoppingListUiState{ current ->
            current.copy(newItemDialogState = current.newItemDialogState.copy(
                isItemNameInputInvalid = isNameInputInvalid,
                isItemNameEmpty = dialogItemName.isBlank(),
                isItemAlreadyOnTheList = false,
                isItemQtyInputInvalid = isQtyInputInvalid,
                isItemQtyEmpty = dialogItemQty.isBlank())
            )
        }
        if(!isNameInputInvalid && !isQtyInputInvalid){
            updateShoppingListUiState{ current ->
                current.copy(shoppingListItemsState = current.shoppingListItemsState.copy(
                    shoppingListItems = current.shoppingListItemsState.shoppingListItems + ShoppingListItem(
                        id = (current.shoppingListItemsState.shoppingListItems.maxOfOrNull { it.id } ?: 0) + 1,
                        name = dialogItemName,
                        quantity = dialogItemQty)
                    )
                )
            }
            updateDialogItemName("")
            updateDialogItemQty("")
        }
    }
}
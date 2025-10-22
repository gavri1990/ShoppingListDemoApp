package com.example.shoppinglist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.shoppinglist.data.entity.ShoppingListItem

class NewItemDialogController(
    private val updateShoppingListUiState: ((ShoppingListUiState) -> ShoppingListUiState) -> Unit,
    private val insertItemToDb: (ShoppingListItem) -> Unit) {
    var dialogItemName by mutableStateOf("")
        private set

    var dialogItemQty by mutableStateOf("")
        private set

    fun updateDialogItemName(itemName: String){
        dialogItemName = itemName
    }

    fun updateDialogItemQty(itemQty: String){
        // Ignore non-numeric input always and initial zero when field is empty. An blank input does not get ignored, so that the textfield can be cleared
        val inputQtyToIntOrNull = itemQty.toIntOrNull()
        val isInputQtyEmpty = itemQty.isEmpty()
        val isTextFieldEmpty = dialogItemQty.isEmpty()
        val shouldIgnoreInputQty = (!isInputQtyEmpty && inputQtyToIntOrNull == null) || (isTextFieldEmpty && inputQtyToIntOrNull == 0)
        if (!shouldIgnoreInputQty)
            dialogItemQty = itemQty
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

            if(shouldAddItem){
                val newItem = ShoppingListItem(
                    name = dialogItemName,
                    quantity = dialogItemQty)
                insertItemToDb(newItem)
                updateDialogItemName("")    //we have copied these properties' values, so we can reset them now
                updateDialogItemQty("")
            }

            current.copy(
                newItemDialogState = newIDialogState
            )
        }
    }
}
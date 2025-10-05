package com.example.shoppinglist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.ui.shoppinglist.ShoppingListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ShoppingListViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ShoppingListUiState())
    val uiState: StateFlow<ShoppingListUiState> = _uiState.asStateFlow()

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
        _uiState.update { currentState ->
            currentState.copy(isDialogDisplayed = isDisplayed)
        }
    }

    fun checkFilledValues(){
        val isNameInputInvalid = (dialogItemName.isBlank())
        val isQtyInputInvalid = (dialogItemQty.isBlank())
        _uiState.update { currentState ->
            currentState.copy(
                isItemNameInputInvalid = isNameInputInvalid,
                isItemNameEmpty = dialogItemName.isBlank(),
                isItemAlreadyOnTheList = false,
                isItemQtyInputInvalid = isQtyInputInvalid,
                isItemQtyEmpty = dialogItemQty.isBlank()
            )
        }
        if(!isNameInputInvalid && !isQtyInputInvalid){
            updateDialogItemName("")
            updateDialogItemQty("")
        }
    }
}





//                            if(!alreadyExistingItem){
//                                shopListItems = shopListItems + ShoppingListItem(
//                                    id = (shopListItems.maxOfOrNull { it.id } ?: 0) + 1,
//                                    name = alertDItemName,
//                                    quantity = alertDItemQty)
//                                Log.i("Item addition", "New Item added to List")
//                                //[ALTERNATIVE]
////                                            shopListItems.add(
////                                                ShoppingListItem(
////                                                    id = (shopListItems.maxOfOrNull { it.id } ?: 0) + 1,
////                                                    name = alertDItemName,
////                                                    quantity = alertDItemQty))
//                                Toast.makeText(
//                                    LocalContext.current,
//                                    "$alertDItemName with a quantity of $alertDItemQty added to the shopping list",
//                                    Toast.LENGTH_LONG
//                                ).show()
//                                alertDItemName = ""
//                                alertDItemQty = ""
//                            }
//                        }
//                    },
//                    shapeP = RectangleShape)
//                CustomAlertButton(
//                    textP = "Close",
//                    onClickP = { shouldAlertDBeDisplayed = false },
//                    shapeP = RectangleShape)
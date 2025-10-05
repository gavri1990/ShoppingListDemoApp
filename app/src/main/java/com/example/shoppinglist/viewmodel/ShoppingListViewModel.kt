package com.example.shoppinglist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.ui.shoppinglist.NewItemDialogController
import com.example.shoppinglist.ui.shoppinglist.ShoppingListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ShoppingListViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ShoppingListUiState())
    val uiState: StateFlow<ShoppingListUiState> = _uiState.asStateFlow()

    val newItemDialogController = NewItemDialogController{transform ->
        _uiState.update(transform)} //passing a lambda function that can be called by the controller(the controller passes the transform when it calls the function. It gets executed in the viewmodel)

    //For NewItemDialog


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
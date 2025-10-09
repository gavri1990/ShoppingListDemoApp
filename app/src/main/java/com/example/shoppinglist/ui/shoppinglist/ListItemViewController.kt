package com.example.shoppinglist.ui.shoppinglist

class ListItemViewController(
    private val updateShoppingListUiState: ((ShoppingListUiState) -> ShoppingListUiState) -> Unit) {

    fun increaseItemQty(currentItem: ShoppingListItem){
        updateShoppingListUiState{current ->
            current.copy(shoppingListItemsState = current.shoppingListItemsState.copy(
                shoppingListItems = current.shoppingListItemsState.shoppingListItems.map { item ->
                    if(item.id == currentItem.id)
                        item.copy(quantity = ((item.quantity.toInt()) + 1).toString())  //no need for toIntOrNull, NewItemDialog Textfiels don't allow 0 or non Ints
                    else item
                }
            ))
        }
    }

    fun decreaseItemQty(currentItem: ShoppingListItem){
        updateShoppingListUiState{current ->
            current.copy(shoppingListItemsState = current.shoppingListItemsState.copy(
                shoppingListItems = current.shoppingListItemsState.shoppingListItems.map { item ->
                    if(item.id == currentItem.id && (item.quantity.toInt()) > 1)
                        item.copy(quantity = (item.quantity.toInt() - 1).toString())
                    else item
                }
            ))
        }
    }

    fun removeItem(currentItem: ShoppingListItem){
        updateShoppingListUiState{current ->
            current.copy(shoppingListItemsState = current.shoppingListItemsState.copy(
                shoppingListItems = current.shoppingListItemsState.shoppingListItems - currentItem
            ))
        }
    }
}


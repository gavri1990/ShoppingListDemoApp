package com.example.shoppinglist.viewmodel

import com.example.shoppinglist.data.entity.ShoppingListItem

class ListItemViewController(
    private val updateItemInDb: (ShoppingListItem) -> Unit,
    private val deleteItemFromDb: (ShoppingListItem) -> Unit) {

    fun increaseItemQty(currentItem: ShoppingListItem){
        val updatedItem = currentItem.copy(quantity = (currentItem.quantity.toInt() + 1).toString()) //no need for toIntOrNull, NewItemDialog Textfiels don't allow 0 or non Ints
        updateItemInDb(updatedItem) //id is not changed, so it is treated as the same database row
    }

    fun decreaseItemQty(currentItem: ShoppingListItem){
        if(currentItem.quantity.toInt() > 1){
            val updatedItem = currentItem.copy(quantity = (currentItem.quantity.toInt() -1).toString()) //no need for toIntOrNull, NewItemDialog Textfiels don't allow 0 or non Ints
            updateItemInDb(updatedItem)
        }
    }

    fun removeItem(currentItem: ShoppingListItem){
        deleteItemFromDb(currentItem)
    }
}


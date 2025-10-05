package com.example.shoppinglist.ui.shoppinglist

data class ShoppingListItem(val id: Int,
                            var name: String,
                            var quantity: String,
                            var isCurrentlyEdited: Boolean = false)

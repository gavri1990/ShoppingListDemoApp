package com.example.shoppinglist.ui.theme.home

data class ShoppingListItem(val id: Int,
                            var name: String,
                            var quantity: Int,
                            var isCurrentlyEdited: Boolean = false)

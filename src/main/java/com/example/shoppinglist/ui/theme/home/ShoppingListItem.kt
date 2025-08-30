package com.example.shoppinglist.ui.theme.home

data class ShoppingListItem(val id: UShort,
                            val name: String,
                            val quantity: UShort,
                            var isCurrentlyEdited: Boolean = false)

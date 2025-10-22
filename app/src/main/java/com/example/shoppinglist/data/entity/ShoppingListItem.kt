package com.example.shoppinglist.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list_item")
data class ShoppingListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,    // default to 0, Room will auto-generate on insert
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "quantity")
    val quantity: String,
    @ColumnInfo(name = "isCurrentlyEdited")
    val isCurrentlyEdited: Boolean = false
)
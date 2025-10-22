package com.example.shoppinglist.domain.repository

import androidx.lifecycle.LiveData
import com.example.shoppinglist.data.entity.ShoppingListItem
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun insert(item: ShoppingListItem)

    suspend fun update(item: ShoppingListItem)

    suspend fun delete(item: ShoppingListItem)

    fun getAllShoppingItems(): Flow<List<ShoppingListItem>>
}

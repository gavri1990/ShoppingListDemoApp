package com.example.shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shoppinglist.data.entity.ShoppingListItem
import kotlinx.coroutines.flow.Flow

@Dao    //Room will generate the concrete implementation of the interface
interface ShoppingListDao {

    @Insert
    suspend fun insert(item: ShoppingListItem)

    @Update
    suspend fun update(item: ShoppingListItem)

    @Delete
    suspend fun delete(item: ShoppingListItem)

    @Query("select * from shopping_list_item")
    fun getAllShoppingItems(): Flow<List<ShoppingListItem>>
}
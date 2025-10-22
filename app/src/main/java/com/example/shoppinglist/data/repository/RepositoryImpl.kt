package com.example.shoppinglist.data.repository

import com.example.shoppinglist.data.db.ShoppingListDao
import com.example.shoppinglist.data.db.ShoppingListDatabase
import com.example.shoppinglist.data.entity.ShoppingListItem
import com.example.shoppinglist.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor( //declared as a substitute of Repository in the bindRepository of the RepositoryModule
    private val dao: ShoppingListDao
) : Repository {
    override suspend fun insert(item: ShoppingListItem) = dao.insert(item)

    override suspend fun update(item: ShoppingListItem) = dao.update(item)

    override suspend fun delete(item: ShoppingListItem) = dao.delete(item)

    override fun getAllShoppingItems() = dao.getAllShoppingItems()
}
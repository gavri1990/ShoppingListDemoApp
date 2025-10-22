package com.example.shoppinglist.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.entity.ShoppingListItem
import com.example.shoppinglist.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val shoppingListRepository: Repository
): ViewModel() {
    private val _uiState = MutableStateFlow(ShoppingListUiState())
    val uiState: StateFlow<ShoppingListUiState> = _uiState.asStateFlow()

    val newItemDialogController = NewItemDialogController(
        updateShoppingListUiState = { transform -> _uiState.update(transform) },  //passing a lambda function that can be called by the controller(the controller passes the transform when it calls the function. It gets executed in the viewmodel)
        insertItemToDb = { newItem -> insert(newItem) }
    )

    val listItemViewController = ListItemViewController(
        updateItemInDb = { existingItem -> update(existingItem) },
        deleteItemFromDb = { existingItem -> delete(existingItem) }
    )

    init{
        viewModelScope.launch {
            Log.d("launched", "launched")
            getAllShoppingItems().collect {
                items -> _uiState.update { current ->
                Log.d("OldListRef", "Hash: ${current.shoppingListItemsState.shoppingListItems.hashCode()}")
                Log.d("NewListRef", "Hash: ${items.hashCode()}")
                current.copy(
                    shoppingListItemsState = current.shoppingListItemsState.copy(
                        shoppingListItems = items
                    )
                ) }
            }
        }
    }

    fun insert(item: ShoppingListItem) = viewModelScope.launch(Dispatchers.IO) { //viewModelScope is a lifecycle-aware coroutine scope
        shoppingListRepository.insert(item)
    }

    fun update(item: ShoppingListItem) = viewModelScope.launch(Dispatchers.IO){
        shoppingListRepository.update(item)
    }

    fun delete(item: ShoppingListItem) = viewModelScope.launch(Dispatchers.IO) {
        shoppingListRepository.delete(item)
    }

    //no coroutine needed, read operation
    fun getAllShoppingItems() = shoppingListRepository.getAllShoppingItems()
}

package com.example.shoppinglist.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ShoppingListViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ShoppingListUiState())
    val uiState: StateFlow<ShoppingListUiState> = _uiState.asStateFlow()

    val newItemDialogController = NewItemDialogController{transform ->
        _uiState.update(transform)  //passing a lambda function that can be called by the controller(the controller passes the transform when it calls the function. It gets executed in the viewmodel)
    }

    val listItemViewController = ListItemViewController{transform ->
        _uiState.update(transform)
    }
}

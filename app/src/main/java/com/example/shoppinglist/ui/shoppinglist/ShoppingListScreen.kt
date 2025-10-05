package com.example.shoppinglist.ui.shoppinglist

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shoppinglist.R
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import com.example.shoppinglist.viewmodel.ShoppingListViewModel

@Composable
fun ShoppingListScreen(
    modifier: Modifier = Modifier,
    shoppingListViewModel: ShoppingListViewModel = viewModel()
){
    val shoppingListUiState by shoppingListViewModel.uiState.collectAsState()
    var shopListItems by remember {mutableStateOf(listOf<ShoppingListItem>())}


//    @Composable
//    fun ShoppingListItem(
//        item: ShoppingItem,
//        isEditing: Boolean,
//        onEditToggle: () -> Unit,
//        onItemChange: (ShoppingItem) -> Unit
//    ) {
//        if (isEditing) {
//            ShoppingItemEditor(item, onItemChange = onItemChange, onDone = onEditToggle)
//        } else {
//            ShoppingItemDisplay(item, onEditClick = onEditToggle)
//        }
//    }
//
//    @Composable
//    fun ShoppingList(items: List<ShoppingItem>) {
//        LazyColumn {
//            items(items) { item ->
//                ShoppingListItem(
//                    item = item,
//                    isEditing = item.isEditing,
//                    onEditToggle = { /* toggle logic */ },
//                    onItemChange = { /* update logic */ }
//                )
//            }
//        }
//    }


    Column(
        modifier = modifier //innerPadding from Scaffold accounts for system insets automatically
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CustomCentralButton(
                text = stringResource(R.string.btn_add_items),
                onClick = { shoppingListViewModel.newItemDialogController.updateDialogDisplayed(true) })

        }
        Spacer(modifier = Modifier.height(16.dp))

        ShoppingList(
            items = shoppingListUiState.shoppingListItemsState.shoppingListItems
        )
//                    listItem -> //if left as 'it' without that line, it shadows the implicit params of the inner lambda
//                    if(listItem.isCurrentlyEdited){
//                        ShoppingListItemEditor(
//                            currContextP = LocalContext.current,
//                            itemP = listItem,
//                            onEditComplete = {
//                            editedItemName, editedItemQuantity ->
//                            shopListItems = shopListItems.map { it.copy(isCurrentlyEdited = false) }
//                            val editedItem = shopListItems.find { it.id == listItem.id }
//                            editedItem?.let{
//                                it.name = editedItemName
//                                it.quantity = editedItemQuantity
//                            }
//                        })
//                    }
//                    else{
//                        ShoppingListItemView(itemP = listItem, onEditIconClickP = {
//                            shopListItems = shopListItems.map{ it.copy(isCurrentlyEdited = (it.id == listItem.id))}
//                        }, onDeleteIconClickP = {
//                            shopListItems = shopListItems - listItem
////                            Toast.makeText(
////                                LocalContext.current,
////                                "${listItem.name} deleted from the list",
////                                Toast.LENGTH_SHORT
////                            ).show()
//
//                        })
//                    }
//                }


//                                        if(!alreadyExistingItem){
//                                            shopListItems = shopListItems + ShoppingListItem(
//                                            id = (shopListItems.maxOfOrNull { it.id } ?: 0) + 1,
//                                            name = alertDItemName,
//                                            quantity = alertDItemQty)
//                                            Log.i("Item addition", "New Item added to List")
//                                            //[ALTERNATIVE]
////                                            shopListItems.add(
////                                                ShoppingListItem(
////                                                    id = (shopListItems.maxOfOrNull { it.id } ?: 0) + 1,
////                                                    name = alertDItemName,
////                                                    quantity = alertDItemQty))
//                                            Toast.makeText(
//                                                LocalContext.current,
//                                                "$alertDItemName with a quantity of $alertDItemQty added to the shopping list",
//                                                Toast.LENGTH_LONG
//                                            ).show()

        }
        if(shoppingListUiState.newItemDialogState.isDialogDisplayed){
            NewItemDialog(
                dialogState = shoppingListUiState.newItemDialogState,
                dialogController = shoppingListViewModel.newItemDialogController
            )
        }

    }
//}


@Composable
fun ShoppingList(
    modifier: Modifier = Modifier,
    items: List<ShoppingListItem>,
){
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
    ) {
        items(items = items) { listItem -> //if left as 'it' without that line, it shadows the implicit params of the inner lambda
            ShoppingListItem(listItem = listItem)
        }
    }
}

@Composable
fun ShoppingListItem(
    modifier: Modifier = Modifier,
    listItem: ShoppingListItem
){
    if (listItem.isCurrentlyEdited) {
        ShoppingListItemEditor(
            currContext = LocalContext.current,
            item = listItem)
//            onEditComplete = { })
////        editedItemName, editedItemQuantity ->
////                shopListItems = shopListItems.map { it.copy(isCurrentlyEdited = false) }
////                val editedItem = shopListItems.find { it.id == listItem.id }
////                editedItem?.let {
////                    it.name = editedItemName
////                    it.quantity = editedItemQuantity
////                }
////            })
      } else {
         ShoppingListItemView(item = listItem, onEditIconClick = {}, onDeleteIconClick = {})
////            shopListItems =
////                shopListItems.map { it.copy(isCurrentlyEdited = (it.id == listItem.id)) }
////        }, onDeleteIconClickP = {
////            shopListItems = shopListItems - listItem
//////                            Toast.makeText(
//////                                LocalContext.current,
//////                                "${listItem.name} deleted from the list",
//////                                Toast.LENGTH_SHORT
//////                            ).show()
//
////        })
    }
}


@Composable
fun NewItemDialog(
    modifier: Modifier = Modifier,
    dialogState : NewItemDialogState,
    dialogController: NewItemDialogController
){
    Dialog(
        onDismissRequest = { dialogController.updateDialogDisplayed(false) }) {
        Surface(
            modifier = Modifier
                .wrapContentSize()  //making sure the Surface wraps its content unless explicitly overriden by params modifier
                .then(modifier),
            shape = RoundedCornerShape(4.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 4.dp
        ) {
            val mediumPadding = dimensionResource(R.dimen.padding_medium)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(mediumPadding),
                verticalArrangement = Arrangement.spacedBy(mediumPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = dialogController.dialogItemName,
                    onValueChange = { dialogController.updateDialogItemName(it)},
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = {
                        when{
                            dialogState.isItemNameEmpty -> Text(text = stringResource(R.string.dialog_lbl_name_empty))
                            dialogState.isItemAlreadyOnTheList -> Text(text = stringResource(R.string.dialog_lbl_name_on_list))
                            else -> Text(text = stringResource(R.string.dialog_lbl_name))
                        }
                    },
                    isError = dialogState.isItemNameInputInvalid,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    )
                )
                OutlinedTextField(
                    value = dialogController.dialogItemQty,
                    onValueChange = { dialogController.updateDialogItemQty(it)},
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = {
                        when {
                            dialogState.isItemQtyEmpty -> Text(text = stringResource(R.string.dialog_lbl_qty_empty))
                            else -> Text(text = stringResource(R.string.dialog_lbl_qty))
                        }
                    },
                    isError = dialogState.isItemQtyInputInvalid,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomAlertButton(
                        text = stringResource(R.string.dialog_btn_add_item),
                        onClick = { dialogController.checkFilledValues() },
                        shape = RectangleShape
                    )
                    CustomAlertButton(
                        text = stringResource(R.string.dialog_btn_close),
                        onClick = { dialogController.updateDialogDisplayed(false) },
                        shape = RectangleShape
                    )
                }
            }
        }
    }
}

@Composable
fun ShoppingListItemView(
    item: ShoppingListItem,
    onEditIconClick: () -> Unit,
    onDeleteIconClick: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                border = BorderStroke(2.dp, Color.DarkGray),
                shape = RoundedCornerShape(20)),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = item.name, modifier = Modifier.padding(8.dp))
        Text(text = "Qty: ${item.quantity}", modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.padding(8.dp)){
            IconButton(onClick = onEditIconClick)  {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = onDeleteIconClick)  {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Composable
fun ShoppingListItemEditor(
    currContext: Context,
    item: ShoppingListItem,
//    onEditComplete: (String, String) -> Unit
){
    var editedName by remember { mutableStateOf(item.name) }
    var editedQuantity by remember { mutableStateOf(item.quantity) }
    var isCurrentlyEdited by remember { mutableStateOf(item.isCurrentlyEdited) }

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly){
            Column {
                BasicTextField(
                    value = editedName,
                    onValueChange = {editedName = it},
                    singleLine = true,
                    modifier = Modifier.wrapContentSize().padding(8.dp)
                )

                BasicTextField(
                    value = editedQuantity,
                    onValueChange = {
                        editedQuantity = if((it.toIntOrNull() ?: 0) == 0) "" else it},
                    singleLine = true,
                    modifier = Modifier.wrapContentSize().padding(8.dp)
                )
            }

            CustomCentralButton(
                text = "Save",
                onClick = {
                    if(editedName.isBlank()) {
                        Toast.makeText(
                            currContext,
                            "Fill the item's name",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else if(editedQuantity.isBlank()) {
                        Toast.makeText(
                            currContext,
                            "Fill the item's quantity",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else{
                        isCurrentlyEdited = false
//                        onEditComplete(editedName, editedQuantity)
                    }
                })

        }
}


@Preview(showBackground = true)
@Composable
fun ShoppingLisScreenPreview(){
    ShoppingListTheme {
        ShoppingListScreen()
    }
}

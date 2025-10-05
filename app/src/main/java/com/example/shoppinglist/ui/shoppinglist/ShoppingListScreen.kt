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


    Column(
        modifier = modifier //innerPadding from Scaffold accounts for system insets automatically
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CustomCentralButton(
                text = stringResource(R.string.btn_add_items),
                onClick = { shoppingListViewModel.updateDialogDisplayed(true) })

        }
        Spacer(modifier = Modifier.height(16.dp))
        Box{
            LazyColumn (modifier = Modifier
                .padding(16.dp)) {
                items(items = shopListItems){
                    listItem -> //if left as 'it' without that line, it shadows the implicit params of the inner lambda
                    if(listItem.isCurrentlyEdited){
                        ShoppingListItemEditor(
                            currContextP = LocalContext.current,
                            itemP = listItem,
                            onEditComplete = {
                            editedItemName, editedItemQuantity ->
                            shopListItems = shopListItems.map { it.copy(isCurrentlyEdited = false) }
                            val editedItem = shopListItems.find { it.id == listItem.id }
                            editedItem?.let{
                                it.name = editedItemName
                                it.quantity = editedItemQuantity
                            }
                        })
                    }
                    else{
                        ShoppingListItemView(itemP = listItem, onEditIconClickP = {
                            shopListItems = shopListItems.map{ it.copy(isCurrentlyEdited = (it.id == listItem.id))}
                        }, onDeleteIconClickP = {
                            shopListItems = shopListItems - listItem
//                            Toast.makeText(
//                                LocalContext.current,
//                                "${listItem.name} deleted from the list",
//                                Toast.LENGTH_SHORT
//                            ).show()

                        })
                    }
                }
            }

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
//                                            alertDItemName = ""
//                                            alertDItemQty = ""
//                                        }
//                                    }
//                                },

//                        }
//                    },
            }
        }
        if(shoppingListUiState.isDialogDisplayed){
            NewItemDialog(
                itemName = shoppingListViewModel.dialogItemName,
                itemQty = shoppingListViewModel.dialogItemQty,
                isItemNameInputWrong = shoppingListUiState.isItemNameInputInvalid,
                isItemQtyInputWrong = shoppingListUiState.isItemQtyInputInvalid,
                isItemNameEmpty = shoppingListUiState.isItemNameEmpty,
                isItemAlreadyOnTheList = shoppingListUiState.isItemAlreadyOnTheList,
                isItemQtyEmpty = shoppingListUiState.isItemQtyEmpty,
                onItemNameChanged = { shoppingListViewModel.updateDialogItemName(it) },
                onItemQtyChanged = { shoppingListViewModel.updateDialogItemQty(it) },
                onAddClick = {shoppingListViewModel.checkFilledValues()},
                onCloseClick = { shoppingListViewModel.updateDialogDisplayed(false)},
                onDismissRequest = { shoppingListViewModel.updateDialogDisplayed(false) }
            )
        }
    }
//}


@Composable
fun NewItemDialog(
    modifier: Modifier = Modifier,
    itemName: String,
    itemQty: String,
    isItemNameInputWrong: Boolean,
    isItemQtyInputWrong: Boolean,
    isItemNameEmpty: Boolean,
    isItemAlreadyOnTheList: Boolean,
    isItemQtyEmpty: Boolean,
    onItemNameChanged: (String) -> Unit,
    onItemQtyChanged: (String) -> Unit,
    onAddClick : () -> Unit,
    onCloseClick: () -> Unit,
    onDismissRequest: () -> Unit,
){
    Dialog(
        onDismissRequest = onDismissRequest) {
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
                    value = itemName,
                    onValueChange = onItemNameChanged,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = {
                        when{
                            isItemNameEmpty -> Text(text = stringResource(R.string.dialog_lbl_name_empty))
                            isItemAlreadyOnTheList -> Text(text = stringResource(R.string.dialog_lbl_name_on_list))
                            else -> Text(text = stringResource(R.string.dialog_lbl_name))
                        }
                    },
                    isError = isItemNameInputWrong,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    )
                )
                OutlinedTextField(
                    value = itemQty,
                    onValueChange = onItemQtyChanged,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = {
                        when {
                            isItemQtyEmpty -> Text(text = stringResource(R.string.dialog_lbl_qty_empty))
                            else -> Text(text = stringResource(R.string.dialog_lbl_qty))
                        }
                    },
                    isError = isItemQtyInputWrong,
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
                        onClick = onAddClick,
                        shape = RectangleShape
                    )
                    CustomAlertButton(
                        text = stringResource(R.string.dialog_btn_close),
                        onClick = onCloseClick,
                        shape = RectangleShape
                    )
                }
            }
        }
    }
}

@Composable
fun ShoppingListItemView(
    itemP: ShoppingListItem,
    onEditIconClickP: () -> Unit,
    onDeleteIconClickP: () -> Unit
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
        Text(text = itemP.name, modifier = Modifier.padding(8.dp))
        Text(text = "Qty: ${itemP.quantity}", modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.padding(8.dp)){
            IconButton(onClick = onEditIconClickP)  {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = onDeleteIconClickP)  {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Composable
fun ShoppingListItemEditor(
    currContextP: Context,
    itemP: ShoppingListItem,
    onEditComplete: (String, String) -> Unit){
    var editedName by remember { mutableStateOf(itemP.name) }
    var editedQuantity by remember { mutableStateOf(itemP.quantity) }
    var isCurrentlyEdited by remember { mutableStateOf(itemP.isCurrentlyEdited) }

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
                            currContextP,
                            "Fill the item's name",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else if(editedQuantity.isBlank()) {
                        Toast.makeText(
                            currContextP,
                            "Fill the item's quantity",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else{
                        isCurrentlyEdited = false
                        onEditComplete(editedName, editedQuantity)
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

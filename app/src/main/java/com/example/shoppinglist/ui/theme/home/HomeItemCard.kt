package com.example.shoppinglist.ui.theme.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import com.example.shoppinglist.ui.theme.shared.CustomAlertButton
import com.example.shoppinglist.ui.theme.shared.CustomCentralButton

@Composable
fun ShoppingListView(modifier: Modifier = Modifier){
    val CURRENT_CONTEXT = LocalContext.current
    var shopListItems by remember {mutableStateOf(listOf<ShoppingListItem>())}
    //[ALTERNATIVE] val shopListItems = remember { mutableStateListOf<ShoppingListItem>() }
    var shouldAlertDBeDisplayed by remember { mutableStateOf(false) }
    var alertDItemName by remember { mutableStateOf("")}
    var alertDItemQty by remember { mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .safeDrawingPadding()
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box{
                CustomCentralButton(
                    textP = "Add items",
                    onClickP = {shouldAlertDBeDisplayed = true})
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Box{
            LazyColumn (modifier = Modifier
                .padding(16.dp)) {
                items(items = shopListItems){
                    listItem -> //if left as 'it' without that line, it shadows the implicit params of the inner lambda
                    if(listItem.isCurrentlyEdited){
                        ShoppingListItemEditor(
                            currContextP = CURRENT_CONTEXT,
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
                            Toast.makeText(
                                CURRENT_CONTEXT,
                                "${listItem.name} deleted from the list",
                                Toast.LENGTH_SHORT
                            ).show()

                        })
                    }
                }
            }
            if(shouldAlertDBeDisplayed){
                AlertDialog(
                    onDismissRequest = { shouldAlertDBeDisplayed = false },
                    confirmButton = {
                        Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            CustomAlertButton(
                                textP = "Add",
                                onClickP = {
                                    if (alertDItemName.isBlank()) {
                                        Toast.makeText(
                                            CURRENT_CONTEXT,
                                            "Fill the item's name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    else if(alertDItemQty.isBlank()){
                                        Toast.makeText(
                                            CURRENT_CONTEXT,
                                            "Fill the item's quantity",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    else {
                                        var alreadyExistingItem = false
                                        shopListItems.forEach(){
                                            if(it.name == alertDItemName){
                                                alreadyExistingItem = true
                                                Toast.makeText(
                                                    CURRENT_CONTEXT,
                                                    "$alertDItemName already on the list, just edit the quantity",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                alertDItemName = ""
                                                alertDItemQty = ""
                                            }
                                        }
                                        if(!alreadyExistingItem){
                                            shopListItems = shopListItems + ShoppingListItem(
                                            id = (shopListItems.maxOfOrNull { it.id } ?: 0) + 1,
                                            name = alertDItemName,
                                            quantity = alertDItemQty)
                                            Log.i("Item addition", "New Item added to List")
                                            //[ALTERNATIVE]
//                                            shopListItems.add(
//                                                ShoppingListItem(
//                                                    id = (shopListItems.maxOfOrNull { it.id } ?: 0) + 1,
//                                                    name = alertDItemName,
//                                                    quantity = alertDItemQty))
                                            Toast.makeText(
                                                CURRENT_CONTEXT,
                                                "$alertDItemName with a quantity of $alertDItemQty added to the shopping list",
                                                Toast.LENGTH_LONG
                                            ).show()
                                            alertDItemName = ""
                                            alertDItemQty = ""
                                        }
                                    }
                                },
                                shapeP = RectangleShape)
                            CustomAlertButton(
                                textP = "Cancel",
                                onClickP = { shouldAlertDBeDisplayed = false },
                                shapeP = RectangleShape)
                        }
                    },
                    title = { Text("Add Item") },
                    text = { Column{
                        OutlinedTextField(
                            value = alertDItemName,
                            onValueChange = { alertDItemName = it },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 8.dp),
                            label = { Text("Name") })

                        OutlinedTextField(
                            value = alertDItemQty,
                            onValueChange = {
                                //characters or 0 not allowed. A completely empty value (user presses backspace key) will also give 0, so again alertDItemQty = ""
                                alertDItemQty = if((it.toIntOrNull() ?: 0) == 0) "" else it
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 8.dp))
                    } }
                )
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
                textP = "Save",
                onClickP = {
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
fun ShoppingListViewPreview(){
    ShoppingListTheme {
        ShoppingListView()
    }
}

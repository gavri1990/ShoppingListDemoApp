package com.example.shoppinglist.ui.theme.home

import android.graphics.drawable.Icon
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import com.example.shoppinglist.ui.theme.shared.CustomAlertButton
import com.example.shoppinglist.ui.theme.shared.CustomCentralButton

@Composable
fun ShoppingListView(modifier: Modifier = Modifier){
    val CURRENT_CONTEXT = LocalContext.current
    val shopListItems = remember { mutableStateListOf<ShoppingListItem>() }
    //[ALTERNATIVE] var shopListItems by remember {mutableStateOf(listOf<ShoppingListItem>())}
    var shouldDialogBeDisplayed by remember { mutableStateOf(false) }
    var alertDItemName by remember { mutableStateOf("")}
    var alertDItemQty by remember { mutableIntStateOf(0)}

    Column(
        modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box{
                CustomCentralButton(
                    textP = "Add items",
                    onClickP = {shouldDialogBeDisplayed = true},
                    modifierP = Modifier.safeDrawingPadding())
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Box{
            LazyColumn (modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                items(items = shopListItems){
                    ShoppingListItemView(it, {}, {})
                }
            }
            if(shouldDialogBeDisplayed)
                AlertDialog(
                    onDismissRequest = { shouldDialogBeDisplayed = false },
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
                                    } else if (alertDItemQty == 0) {
                                        Toast.makeText(
                                            CURRENT_CONTEXT,
                                            "Quantity must be 1 or more",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        var alreadyExistingItem = false
                                        shopListItems.forEach{
                                            if(it.name == alertDItemName){
                                                it.quantity += alertDItemQty
                                                alreadyExistingItem = true
                                                Toast.makeText(
                                                    CURRENT_CONTEXT,
                                                    "${it.quantity} more ${it.name} added!",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                alertDItemName = ""
                                                alertDItemQty = 0
                                            }
                                        }
                                        if(!alreadyExistingItem){
                                            shopListItems.add(
                                                ShoppingListItem(
                                                    id = (shopListItems.maxOfOrNull { it.id } ?: 0) + 1,
                                                    name = alertDItemName,
                                                    quantity = alertDItemQty))
                                            Log.i("Item addition", "New Item added to List")
                                            //[ALTERNATIVE]
                                            //shopListItems = shopListItems + ShoppingListItem(
                                                //id = (shopListItems.maxOfOrNull { it.id } ?: 0) + 1,
                                                //name = alertDItemName,
                                                //quantity = alertDItemQty))
                                            Toast.makeText(
                                                CURRENT_CONTEXT,
                                                "$alertDItemName with a quantity of $alertDItemQty added to the shopping list",
                                                Toast.LENGTH_LONG
                                            ).show()
                                            alertDItemName = ""
                                            alertDItemQty = 0
                                        }
                                    }
                                },
                                shapeP = RectangleShape)

                            CustomAlertButton(
                                textP = "Cancel",
                                onClickP = { shouldDialogBeDisplayed = false },
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
                            value = alertDItemQty.toString(),
                            onValueChange = { alertDItemQty = it.toIntOrNull() ?: 0 },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 8.dp))
                    } }
                )
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
                shape = RoundedCornerShape(20))
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


@Preview(showBackground = true)
@Composable
fun ShoppingListViewPreview(){
    ShoppingListTheme {
        ShoppingListView()
    }
}

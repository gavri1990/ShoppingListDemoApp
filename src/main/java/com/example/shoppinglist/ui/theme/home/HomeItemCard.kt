package com.example.shoppinglist.ui.theme.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import com.example.shoppinglist.ui.theme.shared.CustomButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingList(modifier: Modifier = Modifier){
    val shopListItems by remember { mutableStateOf(listOf<ShoppingListItem>()) }
    var shouldDialogBeDisplayed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box{
                CustomButton(
                    textP = "Edit items",
                    onClickP = {shouldDialogBeDisplayed = true},
                    modifierP = Modifier.safeDrawingPadding())
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box{
                LazyColumn (modifier = Modifier.padding(16.dp)) {
                    items(shopListItems){
                    }
                }
                if(shouldDialogBeDisplayed)
                    BasicAlertDialog(
                        onDismissRequest = { shouldDialogBeDisplayed = false }
                    ){
                        Text("Alert Dialog text")
                    }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShoppingListPreview(){
    ShoppingListTheme {
        ShoppingList()
    }
}

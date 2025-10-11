package com.example.shoppinglist.ui.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shoppinglist.R
import com.example.shoppinglist.model.ShoppingListItem
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import com.example.shoppinglist.viewmodel.ListItemViewController
import com.example.shoppinglist.viewmodel.NewItemDialogController
import com.example.shoppinglist.viewmodel.NewItemDialogState
import com.example.shoppinglist.viewmodel.ShoppingListViewModel


@Composable
fun ShoppingListScreen(
    modifier: Modifier = Modifier,
    shoppingListViewModel: ShoppingListViewModel = viewModel()
){
    val shoppingListUiState by shoppingListViewModel.uiState.collectAsState()

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
            listItemViewController = shoppingListViewModel.listItemViewController,
            items = shoppingListUiState.shoppingListItemsState.shoppingListItems
        )
    }
    if(shoppingListUiState.newItemDialogState.isDialogDisplayed){
        NewItemDialog(
            dialogState = shoppingListUiState.newItemDialogState,
            dialogController = shoppingListViewModel.newItemDialogController
        )
    }
}


@Composable
fun ShoppingList(
    modifier: Modifier = Modifier,
    listItemViewController: ListItemViewController,
    items: List<ShoppingListItem>,
){
    val smallPadding = dimensionResource(R.dimen.padding_small)
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .then(modifier),
        verticalArrangement = Arrangement.spacedBy(smallPadding)
    ) {
        items(items = items) { listItem -> //if left as 'it' without that line, it shadows the implicit params of the inner lambda
            ShoppingListItem(
                listItemViewController = listItemViewController,
                item = listItem)
        }
    }
}

@Composable
fun ShoppingListItem(
    listItemViewController: ListItemViewController,
    item: ShoppingListItem
){
    Card(
        shape = RectangleShape,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 6.dp,
            pressedElevation = 6.dp,
            focusedElevation = 6.dp,
            hoveredElevation = 6.dp,
            disabledElevation = 6.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = item.name,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f) //This composable will take only the remaining space after fixed-size elements are placed.
            )
            IconButton(
                onClick = { listItemViewController.decreaseItemQty(item) })  {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_subtract_24),
                    contentDescription = stringResource(R.string.item_view_desc_icon_subtract))
            }
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(8.dp)
                    .wrapContentSize()
            ){
                Text(
                    text = item.quantity,
                    fontSize = 14.sp,
                    lineHeight = 14.sp
                )
            }
            IconButton(
                onClick = { listItemViewController.increaseItemQty(item) })  {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.item_view_desc_icon_add))
            }
            IconButton(
                onClick = { listItemViewController.removeItem(item) })  {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.item_view_desc_icon_delete))
            }

        }
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


@Preview(showBackground = true)
@Composable
fun ShoppingLisScreenPreview(){
    ShoppingListTheme {
        ShoppingListScreen()
    }
}

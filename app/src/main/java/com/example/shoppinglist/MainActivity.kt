package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import com.example.shoppinglist.ui.shoppinglist.ShoppingListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint  //Hilt hooks into the Android component lifecycle to inject dependencies
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShoppingListScreen(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding))
                }
            }
        }
    }
}


package com.example.shoppinglist.ui.shoppinglist

import androidx.compose.ui.graphics.Shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomAlertButton(
    text: String,
    onClick: () -> Unit,
    shape: Shape = ButtonDefaults.shape,
    modifier: Modifier = Modifier,
    contentColor: Color = Color.White,
    textColor : Color = Color.White,
    textFontSize : TextUnit = 12.sp,
    textFontFamily: FontFamily = FontFamily.Monospace
){
    Button(onClick = onClick,
        shape = shape,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(contentColor = contentColor)) {
        Text(text = text, color = textColor, fontSize = textFontSize, fontFamily = textFontFamily)
    }
}
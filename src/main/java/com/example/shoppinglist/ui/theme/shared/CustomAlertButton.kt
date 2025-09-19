package com.example.shoppinglist.ui.theme.shared

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
    textP: String,
    onClickP: () -> Unit,
    shapeP: Shape = ButtonDefaults.shape,
    modifierP: Modifier = Modifier,
    contentColorP: Color = Color.White,
    textColorP : Color = Color.White,
    textFontSizeP : TextUnit = 12.sp,
    textFontFamilyP: FontFamily = FontFamily.Monospace
){
    Button(onClick = onClickP,
        shape = shapeP,
        modifier = modifierP,
        colors = ButtonDefaults.buttonColors(contentColor = contentColorP)) {
        Text(text = textP, color = textColorP, fontSize = textFontSizeP, fontFamily = textFontFamilyP)
    }
}
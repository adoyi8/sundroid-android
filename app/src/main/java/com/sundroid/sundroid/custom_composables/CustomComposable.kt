package com.sundroid.sundroid.custom_composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sundroid.sundroid.ui.theme.Tilt










@Composable
fun SplashScreenText(text: String, visibility: Boolean){
    val color = MaterialTheme.colorScheme.primary

    val modifier = Modifier

    val textFontSize = 50.sp
    AnimatedVisibility(visible = visibility) {

        Text(text = text, modifier = modifier, color = color, fontSize = textFontSize, fontFamily = Tilt, fontWeight = FontWeight.Bold)

    }
}

@Composable
fun SundroidButton(){

}

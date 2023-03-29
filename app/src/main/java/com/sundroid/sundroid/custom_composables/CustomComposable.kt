package com.sundroid.sundroid.custom_composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sundroid.sundroid.R
import com.sundroid.sundroid.ui.theme.Tilt
import java.text.DateFormat
import java.util.*


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
fun SundroidButton(onClick: ()->Unit, text: String){
 Button(onClick = onClick, shape = MaterialTheme.shapes.small) {
     Text(text = text, style = MaterialTheme.typography.labelSmall)
 }
}
@Composable
fun SundroidTextHeader(text: String){

    Text(text = text, style = MaterialTheme.typography.headlineLarge)
}
@Composable
fun SundroidText(text: String){
    val tilt = FontFamily(
        Font(R.font.tilt_neon),
        Font(R.font.tilt_warp, FontWeight.Bold),
        Font(R.font.tilt_warp, FontWeight.Bold, FontStyle.Italic),
    )
    val kanit = FontFamily(
        Font(R.font.kanit_extrabold),
        Font(R.font.kanit_bold),
        Font(R.font.kanit_medium),
    )
    Text(text = text, style = TextStyle(fontSize = 18.sp, fontFamily = tilt))
}
@Composable
fun SundroidTextAmount(text: String){
    val tilt = FontFamily(
        Font(R.font.tilt_neon),
        Font(R.font.tilt_warp, FontWeight.Bold),
        Font(R.font.tilt_warp, FontWeight.Bold, FontStyle.Italic),
    )
    val kanit = FontFamily(
        Font(R.font.kanit_extrabold),
        Font(R.font.kanit_bold),
        Font(R.font.kanit_medium),
    )
    Text(text = formatCurrency(text), style = TextStyle(fontSize = 15.sp, fontFamily = tilt, fontWeight = FontWeight.Bold), modifier = Modifier.width(200.dp))
}
@Composable
fun SundroidTextKanitBold(text: String){
    val tilt = FontFamily(
        Font(R.font.tilt_neon),
        Font(R.font.tilt_warp, FontWeight.Bold),
        Font(R.font.tilt_warp, FontWeight.Bold, FontStyle.Italic),
    )
    val kanit = FontFamily(
        Font(R.font.kanit_extrabold),
        Font(R.font.kanit_bold),
        Font(R.font.kanit_medium),
    )
    Text(text = text, style = TextStyle(fontSize = 20.sp, fontFamily = kanit))

}
@Composable
fun DisplayLocalDate(modifier: Modifier) {
    val currentDate = Calendar.getInstance().time
    val dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault())

    val tilt = FontFamily(
        Font(R.font.tilt_neon),
        Font(R.font.tilt_warp, FontWeight.Bold),
        Font(R.font.tilt_warp, FontWeight.Bold, FontStyle.Italic),
    )
    val kanit = FontFamily(
        Font(R.font.kanit_extrabold),
        Font(R.font.kanit_bold),
        Font(R.font.kanit_medium),
    )
    Text(text =  dateFormat.format(currentDate) , style = TextStyle(fontSize = 13.sp, fontFamily = tilt))

}
fun formatCurrency(amount: String): String{
    var amount = amount
    amount = try {
        "₦" + String.format("%,.0f", amount.toFloat())
    } catch (e: NumberFormatException) {
        e.printStackTrace()
        return amount
    }
    return amount
}

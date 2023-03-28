package com.sundroid.sundroid.custom_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun SundroidFAB(icon: Int, text: String, onclick: ()->Unit = {}) {
    FloatingActionButton(
        onClick = onclick,
    ) {













        Row(modifier = Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically ) {
            Image(painter = painterResource(id = icon), "Localized description")
            Text(text = text, style = MaterialTheme.typography.labelMedium)
        }

    }
}
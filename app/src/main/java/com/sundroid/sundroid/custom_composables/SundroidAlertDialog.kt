package com.sundroid.sundroid.custom_composables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SundroidAlertDialog(openDialog: MutableState<Boolean>){





    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                openDialog.value = false
            },
            icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
            title = {
                Text(text = "Log Out")
            },
            text = {
                Text(
                    "Do you wish to log out from the App?"
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    }, modifier = Modifier.size(100.dp)
                , shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Yes", style =MaterialTheme.typography.labelSmall)
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    },  modifier = Modifier.size(100.dp)
                ) {
                    Text("No")
                }
            }
        )
    }
}
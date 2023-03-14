package com.sundroid.sundroid.custom_composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState


@Composable
fun SundroidAlertDialog(openDialog: MutableState<Boolean>, onYesClick:()->Unit, onNoClick:()->Unit={}){





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

                SundroidButton(
                    onClick = onYesClick, text = "Yes"
                )
            },
            dismissButton = {
                SundroidButton(
                    onClick = {
                        openDialog.value = false
                        onNoClick
                    }, text = "No"
                )
            }
        )
    }
}
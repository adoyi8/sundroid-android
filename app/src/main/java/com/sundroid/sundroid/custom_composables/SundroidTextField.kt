package com.sundroid.sundroid.custom_composables


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sundroid.sundroid.viewmodel.SundroidViewModel










@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SundroidTextField(placeholder: String="", onChange:(String)->Unit={}, viewModel: SundroidViewModel, value: String, label: String){
    val modifier: Modifier = Modifier
    val leadingIcon: @Composable (() -> Unit)? = null
    val imeAction: ImeAction = ImeAction.Next
    val keyboardType: KeyboardType = KeyboardType.Text
    var keyBoardActions: KeyboardActions = KeyboardActions()
    val isEnabled: Boolean = true
    val focusManager = LocalFocusManager.current

    //Plain text keyboard
    keyBoardActions = KeyboardActions(
        onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }
    )
    OutlinedTextField(
        modifier = modifier.fillMaxWidth().padding(top = 10.dp),
        value = value,
        onValueChange =  onChange,
        leadingIcon = leadingIcon,
        label = {Text(text= label)},
        textStyle = TextStyle(fontSize = 18.sp),
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = keyBoardActions,
        enabled = isEnabled,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Gray,
            disabledTextColor = Color.Black
        ),
        placeholder = {
            Text(text = placeholder, style = TextStyle(fontSize = 18.sp, color = Color.LightGray))
        }
    )
}



@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SundroidTextArea(placeholder: String="", onChange:(String)->Unit={}, viewModel: SundroidViewModel, value: String, label: String){
    val modifier: Modifier = Modifier



    val leadingIcon: @Composable (() -> Unit)? = null
    val imeAction: ImeAction = ImeAction.Next
    val keyboardType: KeyboardType = KeyboardType.Text
    var keyBoardActions: KeyboardActions = KeyboardActions()
    val isEnabled: Boolean = true
    val focusManager = LocalFocusManager.current
    //Plain text keyboard
    keyBoardActions = KeyboardActions(
        onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }
    )

    OutlinedTextField(
        modifier = modifier.fillMaxWidth().padding(top = 10.dp).height(100.dp),
        value = value,
        onValueChange =  onChange,
        leadingIcon = leadingIcon,
        label = {Text(label)},
        textStyle = TextStyle(fontSize = 18.sp),
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = keyBoardActions,
        enabled = isEnabled,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Gray,
            disabledTextColor = Color.Black
        ),
        placeholder = {
            Text(text = placeholder, style = TextStyle(fontSize = 18.sp, color = Color.LightGray))
        }
    )
}










@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SundroidTextFieldAmount(placeholder: String="", onChange:(String)->Unit={}, viewModel: SundroidViewModel, value: Int, label: String ){
    val modifier: Modifier = Modifier
    val leadingIcon: @Composable (() -> Unit)? = null
    val imeAction: ImeAction = ImeAction.Next
    val keyboardType: KeyboardType = KeyboardType.Decimal
    var keyBoardActions: KeyboardActions = KeyboardActions()
    val isEnabled: Boolean = true
    val focusManager = LocalFocusManager.current
    //Plain text keyboard
    keyBoardActions = KeyboardActions(
        onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }
    )
    OutlinedTextField(
        modifier = modifier.fillMaxWidth().padding(top = 10.dp),
        value =  if(value>0.0) value.toString() else{""},
        onValueChange =  onChange,
        label = {Text(label)},
        leadingIcon = leadingIcon,
        textStyle = TextStyle(fontSize = 18.sp),
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = keyBoardActions,
        enabled = isEnabled,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Gray,
            disabledTextColor = Color.Black
        ),
        placeholder = {
            Text(text = placeholder, style = TextStyle(fontSize = 18.sp, color = Color.LightGray))
        }
    )
}
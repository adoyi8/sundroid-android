package com.sundroid.sundroid.practice

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sundroid.sundroid.R
import com.sundroid.sundroid.data.SignInButton
import com.sundroid.sundroid.models.User
import kotlinx.coroutines.launch

@Composable
fun PracticeScreen(
    authViewModel: PracticeViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    var text by remember { mutableStateOf<String?>(null) }
    val user by remember(authViewModel) { authViewModel.user }.collectAsState()
    val signInRequestCode = 1


    PracticeView(
        errorText = text,
        onClick = {
            coroutineScope.launch {

            }

        },
        user,
    )


}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeView(
    errorText: String?,
    onClick: () -> Unit, user: User?
) {


    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SignInButton(
                text = "Sign in with Google",
                loadingText = "Signing in...",

                icon = painterResource(id = R.drawable.ic_connection_error),
                onClick = {

                    onClick()
                }
            )
            Text("Name is "+ user?.displayName )

            errorText?.let {

                Spacer(modifier = Modifier.height(30.dp))
                Text(text = it)
            }
        }
    }
}
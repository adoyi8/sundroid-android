package com.sundroid.sundroid.data

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.common.api.ApiException
import com.sundroid.sundroid.R
import com.sundroid.sundroid.google_auth.AuthResultContract
import com.sundroid.sundroid.models.RoomUserEntity
import com.sundroid.sundroid.viewmodel.SundroidViewModel
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(navController: NavController, viewModel: SundroidViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    var text by remember { mutableStateOf<String?>(null) }

    val signInRequestCode = 1

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)

                if (account == null) {
                    text = "Google sign in failed"
                } else {
                    coroutineScope.launch {
                        account.email?.let {
                            account.displayName?.let { it1 ->
                                val user = RoomUserEntity(email = account.email, displayName = account.displayName, familyName = account.familyName, givenName = account.givenName,idToken= account.idToken,photoUrl = account.photoUrl.toString(),serverAuthCode= account.serverAuthCode)
                                viewModel.insertUser(user)
                                navController.navigate("dashboard_screen") {
                                    popUpTo("auth_screen") { inclusive = true }

                                }
                            }
                        }
                    }
                }
            } catch (e: ApiException) {
                text = "Google sign in failed ${e.message}"
            }
        }

    AuthView(
        errorText = text,
        onClick = {
            text = null
            authResultLauncher.launch(signInRequestCode)
        }
    )

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthView(
    errorText: String?,
    onClick: () -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SignInButton(
                text = "",
                loadingText = "Signing in...",
                isLoading = isLoading,
                icon = painterResource(id = R.drawable.google_sign_in),
                onClick = {
                    isLoading = true
                    onClick()
                }
            )

            errorText?.let {
                isLoading = false
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = it)
            }
        }
    }
}
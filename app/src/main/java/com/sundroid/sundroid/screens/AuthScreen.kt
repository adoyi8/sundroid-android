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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.common.api.ApiException
import com.sundroid.sundroid.R
import com.sundroid.sundroid.data.network.model.LoginModel
import com.sundroid.sundroid.google_auth.AuthResultContract
import com.sundroid.sundroid.google_auth.getGoogleSignInClient
import com.sundroid.sundroid.viewmodel.SundroidViewModel
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(
    navController: NavController, viewModel: SundroidViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    var text by remember { mutableStateOf<String?>(null) }

    val signInRequestCode = 1

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                println("Satire" + account)

                if (account == null) {
                    text = "Google sign in failed"
                } else {
                    coroutineScope.launch {
                        account.email?.let {
                            account.displayName?.let { it1 ->

                                //  val user = RoomUserEntity(email = account.email!!, displayName = account.displayName, familyName = account.familyName, givenName = account.givenName, accessToken =  account.idToken,photoUrl = account.photoUrl.toString(),serverAuthCode= account.serverAuthCode)
                                val loginModel = LoginModel(
                                    email = account.email!!,
                                    firstName = account.givenName,
                                    lastName = account.familyName,
                                    photoUrl = account.photoUrl.toString(),
                                    fullName = account.displayName
                                )
                                val result = viewModel.login(loginModel)
                                
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
        }, viewModel = viewModel, navController=navController
    )

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthView(
    errorText: String?,
    onClick: () -> Unit, viewModel: SundroidViewModel, navController: NavController
) {
    var isLoading by remember { mutableStateOf(false) }
    val logout = getGoogleSignInClient(LocalContext.current).signOut()
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
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
                getGoogleSignInClient(LocalContext.current).signOut()
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = it)
            }
            if(viewModel.loginResponse.value.isSuccessful){
                navController.navigate("dashboard_screen") {
                    popUpTo("auth_screen") { inclusive = true }
                }
            }

        }
    }
}
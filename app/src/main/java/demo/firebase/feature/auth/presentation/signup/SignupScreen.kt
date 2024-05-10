package demo.firebase.feature.auth.presentation.signup
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import demo.firebase.R
import demo.firebase.core.navigation.ROUTE_HOME
import demo.firebase.core.navigation.ROUTE_LOGIN
import demo.firebase.core.navigation.ROUTE_SIGNUP
import demo.firebase.core.util.Resource
import demo.firebase.feature.auth.presentation.AuthViewModel
import demo.firebase.feature.auth.presentation.component.AuthHeader
import demo.firebase.ui.theme.Demo_FirebaseTheme

@Composable
fun SignupScreen(viewModel: AuthViewModel?, navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val signupFlow = viewModel?.signupFlow?.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        AuthHeader()


       Column {
           TextField(
               value = name,
               onValueChange = {
                   name = it
               },
               label = {
                   Text(text = stringResource(id = R.string.name))
               },

               keyboardOptions = KeyboardOptions(
                   capitalization = KeyboardCapitalization.None,
                   autoCorrect = false,
                   keyboardType = KeyboardType.Email,
                   imeAction = ImeAction.Next
               )
           )

           Spacer(modifier = Modifier.height(50.dp))

           TextField(
               value = email,
               onValueChange = {
                   email = it
               },
               label = {
                   Text(text = stringResource(id = R.string.email))
               },

               keyboardOptions = KeyboardOptions(
                   capitalization = KeyboardCapitalization.None,
                   autoCorrect = false,
                   keyboardType = KeyboardType.Email,
                   imeAction = ImeAction.Next
               )
           )

           Spacer(modifier = Modifier.height(50.dp))

           TextField(
               value = password,
               onValueChange = {
                   password = it
               },
               label = {
                   Text(text = stringResource(id = R.string.password))
               },

               visualTransformation = PasswordVisualTransformation(),
               keyboardOptions = KeyboardOptions(
                   capitalization = KeyboardCapitalization.None,
                   autoCorrect = false,
                   keyboardType = KeyboardType.Password,
                   imeAction = ImeAction.Done
               )
           )

       }
        Button(
            onClick = {
                viewModel?.signup(name, email, password)
            },

        ) {
            Text(text = stringResource(id = R.string.signup), style = MaterialTheme.typography.titleMedium)
        }


        Text(
            modifier = Modifier

                .clickable {
                    navController.navigate(ROUTE_LOGIN) {
                        popUpTo(ROUTE_SIGNUP) { inclusive = true }
                    }
                },
            text = stringResource(id = R.string.already_have_account),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )

        signupFlow?.value?.let {
            when (it) {
                is Resource.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier)
                }
                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(ROUTE_HOME) {
                            popUpTo(ROUTE_SIGNUP) { inclusive = true }
                        }
                    }
                }

            }
        }

    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun SignupScreenPreviewLight() {
    Demo_FirebaseTheme {
        SignupScreen(null, rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignupScreenPreviewDark() {
    Demo_FirebaseTheme {
        SignupScreen(null, rememberNavController())
    }
}
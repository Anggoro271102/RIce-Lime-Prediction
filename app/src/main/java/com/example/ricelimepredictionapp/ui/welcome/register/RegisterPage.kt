package com.example.ricelimepredictionapp.ui.welcome.register

import androidx.compose.material.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.media3.common.util.Log
import androidx.navigation.NavController
import com.example.ricelimepredictionapp.R
import com.example.ricelimepredictionapp.ui.theme.JetPackComposeDemoTheme
import com.example.ricelimepredictionapp.ui.theme.colorPrimary
import com.example.ricelimepredictionapp.ui.theme.dark_gray
import com.example.ricelimepredictionapp.ui.theme.gray
import com.example.ricelimepredictionapp.ui.theme.light_gray
import com.example.ricelimepredictionapp.ui.view_model.LoginViewModel
import com.example.ricelimepredictionapp.ui.view_model.RegisterViewModel

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel) {
    var confirm = remember { mutableStateOf(TextFieldValue()) }
    var password = remember { mutableStateOf(TextFieldValue()) }
    var username = remember { mutableStateOf(TextFieldValue()) }
    var email = remember { mutableStateOf(TextFieldValue()) }


    JetPackComposeDemoTheme {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ConstraintLayout {
                    val (image, loginForm) = createRefs()
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillParentMaxHeight()
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                                bottom.linkTo(loginForm.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }) {
                        HeaderView()
                    }
                    Card(
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                            .constrainAs(loginForm) {
                                top.linkTo(anchor = image.bottom, margin = (-600).dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(30.dp)
                        ) {
                            val loginText = "Register your account."
                            val loginWord = "Register"
                            val loginAnnotatedString = buildAnnotatedString {
                                append(loginText)
                                addStyle(
                                    style = SpanStyle(
                                        color = dark_gray,
                                        fontFamily = FontFamily(Font(R.font.helvetica_neue_regular))
                                    ),
                                    start = 0,
                                    end = loginText.length
                                )
                                addStyle(
                                    style = SpanStyle(
                                        color = colorPrimary,
                                        fontFamily = FontFamily(Font(R.font.helvetica_neue_medium))
                                    ),
                                    start = 0,
                                    end = loginWord.length
                                )
                            }

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp, bottom = 20.dp),
                                text = loginAnnotatedString,
                                textAlign = TextAlign.Center,
                                fontSize = 22.sp,
                            )
                            Text(
                                text = "Username",
                                style = MaterialTheme.typography.labelMedium.copy(color = gray),
                                modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
                            )

                            CustomStyleTextField(
                                username.value,
                                "Username",
                                R.drawable.user,
                                KeyboardType.Text,
                                VisualTransformation.None
                            )

                            Text(
                                text = "Email Address",
                                style = MaterialTheme.typography.labelMedium.copy(color = gray),
                                modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
                            )

                            CustomStyleTextField(
                                email.value,
                                "Email Address",
                                R.drawable.email,
                                KeyboardType.Email,
                                VisualTransformation.None
                            )

                            Text(
                                text = "Password",
                                style = MaterialTheme.typography.labelMedium.copy(color = gray),
                                modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                            )
                            CustomStyleTextField(
                                confirm.value,
                                "Password",
                                R.drawable.password,
                                KeyboardType.Password,
                                PasswordVisualTransformation()
                            )
                            Text(
                                text = "Confirm Your Password",
                                style = MaterialTheme.typography.labelMedium.copy(color = gray),
                                modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                            )
                            CustomStyleTextField(
                                password.value,
                                "Confirm Your Password",
                                R.drawable.password,
                                KeyboardType.Password,
                                PasswordVisualTransformation()
                            )


                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                text = "Forgot Password",
                                textAlign = TextAlign.End,
                                style = MaterialTheme.typography.labelSmall.copy(color = colorPrimary)
                            )
                            val signInText = "Already have an account? Sign In"
                            val signInWord = "Sign In"
                            val signInAnnotatedString = buildAnnotatedString {
                                append(signInText)
                                addStyle(
                                    style = SpanStyle(
                                        color = light_gray,
                                        fontFamily = FontFamily(Font(R.font.helvetica_neue_regular))
                                    ),
                                    start = 0,
                                    end = signInText.length
                                )
                                addStyle(
                                    style = SpanStyle(
                                        color = colorPrimary,
                                        fontFamily = FontFamily(Font(R.font.helvetica_neue_medium))
                                    ),
                                    start = signInText.indexOf(signInWord),
                                    end = signInText.length
                                )
                            }

                            Button(
                                onClick = { viewModel.signup(username.value.text,email.value.text, password.value.text)
                                          print("${username.value.toString()}")
                                    android.util.Log.d("ANggoro", "${username.value}");
                                },
                                modifier = Modifier
                                    .padding(top = 20.dp, bottom = 60.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth()
                                    .background(color = Color.Transparent),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text(
                                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                                    text = "Register",
                                    color = Color.White,
                                    style = MaterialTheme.typography.headlineMedium
                                )
                            }
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { navController.navigate("login") },
                                text = signInAnnotatedString,
                                style = TextStyle(
                                    fontSize = 14.sp
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomStyleTextField(
    onValue: TextFieldValue,
    placeholder: String,
    leadingIconId: Int,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation,
    modifier: Modifier = Modifier.fillMaxWidth() // Added modifier parameter for additional customization
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    OutlinedTextField(
        value = textState.value,
        onValueChange = { valueChanged ->
            textState.value = valueChanged
        },
        modifier = modifier, // Use passed modifier instead of hardcoded one
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        placeholder = { Text(text = placeholder) },
        leadingIcon = {
            Image(
                painter = painterResource(id = leadingIconId),
                contentDescription = "custom_text_field",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(18.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color = Color.Transparent)

            )
        },
        shape = MaterialTheme.shapes.medium, // Use MaterialTheme shapes instead of hardcoded shape
        textStyle = MaterialTheme.typography.bodyLarge, // Use MaterialTheme typography for consistent style
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.Transparent,
            focusedLabelColor = Color.White,
            cursorColor = MaterialTheme.colorScheme.primary
        ),
        visualTransformation = visualTransformation
    )
}


@Composable
fun HeaderView() {
    Box (
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            bitmap = ImageBitmap.imageResource(id = R.drawable.rice_bg),
            contentScale = ContentScale.FillHeight,
            contentDescription = "header_view_login_bg"
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 550.dp)
    ) {
        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .background(Color.Transparent)
        ){
            Image(
                modifier = Modifier.wrapContentWidth(),
                bitmap = ImageBitmap.imageResource(id = R.drawable.logo_chalk),
                contentDescription = "header_view_flower_logo",
                contentScale = ContentScale.Fit
            )
        }

    }
}

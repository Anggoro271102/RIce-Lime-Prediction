package com.example.ricelimepredictionapp.ui.welcome.login


import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavHostController
import com.example.ricelimepredictionapp.R
import com.example.ricelimepredictionapp.ui.theme.JetPackComposeDemoTheme
import com.example.ricelimepredictionapp.ui.theme.colorPrimary
import com.example.ricelimepredictionapp.ui.theme.dark_gray
import com.example.ricelimepredictionapp.ui.theme.gray
import com.example.ricelimepredictionapp.ui.theme.light_gray

@Composable
fun LoginScreen(navController: NavHostController) {
    JetPackComposeDemoTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(color = Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ConstraintLayout(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .fillParentMaxHeight()
                        .fillParentMaxWidth()
                ) {
                    val (image, loginForm) = createRefs()
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }) {
                        HeaderView()
                    }
                    Card(
                        shape = RoundedCornerShape(40.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                            .background(color = Color.Transparent)
                            .constrainAs(loginForm) {
                                top.linkTo(anchor = image.top, margin = (500).dp)
                                bottom.linkTo(anchor = image.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(30.dp)
                        ) {

                            val loginText = "Log in to your account."
                            val loginWord = "Log in"
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
                                text = "Email Address",
                                style = MaterialTheme.typography.labelMedium.copy(color = gray),
                                modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
                            )

                            CustomStyleTextField(
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
                                "Password",
                                R.drawable.password,
                                KeyboardType.Password,
                                PasswordVisualTransformation()
                            )

                            Button(
                                onClick = {
                                          navController.navigate("dashboard")
                                },
                                modifier = Modifier
                                    .padding(top = 20.dp, bottom = 100.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth()
                                    .background(color = Color.Transparent),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text(
                                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                                    text = "Login",
                                    color = Color.White,
                                    style = MaterialTheme.typography.headlineMedium
                                )
                            }

                            val signInText = "Don't have an account? Sign Up"
                            val signInWord = "Sign Up"
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

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 48.dp)
                                    .clickable { navController.navigate("register") },
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


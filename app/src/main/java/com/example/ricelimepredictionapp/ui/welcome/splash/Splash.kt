package com.example.ricelimepredictionapp.ui.welcome.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.ricelimepredictionapp.R
import com.example.ricelimepredictionapp.ui.theme.Purple40
import com.example.ricelimepredictionapp.ui.theme.bgSplash
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        ), label = ""
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3000)
        navController.popBackStack()
        navController.navigate("get_started")
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Purple40)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        ConstraintLayout (modifier = Modifier.fillMaxSize()) {
         val (logo, bg) = createRefs()
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(bg) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .background(bgSplash)
            ){

            }
            Box (modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .constrainAs(logo){
                    top.linkTo(anchor = bg.top)
                    bottom.linkTo(bg.bottom)
                    end.linkTo(bg.end)
                    start.linkTo(bg.start)
                }, contentAlignment = Alignment.Center){
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .alpha(alpha),
                    bitmap = ImageBitmap.imageResource(id = R.drawable.logo_chalk),
                    contentScale = ContentScale.Fit,
                    contentDescription = "header_view_login_bg"
                )
            }
        }
//        Icon(
//            modifier = Modifier
//                .size(120.dp)
//                .alpha(alpha = alpha),
//            imageVector = Icons.Default.Email,
//            contentDescription = "Logo Icon",
//            tint = Color.White
//        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash(alpha = 1f)
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun SplashScreenDarkPreview() {
    Splash(alpha = 1f)
}
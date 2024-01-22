package com.example.ricelimepredictionapp.ui.welcome.get_started

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ricelimepredictionapp.R
import com.example.ricelimepredictionapp.ui.theme.Purple
import com.example.ricelimepredictionapp.ui.theme.Purple40
import com.example.ricelimepredictionapp.ui.theme.bgSplash

@Composable
fun GetStarted (navController: NavController){
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Purple40)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        ConstraintLayout (modifier = Modifier.fillMaxSize()) {
            val (logo, bg, button) = createRefs()
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(bg) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }, contentAlignment = Alignment.Center

            ){
                Image(
                    modifier = Modifier.wrapContentWidth(),
                    bitmap = ImageBitmap.imageResource(id = R.drawable.rice_bg),
                    contentDescription = "header_view_flower_logo",
                    contentScale = ContentScale.FillHeight
                )
            }
            Box (modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .constrainAs(logo) {
                    top.linkTo(anchor = bg.top)
                    bottom.linkTo(bg.bottom)
                    end.linkTo(bg.end)
                    start.linkTo(bg.start)
                }, contentAlignment = Alignment.Center){
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    bitmap = ImageBitmap.imageResource(id = R.drawable.logo_chalk),
                    contentScale = ContentScale.FillHeight,
                    contentDescription = "header_view_login_bg"
                )
            }
            Box(modifier = Modifier
                .width(265.dp)
                .height(55.dp)
                .constrainAs(button) {
                    end.linkTo(bg.end)
                    bottom.linkTo(anchor = bg.bottom, margin = 40.dp)
                    start.linkTo(bg.start)
                }, contentAlignment = Alignment.BottomCenter){
                Button(onClick = { navController.navigate("login") }, modifier = Modifier
                    .fillMaxSize(), shape = RoundedCornerShape(40.dp),  colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Get Started")
                    Spacer(modifier = Modifier.width(20.dp))
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "")
                }
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
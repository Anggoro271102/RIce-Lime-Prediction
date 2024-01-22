package com.example.ricelimepredictionapp.ui.dashboard


import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ricelimepredictionapp.R
import com.example.ricelimepredictionapp.ui.dashboard.camera.Camera
import com.example.ricelimepredictionapp.ui.dashboard.camera.ImageWithButtonsScreen
import com.example.ricelimepredictionapp.ui.dashboard.colorButtons.BellColorButton
import com.example.ricelimepredictionapp.ui.dashboard.colorButtons.ButtonBackground
import com.example.ricelimepredictionapp.ui.dashboard.colorButtons.ColorButton
import com.example.ricelimepredictionapp.ui.dashboard.colorButtons.ColorButtonAnimation
import com.example.ricelimepredictionapp.ui.dashboard.colorButtons.PlusColorButton
import com.example.ricelimepredictionapp.ui.dashboard.list.ListRice
import com.example.ricelimepredictionapp.ui.dashboard.profile.Profile
import com.example.ricelimepredictionapp.ui.theme.ElectricViolet
import com.example.ricelimepredictionapp.ui.view_model.PostViewModel
import com.example.ricelimepredictionapp.ui.welcome.login.LoginScreen
import com.example.ricelimepredictionapp.ui.welcome.register.RegisterScreen
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Straight
import com.exyte.animatednavbar.animation.indendshape.StraightIndent
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius


@Stable
data class Item(
    @DrawableRes val icon: Int,
    var isSelected: Boolean,
    val onClick: () -> Unit,
    @StringRes val description: Int,
    val animationType: ColorButtonAnimation = BellColorButton(
        tween(500),
        background = ButtonBackground(R.drawable.plus)
    ),
    val color: Color

    )


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard (modifier: Modifier = Modifier,
               navController: NavHostController = rememberNavController())
{

    val colorButtons = remember {
        listOf(
            Item(
                icon = R.drawable.outline_home,
                isSelected = true,
                description = R.string.Home,
                onClick = {
                    navController.navigate("list_data")
                },
                animationType = BellColorButton(
                    animationSpec = spring(dampingRatio = 0.7f, stiffness = 20f),
                    background = ButtonBackground(
                        icon = R.drawable.circle_background,
                        offset = DpOffset(2.5.dp, 3.dp)
                    ),
                ),
                color = ElectricViolet
            ),
            Item(
                icon = R.drawable.rounded_rect,
                isSelected = false,
                description = R.string.Plus,
                onClick = {
                    navController.navigate("camera")
                },
                animationType = PlusColorButton(
                    animationSpec = spring(
                        dampingRatio = 0.3f,
                        stiffness = Spring.StiffnessVeryLow
                    ),
                    background = ButtonBackground(
                        icon = R.drawable.polygon_background,
                        offset = DpOffset(1.6.dp, 2.dp)
                    ),
                ),
                color = ElectricViolet

            ),
            Item(
                icon = R.drawable.person,
                isSelected = true,
                description = R.string.Person,
                onClick = {
                    navController.navigate("profile")
                },
                animationType = BellColorButton(
                    animationSpec = spring(dampingRatio = 0.7f, stiffness = 20f),
                    background = ButtonBackground(
                        icon = R.drawable.person_background,
                        offset = DpOffset(2.5.dp, 3.dp)
                    ),
                ),
                color = ElectricViolet
            ),
            // Tambahkan item lain jika diperlukan
        )
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current

    Scaffold(
        bottomBar =  {
            ColorButtonNavBar(navController, colorButtons)
        }
    ) {innerPadding ->
        val postViewModel : PostViewModel = viewModel()
        NavHost(navController = navController, startDestination = "list_data", modifier= Modifier.padding(innerPadding)) {
            composable("list_data") {
                ListRice()
            }
            composable("profile") {
                Profile()
            }
            composable("camera") {
                Camera(postViewModel, navController)
            }
            composable("after_image") {
                ImageWithButtonsScreen(postViewModel, navController)

            }

        }
    }
}

@Composable
fun ColorButtonNavBar(navController: NavHostController,  items: List<Item>, ) {
    var selectedItem by remember { mutableStateOf(0) }
    var prevSelectedIndex by remember { mutableStateOf(0) }
    val context = LocalContext.current
    AnimatedNavigationBar(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 20.dp)
            .height(75.dp),
        selectedIndex = selectedItem,
        ballColor = Color.Blue,
        cornerRadius = shapeCornerRadius(25.dp),
        ballAnimation = Straight(
            spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessVeryLow)
        ),
        indentAnimation = StraightIndent(
            indentWidth = 56.dp,
            indentHeight = 15.dp,
            animationSpec = tween(1000)
        ),
        barColor = items[selectedItem].color
    ) {
        items.forEachIndexed { index, it ->
            ColorButton(
                modifier = Modifier.fillMaxSize(),
                prevSelectedIndex = prevSelectedIndex,
                selectedIndex = selectedItem,
                index = index,
                onClick = {
                    prevSelectedIndex = selectedItem
                    selectedItem = index
                    it.onClick()

                },
                icon = it.icon,
                contentDescription = stringResource(id = it.description),
                animationType = it.animationType,
                background = it.animationType.background
            )
        }
    }
}


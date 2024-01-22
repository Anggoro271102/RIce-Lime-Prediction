package com.example.ricelimepredictionapp

import android.os.Bundle
import android.window.SplashScreen
import androidx.navigation.compose.rememberNavController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ricelimepredictionapp.ui.theme.RiceLimePredictionAppTheme
import com.example.ricelimepredictionapp.ui.welcome.login.LoginScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ricelimepredictionapp.data.repository.StoryRepository
import com.example.ricelimepredictionapp.ui.dashboard.Dashboard
import com.example.ricelimepredictionapp.ui.dashboard.colorButtons.ColorButton
import com.example.ricelimepredictionapp.ui.view_model.LoginViewModel
import com.example.ricelimepredictionapp.ui.view_model.RegisterViewModel
import com.example.ricelimepredictionapp.ui.view_model.ViewModelFactory
import com.example.ricelimepredictionapp.ui.welcome.get_started.GetStarted
import com.example.ricelimepredictionapp.ui.welcome.register.RegisterScreen
import com.example.ricelimepredictionapp.ui.welcome.splash.AnimatedSplashScreen
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Straight
import com.exyte.animatednavbar.animation.indendshape.StraightIndent
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            RiceLimePredictionAppTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
                val signinViewModel: LoginViewModel by viewModels {
                    factory
                }
                val signupViewModel: RegisterViewModel by viewModels {
                    factory
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "splash") {
                        composable("login") {
                            LoginScreen(navController = navController)
                        }
                        composable("register") {
                            RegisterScreen(navController = navController, signupViewModel)
                        }
                        composable("dashboard") {
                            Dashboard()
                        }
                        composable("splash") {
                            AnimatedSplashScreen(navController)
                        }
                        composable("get_started") {
                            GetStarted(navController)
                        }

                    }
                }
            }
        }
    }
}




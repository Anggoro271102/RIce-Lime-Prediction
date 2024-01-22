package com.example.ricelimepredictionapp.ui.dashboard


import android.service.autofill.OnClickAction
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.ricelimepredictionapp.R
import com.example.ricelimepredictionapp.ui.dashboard.colorButtons.BellColorButton
import com.example.ricelimepredictionapp.ui.dashboard.colorButtons.ButtonBackground
import com.example.ricelimepredictionapp.ui.dashboard.colorButtons.CalendarAnimation
import com.example.ricelimepredictionapp.ui.dashboard.colorButtons.ColorButtonAnimation
import com.example.ricelimepredictionapp.ui.dashboard.colorButtons.GearColorButton
import com.example.ricelimepredictionapp.ui.dashboard.colorButtons.PlusColorButton

//@Stable
//data class Item(
//    @DrawableRes val icon: Int,
//    var isSelected: Boolean,
//    val onClick: () -> Unit,
//    @StringRes val description: Int,
//    val animationType: ColorButtonAnimation = BellColorButton(
//        tween(500),
//        background = ButtonBackground(R.drawable.plus)
//    ),
//
//)
//
//val colorButtons = listOf(
//
//    Item(
//        icon = R.drawable.outline_home,
//        isSelected = true,
//        onClick = {
//
//        },
//        description = R.string.Home,
//        animationType = BellColorButton(
//            animationSpec = spring(dampingRatio = 0.7f, stiffness = 20f),
//            background = ButtonBackground(
//                icon = R.drawable.circle_background,
//                offset = DpOffset(2.5.dp, 3.dp)
//            ),
//        ),
//    ),
//    Item(
//        icon = R.drawable.rounded_rect,
//        isSelected = false,
//        onClick = {
//
//        },
//        description = R.string.Plus,
//        animationType = PlusColorButton(
//            animationSpec = spring(
//                dampingRatio = 0.3f,
//                stiffness = Spring.StiffnessVeryLow
//            ),
//            background = ButtonBackground(
//                icon = R.drawable.polygon_background,
//                offset = DpOffset(1.6.dp, 2.dp)
//            ),
//        )
//    ),
//    Item(
//        icon = R.drawable.person,
//        isSelected = true,
//        onClick = {
//
//        },
//        description = R.string.Home,
//        animationType = BellColorButton(
//            animationSpec = spring(dampingRatio = 0.7f, stiffness = 20f),
//            background = ButtonBackground(
//                icon = R.drawable.person_background,
//                offset = DpOffset(2.5.dp, 3.dp)
//            ),
//        )
//    ),
//)
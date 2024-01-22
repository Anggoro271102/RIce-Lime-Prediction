package com.example.ricelimepredictionapp.ui.dashboard.camera

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ricelimepredictionapp.ui.view_model.PostViewModel

@Composable
fun ImageWithButtonsScreen(viewModel: PostViewModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageModifier = Modifier
            .size(200.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(Color.Gray)


        AsyncImage(model = viewModel.afterImage, contentDescription ="" );
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {navController.navigate("camera") },
                modifier = Modifier.weight(1f)
            ) {
                Text("Kembali")
            }

            Button(
                onClick = {navController.navigate("camera")},
                modifier = Modifier.weight(1f)
            ) {
                Text("Save Gambar")
            }
        }
    }
}
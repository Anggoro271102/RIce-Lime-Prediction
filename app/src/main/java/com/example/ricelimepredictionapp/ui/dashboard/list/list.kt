package com.example.ricelimepredictionapp.ui.dashboard.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ListRice (){
    Box (modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        , contentAlignment = Alignment.Center
    )
    {
        Text(text = "List")
    }
}

package com.example.mediaplayer.ui.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MiniPlayerScreen(
    viewModel: MainViewModel,
    onExpandClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        VideoPlayer(
            player = viewModel.player,
            shouldShowFullScreenButton = true,
            onExpandVideoClick = onExpandClick,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun LargePlayerScreen(viewModel: MainViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        VideoPlayer(
            player = viewModel.player,
            shouldShowFullScreenButton = false,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun VideoSelector(viewModel: MainViewModel) {
    val selectVideoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let(viewModel::selectVideo)
        }
    )

    Button(onClick = { selectVideoLauncher.launch("video/*") }) {
        Text("Select Video")
    }
}
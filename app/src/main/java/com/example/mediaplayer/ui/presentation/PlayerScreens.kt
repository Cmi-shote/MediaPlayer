package com.example.mediaplayer.ui.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player

@Composable
fun MiniPlayerScreen(
    player: Player,
    onExpandClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VideoPlayer(
            player = player,
            shouldShowFullScreenButton = true,
            onExpandVideoClick = onExpandClick,
            modifier = Modifier
                .size(height = 200.dp, width = 250.dp)
        )
    }
}

@Composable
fun LargePlayerScreen(player: Player) {
    Column(modifier = Modifier.fillMaxSize()) {
        VideoPlayer(
            player = player,
            shouldShowFullScreenButton = false,
            modifier = Modifier.fillMaxSize()
        )
    }
}
@Composable
fun VideoSelector(onVideoSelected: (Uri) -> Unit) {
    val selectVideoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let { onVideoSelected(it) }
        }
    )

    Button(onClick = { selectVideoLauncher.launch("video/*") }) {
        Text("Select Video")
    }
}
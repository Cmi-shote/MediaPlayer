package com.example.mediaplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mediaplayer.ui.presentation.MainViewModel
import com.example.mediaplayer.ui.presentation.nav.AppNavigation
import com.example.mediaplayer.ui.theme.MediaPlayerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = koinViewModel<MainViewModel>()
            val isVideoSelected by viewModel.isVideoSelected.collectAsState()
            MediaPlayerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    AppNavigation(
                        navController = navController,
                        player = viewModel.player,
                        isVideoSelected = isVideoSelected,
                        onVideoSelected = viewModel::selectVideo
                    )
                }
            }
        }
    }
}
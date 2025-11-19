package com.example.mediaplayer.ui.presentation.nav

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mediaplayer.ui.presentation.LargePlayerScreen
import com.example.mediaplayer.ui.presentation.MiniPlayerScreen
import com.example.mediaplayer.ui.presentation.VideoSelector
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation(
    navController: NavHostController,
    player: Player,
    isVideoSelected: Boolean,
    onVideoSelected: (Uri) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.MainRoute
    ) {
        composable<AppRoute.MainRoute> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                VideoSelector(onVideoSelected)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate(AppRoute.MiniPlayerRoute) },
                    modifier = Modifier,
                    enabled = isVideoSelected
                ) {
                    Text("Click button to open Miniview")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate(AppRoute.MaxPlayerRoute) },
                    modifier = Modifier,
                    enabled = isVideoSelected
                ) {
                    Text("Click button to open MainView")
                }
            }
        }

        composable<AppRoute.MiniPlayerRoute> {
            MiniPlayerScreen(
                player = player,
                onExpandClick = { navController.navigate(AppRoute.MaxPlayerRoute) }
            )
        }

        composable<AppRoute.MaxPlayerRoute> {
            LargePlayerScreen(
                player = player
            )
        }
    }
}

sealed interface AppRoute {
    @Serializable
    data object MainRoute : AppRoute

    @Serializable
    data object MiniPlayerRoute : AppRoute

    @Serializable
    data object MaxPlayerRoute : AppRoute
}
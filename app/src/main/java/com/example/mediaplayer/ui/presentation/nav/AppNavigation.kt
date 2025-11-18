package com.example.mediaplayer.ui.presentation.nav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mediaplayer.ui.presentation.LargePlayerScreen
import com.example.mediaplayer.ui.presentation.MainViewModel
import com.example.mediaplayer.ui.presentation.MiniPlayerScreen
import com.example.mediaplayer.ui.presentation.VideoSelector
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    val viewModel = koinViewModel<MainViewModel>()
    val isVideoSelected by viewModel.isVideoSelected.collectAsState()

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
                VideoSelector(viewModel)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.navigate(AppRoute.MiniPlayerRoute)
                    },
                    enabled = isVideoSelected
                ) {
                    Text("Click button to open Miniview")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.navigate(AppRoute.MaxPlayerRoute)
                    },
                    enabled = isVideoSelected
                ) {
                    Text("Click button to open MainView")
                }
            }
        }

        composable<AppRoute.MiniPlayerRoute> {
            MiniPlayerScreen(
                viewModel = viewModel,
                onExpandClick = {
                    navController.navigate(AppRoute.MaxPlayerRoute)
                }
            )
        }

        composable<AppRoute.MaxPlayerRoute> {
            LargePlayerScreen(viewModel = viewModel)
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
# Media Player Android App

This is an Android media player application built using Kotlin and Jetpack Compose. The app allows users to select and play video files from their device with different viewing modes.

## Features Implemented

- **Video Selection**: Users can browse and select video files from their device's storage using the system's file picker.
- **Mini Player View**: A compact video player interface (200dp height) for playing videos in a smaller window.
- **Full-Screen Player**: An expanded view for immersive video playback across the entire screen.
- **Navigation**: Seamless navigation between the main screen, mini player, and full-screen player using Jetpack Navigation Compose.
- **State Management**: Utilizes ViewModel with SavedStateHandle to persist video selection across configuration changes (e.g., screen rotation).
- **Media Playback**: Integrated ExoPlayer for robust video playback functionality.
- **Dependency Injection**: Koin is used for managing dependencies, including the ExoPlayer instance and ViewModel.
- **Modern UI**: Built with Jetpack Compose for a declarative, responsive user interface following Material Design principles.

## Architecture

- **MainActivity**: Entry point that sets up the Compose UI and navigation.
- **AppNavigation**: Handles screen navigation with three routes: Main, Mini Player, and Large Player.
- **MainViewModel**: Manages video URI state and ExoPlayer lifecycle. Uses SavedStateHandle to persist the selected video URI across process death and configuration changes.
- **PlayerScreens**: Contains composables for different player views and video selector.
- **AppModule**: Koin module for dependency injection.

## What Should Be Done

- **Seamless Playback Continuation**: When expanding from mini player to full-screen player, ensure the video continues playing from the exact position where it was paused or stopped in the mini view.

## Technologies Used

- Kotlin
- Jetpack Compose
- ExoPlayer
- Koin (Dependency Injection)
- Jetpack Navigation Compose
- ViewModel & SavedStateHandle
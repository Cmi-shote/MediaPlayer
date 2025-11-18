package com.example.mediaplayer.di

import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.mediaplayer.ui.presentation.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<Player> {
        ExoPlayer.Builder(androidContext()).build()
    }
    viewModelOf(::MainViewModel)
}
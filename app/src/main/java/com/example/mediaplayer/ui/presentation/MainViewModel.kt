package com.example.mediaplayer.ui.presentation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map

class MainViewModel(
    private val savedStateHandle: SavedStateHandle,
    val player: Player
) : ViewModel() {

    private val _videoUri = MutableStateFlow(
        savedStateHandle.get<String>("videoUri")?.let { Uri.parse(it) } ?: Uri.EMPTY
    )
    val videoUri: StateFlow<Uri> = _videoUri

    val isVideoSelected: StateFlow<Boolean> = videoUri
        .map { it != Uri.EMPTY }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    init {
        player.prepare()
    }

    fun selectVideo(uri: Uri) {
        savedStateHandle["videoUri"] = uri.toString()
        _videoUri.value = uri

        player.setMediaItem(MediaItem.fromUri(uri))
        player.prepare()
        player.playWhenReady = true
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}

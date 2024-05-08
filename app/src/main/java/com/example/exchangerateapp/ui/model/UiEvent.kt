package com.example.exchangerateapp.ui.model

sealed interface UiEvent {

    data class OpenLink(val externalLink: String) : UiEvent
}
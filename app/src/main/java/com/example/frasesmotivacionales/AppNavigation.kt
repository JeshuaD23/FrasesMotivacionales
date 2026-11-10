package com.example.frasesmotivacionales

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.frasesmotivacionales.data.Mood
import com.example.frasesmotivacionales.ui.theme.screens.MoodScreen
import com.example.frasesmotivacionales.ui.theme.screens.PhraseScreen
import com.example.frasesmotivacionales.ui.theme.screens.WelcomeScreen

enum class Screen { WELCOME, MOOD, PHRASE }

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf(Screen.WELCOME) }
    var selectedMood by remember { mutableStateOf(Mood.FELIZ) }

    val duration = 500

    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = {
            // Primero sale la pantalla actual (Fade Out)
            // y después entra la nueva (Fade In)
            fadeIn(tween(duration, delayMillis = duration)) togetherWith
                    fadeOut(tween(duration))
        },
        label = "transicion_pantallas"
    ) { screen ->
        when (screen) {
            Screen.WELCOME -> WelcomeScreen(
                onStart = { currentScreen = Screen.MOOD }
            )

            Screen.MOOD -> MoodScreen(
                onSeePhrase = { mood ->
                    selectedMood = mood
                    currentScreen = Screen.PHRASE
                }
            )

            Screen.PHRASE -> PhraseScreen(
                mood = selectedMood,
                onChooseMood = { currentScreen = Screen.MOOD },
                onHome = { currentScreen = Screen.WELCOME }
            )
        }
    }
}
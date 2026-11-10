package com.example.frasesmotivacionales.ui.theme.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frasesmotivacionales.data.Mood
import com.example.frasesmotivacionales.ui.theme.components.FadeIn
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PhraseScreen(
    mood: Mood,
    onChooseMood: () -> Unit,
    onHome: () -> Unit
) {
    val duration = 700
    val scope = rememberCoroutineScope()

    var phrase by remember { mutableStateOf(mood.phrases.random()) }
    var phraseVisible by remember { mutableStateOf(false) }
    var changing by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(600)
        phraseVisible = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(mood.color.copy(alpha = 0.7f), Color.White))
            )
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FadeIn(delayMillis = 0) {
            Text(
                "Una frase para ti",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(12.dp))

        FadeIn(delayMillis = 300) {
            Text("${mood.emoji} ${mood.label}", fontSize = 20.sp)
        }

        Spacer(Modifier.height(32.dp))

        AnimatedVisibility(
            visible = phraseVisible,
            enter = fadeIn(tween(duration)),
            exit = fadeOut(tween(duration))
        ) {
            Text(
                text = "“$phrase”",
                fontSize = 26.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }

        Spacer(Modifier.height(40.dp))

        FadeIn(delayMillis = 1200) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = {
                        scope.launch {
                            changing = true
                            phraseVisible = false              // 1. Fade Out
                            delay(duration.toLong())
                            phrase = mood.phrases                // 2. Otra frase de la misma categoría
                                .filter { it != phrase }
                                .random()
                            phraseVisible = true                // 3. Fade In
                            delay(duration.toLong())
                            changing = false
                        }
                    },
                    enabled = !changing,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cambiar frase")
                }

                Spacer(Modifier.height(8.dp))

                OutlinedButton(
                    onClick = onChooseMood,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Elegir otro estado de ánimo")
                }

                TextButton(onClick = onHome) {
                    Text("Inicio")
                }
            }
        }
    }
}
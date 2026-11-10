package com.example.frasesmotivacionales.ui.theme.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frasesmotivacionales.data.Mood
import com.example.frasesmotivacionales.ui.theme.components.FadeIn

@Composable
fun MoodScreen(onSeePhrase: (Mood) -> Unit) {
    var selected by remember { mutableStateOf<Mood?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(24.dp))

        FadeIn(delayMillis = 0) {
            Text(
                "Selecciona tu estado de ánimo",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Spacer(Modifier.height(16.dp))

        Mood.entries.forEachIndexed { index, mood ->
            FadeIn(delayMillis = 400 + index * 250) {
                val isSelected = selected == mood
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable { selected = mood },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = mood.color.copy(alpha = if (isSelected) 0.9f else 0.35f)
                    ),
                    border = if (isSelected) BorderStroke(3.dp, Color.Black) else null
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(mood.emoji, fontSize = 30.sp)
                        Spacer(Modifier.width(14.dp))
                        Text(mood.label, fontSize = 20.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        AnimatedContent(
            targetState = selected,
            transitionSpec = {
                fadeIn(tween(400, delayMillis = 400)) togetherWith fadeOut(tween(400))
            },
            label = "descripcion_estado"
        ) { mood ->
            Text(
                text = mood?.description ?: "Elige cómo te sientes para continuar",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(Modifier.height(16.dp))

        FadeIn(delayMillis = 1800) {
            Button(
                onClick = { selected?.let(onSeePhrase) },
                enabled = selected != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver mi frase")
            }
        }
    }
}
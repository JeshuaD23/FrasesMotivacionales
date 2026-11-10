package com.example.frasesmotivacionales.data

import androidx.compose.ui.graphics.Color

enum class Mood(
    val emoji: String,
    val label: String,
    val description: String,
    val color: Color,
    val phrases: List<String>
) {
    FELIZ(
        "😊", "Feliz",
        "¡Qué bien! Aprovecha esa energía.",
        Color(0xFFFFC107),
        listOf(
            "La felicidad crece cuando se comparte.",
            "Hoy es un gran día para sonreír.",
            "Disfruta el momento, te lo ganaste.",
            "Tu alegría contagia a los demás."
        )
    ),
    TRISTE(
        "😔", "Triste",
        "Está bien sentirse así, esto también pasará.",
        Color(0xFF64B5F6),
        listOf(
            "Después de la tormenta siempre sale el sol.",
            "Está bien no estar bien, mañana será mejor.",
            "Cada día difícil te hace más fuerte.",
            "No estás solo, esto también pasará."
        )
    ),
    ENOJADO(
        "😡", "Enojado",
        "Respira profundo, tú tienes el control.",
        Color(0xFFEF5350),
        listOf(
            "Respira hondo, tú controlas tu calma.",
            "No dejes que un mal momento arruine tu día.",
            "La paciencia es la mayor fortaleza.",
            "Suelta lo que no puedes cambiar."
        )
    ),
    ESTRESADO(
        "😰", "Estresado",
        "Ve paso a paso, todo se puede lograr.",
        Color(0xFF81C784),
        listOf(
            "Un paso a la vez es suficiente.",
            "Tómate un descanso, lo mereces.",
            "No tienes que hacerlo todo hoy.",
            "Respira: lo estás haciendo mejor de lo que crees."
        )
    ),
    DESMOTIVADO(
        "😴", "Desmotivado",
        "Un pequeño avance ya es un gran comienzo.",
        Color(0xFFBA68C8),
        listOf(
            "Todos los días son una nueva oportunidad.",
            "Empieza pequeño, pero empieza hoy.",
            "Tu esfuerzo de hoy es tu éxito de mañana.",
            "No te rindas, estás más cerca de lo que piensas."
        )
    )
}
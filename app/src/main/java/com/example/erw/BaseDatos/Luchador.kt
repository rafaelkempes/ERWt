package com.example.erw.BaseDatos

data class Luchador(
    val dni: String,
    val nombre: String,
    val nombreArtistico: String,
    val activo: Boolean,
    val censurado: Boolean,
    val edad: Int,
    val licencia: String,
    val peso: Double,
    val altura: Double,
    val habilidades: String,
    val entrada: String,
    val musica: String,
    val gimmick: String,
    val titulos: String,
    val foto: String
)

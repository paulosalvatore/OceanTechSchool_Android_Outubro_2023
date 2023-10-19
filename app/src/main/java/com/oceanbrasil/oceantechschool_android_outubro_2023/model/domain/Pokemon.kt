package com.oceanbrasil.oceantechschool_android_outubro_2023.model.domain

data class Pokemon(
    val number: Int,
    val name: String,
    val imageUrl: String,
    val type1: String,
    val type2: String,
    val hp: Int,
    val atk: Int,
    val def: Int,
    val speed: Int,
)

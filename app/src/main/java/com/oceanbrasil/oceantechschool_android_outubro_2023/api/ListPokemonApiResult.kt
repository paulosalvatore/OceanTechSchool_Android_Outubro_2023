package com.oceanbrasil.oceantechschool_android_outubro_2023.api

data class ListPokemonApiResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonItemApi>
)

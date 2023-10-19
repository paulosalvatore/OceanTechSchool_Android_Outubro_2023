package com.oceanbrasil.oceantechschool_android_outubro_2023.model.api

data class GetPokemonApiResult(
    val id: Int,
    val name: String,
    val stats: List<StatValue>,
    val types: List<TypeSlot>
)

data class Stat (
    val name: String
)

data class StatValue(
    val baseStat: Int,
    val stat: Stat
)

data class Type(
    val name: String
)

data class TypeSlot(
    val type: Type
)

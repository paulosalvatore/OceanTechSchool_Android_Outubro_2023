package com.oceanbrasil.oceantechschool_android_outubro_2023.viewmodel

import androidx.lifecycle.ViewModel
import com.oceanbrasil.oceantechschool_android_outubro_2023.model.PokemonRepository

class PokemonViewModel : ViewModel() {
    val pokemonItems = PokemonRepository.pokemonItems
}

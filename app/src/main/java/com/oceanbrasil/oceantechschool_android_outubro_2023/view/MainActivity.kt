package com.oceanbrasil.oceantechschool_android_outubro_2023.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oceanbrasil.oceantechschool_android_outubro_2023.R
import com.oceanbrasil.oceantechschool_android_outubro_2023.view.list.PokemonListAdapter
import com.oceanbrasil.oceantechschool_android_outubro_2023.viewmodel.PokemonViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        val pokemonItems = pokemonViewModel.pokemonItems

        val rvPokemon = findViewById<RecyclerView>(R.id.rvPokemon)
        rvPokemon.layoutManager = LinearLayoutManager(this)

        pokemonItems.observe(this) {
            rvPokemon.adapter = PokemonListAdapter(it.toTypedArray())
        }
    }
}

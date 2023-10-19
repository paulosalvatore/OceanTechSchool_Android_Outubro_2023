package com.oceanbrasil.oceantechschool_android_outubro_2023.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.oceanbrasil.oceantechschool_android_outubro_2023.R
import com.oceanbrasil.oceantechschool_android_outubro_2023.viewmodel.PokemonViewModel

class PokemonViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_view)

        val pokemonNumber = intent.getIntExtra("POKEMON_NUMBER", 0)

        if (pokemonNumber == 0) {
            Toast.makeText(this, "Número do pokémon não recebido.", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        val pokemonViewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        pokemonViewModel.loadPokemonByNumber(pokemonNumber)

        val tvName = findViewById<TextView>(R.id.tvName)
        val chipType1 = findViewById<Chip>(R.id.chipType1)
        val chipType2 = findViewById<Chip>(R.id.chipType2)
        val ivImage = findViewById<ImageView>(R.id.ivImage)

        pokemonViewModel.pokemon.observe(this) {
            tvName.text = it.name
            chipType1.text = it.type1
            chipType2.text = it.type2

            Glide.with(this).load(it.imageUrl).into(ivImage)
        }
    }
}

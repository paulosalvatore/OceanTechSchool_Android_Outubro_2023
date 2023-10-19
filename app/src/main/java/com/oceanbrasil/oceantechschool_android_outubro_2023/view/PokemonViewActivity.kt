package com.oceanbrasil.oceantechschool_android_outubro_2023.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.oceanbrasil.oceantechschool_android_outubro_2023.R

class PokemonViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_view)

        val pokemonNumber = intent.getIntExtra("POKEMON_NUMBER", 0)

        Toast.makeText(this, pokemonNumber.toString(), Toast.LENGTH_LONG).show()
    }
}

package com.oceanbrasil.oceantechschool_android_outubro_2023

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


data class ListPokemonResult(
    val name: String,
    val url: String
)

data class ListPokemonApiResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ListPokemonResult>
)

interface PokeApiService {

    // https://pokeapi.co/api/v2/pokemon?limit=20&offset=0
    // Base: https://pokeapi.co/api/v2/
    // Endpoint (Rota): pokemon?limit=20&offset=0
    @GET("pokemon?limit=20&offset=0")
    fun listPokemon(): Call<ListPokemonApiResult>
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PokeApiService::class.java)

        val call = service.listPokemon()

        call.enqueue(object : Callback<ListPokemonApiResult> {
            override fun onResponse(
                call: Call<ListPokemonApiResult>,
                response: Response<ListPokemonApiResult>
            ) {
                // Caso a requisição HTTP tenha sido bem sucedida
                Log.d("POKEMON_API", response.body().toString())
            }

            override fun onFailure(call: Call<ListPokemonApiResult>, t: Throwable) {
                // Caso a requisição HTTP tenha falhado
                Log.e("POKEMON_API", "Erro ao carregar API.", t)
            }
        })
    }
}

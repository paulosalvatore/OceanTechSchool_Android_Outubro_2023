package com.oceanbrasil.oceantechschool_android_outubro_2023.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.oceanbrasil.oceantechschool_android_outubro_2023.model.api.GetPokemonApiResult
import com.oceanbrasil.oceantechschool_android_outubro_2023.model.api.ListPokemonApiResult
import com.oceanbrasil.oceantechschool_android_outubro_2023.model.api.PokeApiService
import com.oceanbrasil.oceantechschool_android_outubro_2023.model.domain.Pokemon
import com.oceanbrasil.oceantechschool_android_outubro_2023.model.domain.PokemonItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository {
    val pokemonItems = MutableLiveData<List<PokemonItem>>()

    val pokemon = MutableLiveData<Pokemon>()

    private val service: PokeApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PokeApiService::class.java)

        val listPokemonCall = service.listPokemon()
        listPokemonCall.enqueue(object : Callback<ListPokemonApiResult> {
            override fun onResponse(
                call: Call<ListPokemonApiResult>,
                response: Response<ListPokemonApiResult>
            ) {
                // Caso a requisição HTTP tenha sido bem sucedida
                Log.d("POKEMON_API", response.body().toString())

                response.body()?.let {
                    this@PokemonRepository.pokemonItems.postValue(
                        it.results.mapIndexed { index, result ->
                            val number = index + 1
                            val imageUrl = getPokemonImageUrl(number)

                            PokemonItem(number, result.name, imageUrl)
                        }
                    )
                }
            }

            override fun onFailure(call: Call<ListPokemonApiResult>, t: Throwable) {
                // Caso a requisição HTTP tenha falhado
                Log.e("POKEMON_API", "Erro ao carregar API.", t)
            }
        })
    }

    private fun getPokemonImageUrl(number: Int): String {
        val numberFormatted = number.toString().padStart(3, '0')
        return "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$numberFormatted.png"
    }

    fun loadPokemonByNumber(number: Int) {
        val getPokemonCall = service.getPokemonByNumber(number)
        getPokemonCall.enqueue(object : Callback<GetPokemonApiResult> {
            override fun onResponse(
                call: Call<GetPokemonApiResult>,
                response: Response<GetPokemonApiResult>
            ) {
                Log.d("POKEMON_API", response.body().toString())

                response.body()?.let { getPokemonApiResult ->
                    val number = getPokemonApiResult.id
                    val name = getPokemonApiResult.name
                    val imageUrl = getPokemonImageUrl(getPokemonApiResult.id)
                    val type1 = getPokemonApiResult.types.first().type.name
                    val type2 = getPokemonApiResult.types.last().type.name
                    val hp = getPokemonApiResult.stats.first { it.stat.name == "hp" }.baseStat
                    val atk = getPokemonApiResult.stats.first { it.stat.name == "attack" }.baseStat
                    val def = getPokemonApiResult.stats.first { it.stat.name == "defense" }.baseStat
                    val speed = getPokemonApiResult.stats.first { it.stat.name == "speed" }.baseStat

                    this@PokemonRepository.pokemon.postValue(
                        Pokemon(number, name, imageUrl, type1, type2, hp, atk, def, speed)
                    )
                }
            }

            override fun onFailure(call: Call<GetPokemonApiResult>, t: Throwable) {
                Log.e("POKEMON_API", "Erro ao carregar API.", t)
            }
        })
    }
}

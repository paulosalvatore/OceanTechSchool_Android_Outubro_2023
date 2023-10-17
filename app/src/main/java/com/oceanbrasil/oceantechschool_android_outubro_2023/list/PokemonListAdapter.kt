package com.oceanbrasil.oceantechschool_android_outubro_2023.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oceanbrasil.oceantechschool_android_outubro_2023.R

class PokemonListAdapter(private val dataSet: Array<PokemonItem>) :
    RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView
        val ivImage: ImageView

        init {
            tvName = view.findViewById(R.id.tvName)
            ivImage = view.findViewById(R.id.ivImage)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.pokemon_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val pokemonItem = dataSet[position]

        viewHolder.tvName.text = pokemonItem.name.replaceFirstChar {
            it.uppercase()
        }

        Glide.with(viewHolder.ivImage)
            .load(pokemonItem.imageUrl)
            .into(viewHolder.ivImage)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}

package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@Suppress("DEPRECATION")
class CharacterAdapter(
    private val characters: List<Character>,
    private val onItemClick: (Character) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.character_name)
        val imageView: ImageView = view.findViewById(R.id.character_image)
        init {
            view.setOnClickListener {
                val character = characters[adapterPosition]
                val context = view.context
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("name", character.name)
                    putExtra("image", character.image)
                    putExtra("species", character.species)
                    putExtra("status", character.status)
                    putExtra("gender", character.gender)
                }

                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.nameText.text = character.name
        Glide.with(holder.itemView.context)
            .load(character.image)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int = characters.size
}
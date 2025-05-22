package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val nameView = findViewById<TextView>(R.id.textViewName)
        val speciesView = findViewById<TextView>(R.id.textViewSpecies)
        val statusView = findViewById<TextView>(R.id.textViewStatus)
        val genderView = findViewById<TextView>(R.id.textViewGender)

        val name = intent.getStringExtra("name")
        val imageUrl = intent.getStringExtra("image")
        val species = intent.getStringExtra("species")
        val status = intent.getStringExtra("status")
        val gender = intent.getStringExtra("gender")

        nameView.text = name
        speciesView.text = "Especie: $species"
        statusView.text = "Estado: $status"
        genderView.text = "Género: $gender"

        Glide.with(this)
            .load(imageUrl)
            .into(imageView)

        }
}
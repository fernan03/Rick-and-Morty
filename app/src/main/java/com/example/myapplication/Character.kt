package com.example.myapplication

import androidx.annotation.Size

data class Character(
    val id:Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String

)

data class CharacterResponse(
    val results: List<Character>
)

package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        RetrofitCliente.api.getCharacters().enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful) {
                    val characters = response.body()?.results ?: emptyList()
                    recyclerView.adapter = CharacterAdapter(characters) { character ->
                        val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                            putExtra("character_name", character.name)
                            putExtra("character_status", character.status)
                            putExtra("character_species", character.species)
                            putExtra("character_gender", character.gender)
                        }
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error al cargar datos", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
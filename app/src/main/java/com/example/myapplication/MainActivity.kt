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
    private lateinit var adapter: CharacterAdapter
    private val characterList = mutableListOf<Character>()
    private var currentPage = 1
    private var isLoading = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CharacterAdapter(characterList){character ->
            val intent = Intent(this@MainActivity,DetailActivity::class.java).apply {
                putExtra("character_name", character.name)
                putExtra("character_status", character.status)
                putExtra("character_species", character.species)
                putExtra("character_gender", character.gender)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if(!isLoading && !isLastPage){
                    if((visibleItemCount + firstVisibleItemPosition)>=totalItemCount && firstVisibleItemPosition >=0){
                        fetchCharacters(currentPage)
                    }
                }
            }
        })

        fetchCharacters(currentPage)
    }
    private fun fetchCharacters(page:Int){
        isLoading=true

        RetrofitCliente.api.getCharacters(page).enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if(response.isSuccessful){
                    val newCharacters = response.body()?.results ?: emptyList()
                    characterList.addAll(newCharacters)
                    adapter.notifyDataSetChanged()
                    isLastPage = newCharacters.isEmpty()
                    currentPage++
                }
                isLoading=false
            }
            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                isLoading = false
                Toast.makeText(this@MainActivity, "Error al cargar datos", Toast.LENGTH_SHORT).show()
            }

        })
    }
}
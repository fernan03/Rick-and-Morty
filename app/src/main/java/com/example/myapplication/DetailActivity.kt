package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        findViewById<TextView>(R.id.detail_name).text = intent.getStringExtra("character_name")
        findViewById<TextView>(R.id.detail_status).text = intent.getStringExtra("character_status")
        findViewById<TextView>(R.id.detail_species).text = intent.getStringExtra("character_species")
        findViewById<TextView>(R.id.detail_gender).text = intent.getStringExtra("character_gender")
    }
}
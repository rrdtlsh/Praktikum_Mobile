package com.example.biografi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLihatDetail = findViewById<Button>(R.id.btnLihatDetail)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        btnLihatDetail.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        btnKembali.setOnClickListener {
            finish()
        }
    }
}
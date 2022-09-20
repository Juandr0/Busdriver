package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

   lateinit var startButton : Button
   lateinit var rulesButton : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.startButton)
        startButton.text ="Starta spelet"
        rulesButton = findViewById(R.id.rulesButton)
        rulesButton.text ="Spelregler"

        startButton.setOnClickListener {
            val intent = Intent(this, NumOfPlayersActivity::class.java)
            startActivity(intent)
        }

        rulesButton.setOnClickListener {
            val intent = Intent(this, RulesActivity::class.java)
            startActivity(intent)
        }
    }




}
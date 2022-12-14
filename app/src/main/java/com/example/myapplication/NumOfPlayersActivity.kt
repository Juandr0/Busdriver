package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class NumOfPlayersActivity : AppCompatActivity() {

    private lateinit var amountOfPlayers: TextView
    private lateinit var onePlayer: Button
    private lateinit var twoPlayers: Button
    private lateinit var threePlayers: Button
    private lateinit var fourPlayers: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numofplayers)
        initiateView()
        activateClickListeners()
    }


    //Skapar click listeners som tar användaren vidare till spelet tillsammans med
    //värdet på "numOfPlayers" som kan vara 1-4 beroende på vilken knapp användaren klickade på.
    //Detta används i CardgameActivity för att bestämma hur många spelare

    private fun activateClickListeners() {
        onePlayer.setOnClickListener {
            intent = Intent(this, ChangePlayerNameActivity::class.java)
            intent.putExtra("numOfPlayers", 1)
            startActivity(intent)
        }

        twoPlayers.setOnClickListener {
            intent = Intent(this, ChangePlayerNameActivity::class.java)
            intent.putExtra("numOfPlayers", 2)
            startActivity(intent)
        }

        threePlayers.setOnClickListener {
            intent = Intent(this, ChangePlayerNameActivity::class.java)
            intent.putExtra("numOfPlayers", 3)
            startActivity(intent)
        }

        fourPlayers.setOnClickListener {
            intent = Intent(this, ChangePlayerNameActivity::class.java)
            intent.putExtra("numOfPlayers", 4)
            startActivity(intent)
        }
    }

    //Initierar och sätter relevant text på textview och knapparna för hur många som ska spela
    private fun initiateView() {

        amountOfPlayers = findViewById(R.id.amountOfPlayersView)

        onePlayer = findViewById(R.id.onePlayer)

        twoPlayers = findViewById(R.id.twoPlayers)
        threePlayers = findViewById(R.id.threePlayers)
        fourPlayers = findViewById(R.id.fourPlayers)
    }
}
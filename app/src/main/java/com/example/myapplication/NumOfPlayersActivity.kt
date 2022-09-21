package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class NumOfPlayersActivity : AppCompatActivity() {

    lateinit var amountOfPlayers : TextView
    lateinit var onePlayer : Button
    lateinit var twoPlayers : Button
    lateinit var threePlayers : Button
    lateinit var fourPlayers : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.numofplayers_activity)
        initiateView()
        activateClickListeners()
    }


    //Skapar click listeners som tar användaren vidare till spelet tillsammans med
    //värdet på "numOfPlayers" som kan vara 1-4 beroende på vilken knapp användaren klickade på.
    //Detta används i CardgameActivity för att bestämma hur många spelare

    fun activateClickListeners(){
        onePlayer.setOnClickListener {
            intent = Intent(this, ChangePlayerNameView::class.java)
            intent.putExtra("numOfPlayers", 1)
            startActivity(intent)
        }

        twoPlayers.setOnClickListener {
            intent = Intent(this, ChangePlayerNameView::class.java)
            intent.putExtra("numOfPlayers", 2)
            startActivity(intent)
        }

        threePlayers.setOnClickListener {
            intent = Intent(this, ChangePlayerNameView::class.java)
            intent.putExtra("numOfPlayers", 3)
            startActivity(intent)
        }

        fourPlayers.setOnClickListener {
            intent = Intent(this, ChangePlayerNameView::class.java)
            intent.putExtra("numOfPlayers", 4)
            startActivity(intent)
        }
    }

    //Initierar och sätter relevant text på textview och knapparna för hur många som ska spela
    fun initiateView() {

        amountOfPlayers = findViewById(R.id.amountOfPlayersView)
        amountOfPlayers.text = "Hur många ska spela?"

        onePlayer = findViewById(R.id.onePlayer)
        onePlayer.text = "En spelare"

        twoPlayers = findViewById(R.id.twoPlayers)
        twoPlayers.text = "Två spelare"

        threePlayers = findViewById(R.id.threePlayers)
        threePlayers.text ="Tre spelare"

        fourPlayers = findViewById(R.id.fourPlayers)
        fourPlayers.text = "Fyra spelare"
    }
}
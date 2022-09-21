package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ChangePlayerNameView : AppCompatActivity() {

    // denna intent ska skickas till CardgameActivity::class.java
    lateinit var startGameButton : Button
    lateinit var changePlayerNameView : TextView
    lateinit var playerOneNameInputEdit   : EditText
    lateinit var playerTwoNameInputEdit   : EditText
    lateinit var playerThreeNameInputEdit : EditText
    lateinit var playerFourNameInputEdit  : EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.changeplayername_activity)

        playerOneNameInputEdit.findViewById<EditText>(R.id.playerOneNameInputEdit)
        playerTwoNameInputEdit.findViewById<EditText>(R.id.playerTwoNameInputEdit)
        playerThreeNameInputEdit.findViewById<EditText>(R.id.playerThreeNameInputEdit)
        playerFourNameInputEdit.findViewById<EditText>(R.id.playerFourNameInputEdit)

        changePlayerNameView = findViewById(R.id.changePlayerNameView)
        changePlayerNameView.text = "Vill du välja själv namn på de som ska spela?"

        startGameButton = findViewById(R.id.startGameButton)
        startGameButton.text = "Starta spelet"



        val numOfPlayers = getAmountOfPlayers()
        getEditTextViews(numOfPlayers)
    }




    fun getEditTextViews(numOfPlayersAsInt : Int) {

        when (numOfPlayersAsInt) {
            1 -> {
                playerTwoNameInputEdit.visibility = View.INVISIBLE
                playerThreeNameInputEdit.visibility = View.INVISIBLE
                playerFourNameInputEdit.visibility = View.INVISIBLE
            }
            2 -> {
                playerThreeNameInputEdit.visibility = View.INVISIBLE
                playerFourNameInputEdit.visibility = View.INVISIBLE
            }

            3 -> {
                playerFourNameInputEdit.visibility = View.INVISIBLE
            }

        }

    }

    fun getAmountOfPlayers() : Int{
        return intent.getIntExtra("numOfPlayers", 1)
    }

}
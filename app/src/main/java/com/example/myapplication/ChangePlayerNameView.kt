package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView

class ChangePlayerNameView : AppCompatActivity() {

    lateinit var changePlayerNameView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.changeplayername_activity)


        val numOfPlayers = getAmountOfPlayers()

        var playerOneNameInputEdit   : EditText = findViewById<EditText>(R.id.playerOneNameInputEdit)
        var playerTwoNameInputEdit   : EditText = findViewById<EditText>(R.id.playerTwoNameInputEdit)
        var playerThreeNameInputEdit : EditText = findViewById<EditText>(R.id.playerThreeNameInputEdit)
        var playerFourNameInputEdit  : EditText = findViewById<EditText>(R.id.playerFourNameInputEdit)

        var startGameButton : Button = findViewById(R.id.startGameButton)
        startGameButton.text = "Starta spelet"

        changePlayerNameView = findViewById(R.id.changePlayerNameView)
        changePlayerNameView.text = "Ange namn på de som ska spela"

        getEditTextViews(numOfPlayers, playerTwoNameInputEdit, playerThreeNameInputEdit, playerFourNameInputEdit)

        startGameButton.setOnClickListener {
            startGame(playerOneNameInputEdit,playerTwoNameInputEdit, playerThreeNameInputEdit, playerFourNameInputEdit)
        }
    }

    //Default värdepå edittext om inget anges


    fun getAmountOfPlayers() : Int{
        return intent.getIntExtra("numOfPlayers", 1)
    }

    fun getEditTextViews(numOfPlayersAsInt : Int, playerTwoNameInputEdit  : EditText, playerThreeNameInputEdit : EditText, playerFourNameInputEdit : EditText) {
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

    fun startGame(playerOneNameInputEdit : EditText, playerTwoNameInputEdit : EditText, playerThreeNameInputEdit : EditText, playerFourNameInputEdit : EditText, ) {
        val oldIntent = intent.getIntExtra("numOfPlayers", 1)
        intent = Intent(this, CardgameActivity::class.java)
        val playerOneName = playerOneNameInputEdit.text.toString()
        val playerTwoName = playerTwoNameInputEdit.text.toString()
        val playerThreeName = playerThreeNameInputEdit.text.toString()
        val playerFourName = playerFourNameInputEdit.text.toString()



        intent.putExtra("player1", playerOneName)
        intent.putExtra("player2", playerTwoName)
        intent.putExtra("player3", playerThreeName)
        intent.putExtra("player4", playerFourName)
        intent.putExtra("numOfPlayers", oldIntent)

        startActivity(intent)

    }


}

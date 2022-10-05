package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ChangePlayerNameActivity : AppCompatActivity() {

    private lateinit var changePlayerNameView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changeplayername)


        val numOfPlayers = getAmountOfPlayers()

        val playerOneNameInputEdit: EditText = findViewById(R.id.playerOneNameInputEdit)
        val playerTwoNameInputEdit: EditText = findViewById(R.id.playerTwoNameInputEdit)
        val playerThreeNameInputEdit: EditText = findViewById(R.id.playerThreeNameInputEdit)
        val playerFourNameInputEdit: EditText = findViewById(R.id.playerFourNameInputEdit)

        val startGameButton: Button = findViewById(R.id.startGameButton)

        changePlayerNameView = findViewById(R.id.changePlayerNameView)
        getEditTextViews(
            numOfPlayers,
            playerTwoNameInputEdit,
            playerThreeNameInputEdit,
            playerFourNameInputEdit
        )

        startGameButton.setOnClickListener {
            startGame(
                playerOneNameInputEdit,
                playerTwoNameInputEdit,
                playerThreeNameInputEdit,
                playerFourNameInputEdit
            )
        }
    }

    //Default värdepå edittext om inget anges


    private fun getAmountOfPlayers(): Int {
        return intent.getIntExtra("numOfPlayers", 1)
    }

    private fun getEditTextViews(
        numOfPlayersAsInt: Int,
        playerTwoNameInputEdit: EditText,
        playerThreeNameInputEdit: EditText,
        playerFourNameInputEdit: EditText
    ) {
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

    private fun startGame(
        playerOneNameInputEdit: EditText,
        playerTwoNameInputEdit: EditText,
        playerThreeNameInputEdit: EditText,
        playerFourNameInputEdit: EditText
    ) {
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

package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Player (var name : String, var score : Int){
}


class Card (var name : String, var number : Int, var suite : String, var image: Int, var isFaceCard : Boolean = false) {
}



class CardgameActivity : AppCompatActivity() {

    var imageViewList = mutableListOf<ImageView>()
    var deckOfCards = mutableListOf<Card>()
    var cardsInPyramid = mutableListOf<Card>()
    var playerList = mutableListOf<Player>()


    lateinit var playerOneScore : TextView
    lateinit var playerTwoScore : TextView
    lateinit var playerThreeScore : TextView
    lateinit var playerFourScore : TextView
    lateinit var builder : AlertDialog.Builder



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.cardgame_activity)
        initiatePlayerScoreViews()
        amountOfPlayers()
        initiateDeckOfCards()
    }

    override fun onStart() {
        super.onStart()
        deckOfCards.shuffle()
        initiateCardsOnBoard()
        builder = AlertDialog.Builder(this)

        // Skapa en loop som kör igenom spelet tills någon har 5pts.
                startGame()



    }



    fun startGame(){
        //Hårdkodat temporärt
        for (player in playerList) {
            if (player.score != 5) {
                activateFirstRow(playerList[0])
            }
        }
    }

    //Initierar de textviews som används för att visa poängställningen och knyter dem till
    //de olika objekten av spelare. Dessa sparas i listan playerList som nås över hela klassen.

    fun initiatePlayerScoreViews() {
        var player1Name = intent.getStringExtra("player1").toString()
        var player2Name = intent.getStringExtra("player2").toString()
        var player3Name = intent.getStringExtra("player3").toString()
        var player4Name = intent.getStringExtra("player4").toString()

        if (player1Name == ""){
            player1Name = "Player 1"
        }
        if (player2Name == ""){
            player2Name = "Player 2"
        }
        if (player3Name == ""){
            player3Name = "Player 3"
        }
        if (player4Name == ""){
            player4Name = "Player 4"
        }


        var player1 = Player(player1Name , 0)
        playerOneScore = findViewById(R.id.counterOne)
        "${player1.name}: ${player1.score}".also { playerOneScore.text = it }


        var player2 = Player(player2Name, 0)
        playerTwoScore = findViewById(R.id.counterTwo)
        "${player2.name}: ${player2.score}".also { playerTwoScore.text = it }


        var player3 = Player(player3Name, 0)
        playerThreeScore = findViewById(R.id.counterThree)
        "${player3.name}: ${player3.score}".also { playerThreeScore.text = it }


        var player4 = Player(player4Name, 0)
        playerFourScore = findViewById(R.id.counterFour)
        "${player4.name}:  ${player4.score}".also { playerFourScore.text = it }

        var numOfPlayersAsInt = intent.getIntExtra("numOfPlayers", 0)
        var index = 1

        when (numOfPlayersAsInt) {
            1 -> {
                playerList.add(player1)
            }
            2 -> {
                playerList.add(player1)
                playerList.add(player2)
            }
            3 -> {
                playerList.add(player1)
                playerList.add(player2)
                playerList.add(player3)
            }
            3 -> {
                playerList.add(player1)
                playerList.add(player2)
                playerList.add(player3)
                playerList.add(player4)
            }
        }
    }


    fun amountOfPlayers() {
        val numOfPlayers = intent.getIntExtra("numOfPlayers", 0)

        if (numOfPlayers != 0){
            when (numOfPlayers.toString().toInt()) {
                1 -> {
                    playerTwoScore.visibility = View.INVISIBLE
                    playerThreeScore.visibility = View.INVISIBLE
                    playerFourScore.visibility = View.INVISIBLE
                }
                2 -> {
                    playerThreeScore.visibility = View.INVISIBLE
                    playerFourScore.visibility = View.INVISIBLE
                }
                3 -> {
                    playerFourScore.visibility = View.INVISIBLE

                }
        }

        }
    }


    fun activateFirstRow(player : Player) {
        var currentPlayer = player
        Toast.makeText(this,getString(R.string.newTurn, player.name),Toast.LENGTH_LONG).show()
        var rowList = mutableListOf<ImageView>(imageViewList[0], imageViewList[1], imageViewList[2], imageViewList[3], imageViewList[4])

            imageViewList[0].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[0])
                diableClickRow(rowList)
                if (!cardsInPyramid[0].isFaceCard) activateSecondRow(currentPlayer) else lostGame(currentPlayer)

            }
            imageViewList[1].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[1])
                diableClickRow(rowList)
                if (!cardsInPyramid[0].isFaceCard) activateSecondRow(currentPlayer) else lostGame(currentPlayer)
            }
            imageViewList[2].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[2])
                diableClickRow( rowList)
                if (!cardsInPyramid[0].isFaceCard) activateSecondRow(currentPlayer) else lostGame(currentPlayer)
            }
            imageViewList[3].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[3])
                diableClickRow(rowList)
                if (!cardsInPyramid[0].isFaceCard) activateSecondRow(currentPlayer) else lostGame(currentPlayer)
            }
            imageViewList[4].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[4])
                diableClickRow(rowList)
                if (!cardsInPyramid[0].isFaceCard) activateSecondRow(currentPlayer) else lostGame(currentPlayer)
            }
    }

    fun activateSecondRow(player : Player) {
        var currentPlayer = player
        var rowList = mutableListOf<ImageView>(imageViewList[5], imageViewList[6], imageViewList[7], imageViewList[8])

        imageViewList[5].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[5])
            diableClickRow(rowList)
            if (!cardsInPyramid[1].isFaceCard) activateThirdRow(currentPlayer)else lostGame(currentPlayer)

        }
        imageViewList[6].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[6])
            diableClickRow(rowList)
            if (!cardsInPyramid[1].isFaceCard) activateThirdRow(currentPlayer) else lostGame(currentPlayer)
        }
        imageViewList[7].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[7])
            diableClickRow(rowList)
            if (!cardsInPyramid[1].isFaceCard) activateThirdRow(currentPlayer) else lostGame(currentPlayer)
        }
        imageViewList[8].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[8])
            diableClickRow(rowList)
            if (!cardsInPyramid[1].isFaceCard) activateThirdRow(currentPlayer) else lostGame(currentPlayer)

        }
    }

    fun activateThirdRow(player : Player) {
        var currentPlayer = player
        var rowList = mutableListOf<ImageView>(imageViewList[9], imageViewList[10], imageViewList[11])

        imageViewList[9].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[9])
            diableClickRow(rowList)
            if (!cardsInPyramid[2].isFaceCard) activateFourthRow(currentPlayer) else lostGame(currentPlayer)

        }
        imageViewList[10].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[10])
            if (!cardsInPyramid[2].isFaceCard){ activateFourthRow(currentPlayer)} else lostGame(currentPlayer)

        }
        imageViewList[11].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[11])
            diableClickRow( rowList)
            activateFourthRow(currentPlayer)
            if (!cardsInPyramid[2].isFaceCard) activateFourthRow(currentPlayer) else lostGame(currentPlayer)

        }
    }

    fun activateFourthRow(player : Player) {
        var currentPlayer = player
        var rowList = mutableListOf<ImageView>(imageViewList[12], imageViewList[13])

        imageViewList[12].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[12])
            diableClickRow(rowList)
            if (!cardsInPyramid[3].isFaceCard) activateFifthrow(currentPlayer) else lostGame(currentPlayer)
        }
        imageViewList[13].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[13])
            diableClickRow(rowList)
            if (!cardsInPyramid[3].isFaceCard) activateFifthrow(currentPlayer) else lostGame(currentPlayer)
        }

    }

    fun activateFifthrow(player : Player) {
        var currentPlayer = player
        var rowList = mutableListOf<ImageView>(imageViewList[14])

        imageViewList[14].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[14])
            diableClickRow(rowList)
            if (!cardsInPyramid[4].isFaceCard) wonGame(currentPlayer) else lostGame(currentPlayer)
        }
    }

    //Tar flyttar två instanser av samma kod till en funktion, används för att öppna en messagebox som bara går att stänga
    //när användaren klickar på ok. På klick resetas även korten och onStart körs.
    fun builderHelper(){
        builder.setCancelable(false)
        builder.setPositiveButton("OK"){dialogInterface, it ->
            resetPictures()
            cardsInPyramid.removeAll(cardsInPyramid)
            onStart()
        }
        builder.show()
    }

    fun wonGame(player : Player) {

        builder.setTitle(getString(R.string.winTitle))
        builder.setMessage(getString(R.string.winText, player.name))
        builderHelper()
        player.score ++

        playerOneScore.text = "${player.name}: ${player.score}"
    }

    fun lostGame(player : Player) {
        builder.setTitle(getString(R.string.lossTitle))
        builder.setMessage(getString(R.string.lossText, player.name))
        builder.setCancelable(false)
        builderHelper()


    }

    fun resetPictures() {
        for (image in imageViewList) {
            image.setImageResource(R.drawable.cardback)
        }
    }


    fun diableClickRow(imageViewList: List<ImageView>){
           for (view in imageViewList){
               view.isClickable = false
           }
    }


    fun initiateCardsOnBoard() {

        //Första raden nedifrån
        var firstRowFirstCard = findViewById<ImageView>(R.id.firstRowFirstCard)
        var firstRowSecondCard = findViewById<ImageView>(R.id.firstRowSecondCard)
        var firstRowThirdCard = findViewById<ImageView>(R.id.firstRowThirdCard)
        var firstRowFourthCard = findViewById<ImageView>(R.id.firstRowFourthCard)
        var firstRowFifthCard = findViewById<ImageView>(R.id.firstRowFifthCard)


        imageViewList.add(firstRowFirstCard)
        imageViewList.add(firstRowSecondCard)
        imageViewList.add(firstRowThirdCard)
        imageViewList.add(firstRowFourthCard)
        imageViewList.add(firstRowFifthCard)

        //Andra raden nedifrån
        var secondRowFirstCard = findViewById<ImageView>(R.id.secondRowFirstCard)
        var secondRowSecondCard = findViewById<ImageView>(R.id.secondRowSecondCard)
        var secondRowThirdCard = findViewById<ImageView>(R.id.secondRowThirdCard)
        var secondRowFourthCard = findViewById<ImageView>(R.id.secondRowFourthCard)

        imageViewList.add(secondRowFirstCard)
        imageViewList.add(secondRowSecondCard)
        imageViewList.add(secondRowThirdCard)
        imageViewList.add(secondRowFourthCard)
        //Tredje raden nedifrån

        var thirdRowFirstCard = findViewById<ImageView>(R.id.thirdRowFirstCard)
        var thirdRowSecondCard = findViewById<ImageView>(R.id.thirdRowSecondCard)
        var thirdRowThirdCard = findViewById<ImageView>(R.id.thirdRowThirdCard)


        imageViewList.add(thirdRowFirstCard)
        imageViewList.add(thirdRowSecondCard)
        imageViewList.add(thirdRowThirdCard)
        //Fjärde raden nedifrån

        var fourthRowFirstCard = findViewById<ImageView>(R.id.fourthRowFirstCard)
        var fourthRowSecondCard = findViewById<ImageView>(R.id.fourthRowSecondCard)

        imageViewList.add(fourthRowFirstCard)
        imageViewList.add(fourthRowSecondCard)
        //Femte raden nedifrån

        var fifthRowFirstCard = findViewById<ImageView>(R.id.fifthRowFirstCard)
        imageViewList.add(fifthRowFirstCard)

    }




    fun changeCardBackgroundHelperMethod(imageView: ImageView){
        imageView.setImageResource(deckOfCards[0].image)
        cardsInPyramid.add(deckOfCards[0])
        deckOfCards.removeAt(0)
    }

    fun initiateDeckOfCards () {

        deckOfCards.add(Card("ace_of_spades",  1, "spades", R.drawable.ace_of_spades))
        deckOfCards.add(Card("two_of_spades",  2, "spades", R.drawable.two_of_spades))
        deckOfCards.add(Card("three_of_spades",3, "spades", R.drawable.three_of_spades))
        deckOfCards.add(Card("four_of_spades", 4, "spades", R.drawable.four_of_spades))
        deckOfCards.add(Card("five_of_spades", 5, "spades", R.drawable.five_of_spades))
        deckOfCards.add(Card("six_of_spades",  6, "spades", R.drawable.six_of_spades))
        deckOfCards.add(Card("seven_of_spades",7, "spades", R.drawable.seven_of_spades))
        deckOfCards.add(Card("eight_of_spades",8, "spades", R.drawable.eight_of_spades))
        deckOfCards.add(Card("nine_of_spades", 9, "spades", R.drawable.nine_of_spades))
        deckOfCards.add(Card("ten_of_spades",  10, "spades",R.drawable.ten_of_spades))
        deckOfCards.add(Card("jack_of_spades", 11, "spades",R.drawable.jack_of_spades, isFaceCard = true))
        deckOfCards.add(Card("queen_of_spades",12, "spades",R.drawable.queen_of_spades, isFaceCard = true))
        deckOfCards.add(Card("king_of_spades", 13, "spades",R.drawable.king_of_spades, isFaceCard = true))

        deckOfCards.add(Card("ace_of_diamonds",  1, "diamonds", R.drawable.ace_of_diamonds))
        deckOfCards.add(Card("two_of_diamonds",  2, "diamonds", R.drawable.two_of_diamonds))
        deckOfCards.add(Card("three_of_diamonds",3, "diamonds", R.drawable.three_of_diamonds))
        deckOfCards.add(Card("four_of_diamonds", 4, "diamonds", R.drawable.four_of_diamonds))
        deckOfCards.add(Card("five_of_diamonds", 5, "diamonds", R.drawable.five_of_diamonds))
        deckOfCards.add(Card("six_of_diamonds",  6, "diamonds", R.drawable.six_of_diamonds))
        deckOfCards.add(Card("seven_of_diamonds",7, "diamonds", R.drawable.seven_of_diamonds))
        deckOfCards.add(Card("eight_of_diamonds",8, "diamonds", R.drawable.eight_of_diamonds))
        deckOfCards.add(Card("nine_of_diamonds", 9, "diamonds", R.drawable.nine_of_diamonds))
        deckOfCards.add(Card("ten_of_diamonds",  10, "diamonds",R.drawable.ten_of_diamonds))
        deckOfCards.add(Card("jack_of_diamonds", 11, "diamonds",R.drawable.jack_of_diamonds, isFaceCard = true))
        deckOfCards.add(Card("queen_of_diamonds",12, "diamonds",R.drawable.queen_of_diamonds, isFaceCard = true))
        deckOfCards.add(Card("king_of_diamonds", 13, "diamonds",R.drawable.king_of_diamonds, isFaceCard = true))

        deckOfCards.add(Card("ace_of_clubs",  1, "clubs", R.drawable.ace_of_clubs))
        deckOfCards.add(Card("two_of_clubs",  2, "clubs", R.drawable.two_of_clubs))
        deckOfCards.add(Card("three_of_clubs",3, "clubs", R.drawable.three_of_clubs))
        deckOfCards.add(Card("four_of_clubs", 4, "clubs", R.drawable.four_of_clubs))
        deckOfCards.add(Card("five_of_clubs", 5, "clubs", R.drawable.five_of_clubs))
        deckOfCards.add(Card("six_of_clubs",  6, "clubs", R.drawable.six_of_clubs))
        deckOfCards.add(Card("seven_of_clubs",7, "clubs", R.drawable.seven_of_clubs))
        deckOfCards.add(Card("eight_of_clubs",8, "clubs", R.drawable.eight_of_clubs))
        deckOfCards.add(Card("nine_of_clubs", 9, "clubs", R.drawable.nine_of_clubs))
        deckOfCards.add(Card("ten_of_clubs",  10, "clubs",R.drawable.ten_of_clubs))
        deckOfCards.add(Card("jack_of_clubs", 11, "clubs",R.drawable.jack_of_clubs, isFaceCard = true))
        deckOfCards.add(Card("queen_of_clubs",12, "clubs",R.drawable.queen_of_clubs, isFaceCard = true))
        deckOfCards.add(Card("king_of_clubs", 13, "clubs",R.drawable.king_of_clubs, isFaceCard = true))

        deckOfCards.add(Card("ace_of_hearts",  1, "hearts", R.drawable.ace_of_hearts))
        deckOfCards.add(Card("two_of_hearts",  2, "hearts", R.drawable.two_of_hearts))
        deckOfCards.add(Card("three_of_hearts",3, "hearts", R.drawable.three_of_hearts))
        deckOfCards.add(Card("four_of_hearts", 4, "hearts", R.drawable.four_of_hearts))
        deckOfCards.add(Card("five_of_hearts", 5, "hearts", R.drawable.five_of_hearts))
        deckOfCards.add(Card("six_of_hearts",  6, "hearts", R.drawable.six_of_hearts))
        deckOfCards.add(Card("seven_of_hearts",7, "hearts", R.drawable.seven_of_hearts))
        deckOfCards.add(Card("eight_of_hearts",8, "hearts", R.drawable.eight_of_hearts))
        deckOfCards.add(Card("nine_of_hearts", 9, "hearts", R.drawable.nine_of_hearts))
        deckOfCards.add(Card("ten_of_hearts",  10, "hearts",R.drawable.ten_of_hearts))
        deckOfCards.add(Card("jack_of_hearts", 11, "hearts",R.drawable.jack_of_hearts, isFaceCard = true))
        deckOfCards.add(Card("queen_of_hearts",12, "hearts",R.drawable.queen_of_hearts, isFaceCard = true))
        deckOfCards.add(Card("king_of_hearts", 13, "hearts",R.drawable.king_of_hearts, isFaceCard = true))
    }
}





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


class Card (var name : String, var isFaceCard : Boolean = false) {
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
        // Här är jag! Försöker att skapa en loop som kör igenom spelet tills någon har 10pts.


        for (player in playerList){
            if (player.score != 5) {
                playBusdriver(player)
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




    fun playBusdriver(player : Player){
        var firstRow = activateFirstRow(player)


    }


    fun activateFirstRow(player : Player) : Boolean{
        var failedRow = false
        var currentPlayer = player
        Toast.makeText(this,"${player.name}'s tur att spela!",Toast.LENGTH_LONG).show()
        var rowList = mutableListOf<ImageView>(imageViewList[0], imageViewList[1], imageViewList[2], imageViewList[3], imageViewList[4])

            imageViewList[0].setOnClickListener { changeBackgroundHelperMethod(imageViewList[0])
                disableClickRow(rowList)
                if (!cardsInPyramid[0].isFaceCard) {
                    activateSecondRow(currentPlayer)
                } else lostGame(currentPlayer)

            }
            imageViewList[1].setOnClickListener { changeBackgroundHelperMethod(imageViewList[1])
                disableClickRow(rowList)
                if (!cardsInPyramid[0].isFaceCard) activateSecondRow(currentPlayer) else lostGame(currentPlayer)
            }
            imageViewList[2].setOnClickListener { changeBackgroundHelperMethod(imageViewList[2])
                disableClickRow( rowList)
                if (!cardsInPyramid[0].isFaceCard) activateSecondRow(currentPlayer) else lostGame(currentPlayer)
            }
            imageViewList[3].setOnClickListener { changeBackgroundHelperMethod(imageViewList[3])
                disableClickRow(rowList)
                if (!cardsInPyramid[0].isFaceCard) activateSecondRow(currentPlayer) else lostGame(currentPlayer)
            }
            imageViewList[4].setOnClickListener { changeBackgroundHelperMethod(imageViewList[4])
                disableClickRow(rowList)
                if (!cardsInPyramid[0].isFaceCard) activateSecondRow(currentPlayer) else lostGame(currentPlayer)
            }
    return failedRow
    }


    fun activateSecondRow(player : Player) {
        var currentPlayer = player
        var rowList = mutableListOf<ImageView>(imageViewList[5], imageViewList[6], imageViewList[7], imageViewList[8])

        imageViewList[5].setOnClickListener { changeBackgroundHelperMethod(imageViewList[5])
            disableClickRow(rowList)
            if (!cardsInPyramid[1].isFaceCard) activateThirdRow(currentPlayer)else lostGame(currentPlayer)

        }
        imageViewList[6].setOnClickListener { changeBackgroundHelperMethod(imageViewList[6])
            disableClickRow(rowList)
            if (!cardsInPyramid[1].isFaceCard) activateThirdRow(currentPlayer) else lostGame(currentPlayer)
        }
        imageViewList[7].setOnClickListener { changeBackgroundHelperMethod(imageViewList[7])
            disableClickRow(rowList)
            if (!cardsInPyramid[1].isFaceCard) activateThirdRow(currentPlayer) else lostGame(currentPlayer)
        }
        imageViewList[8].setOnClickListener { changeBackgroundHelperMethod(imageViewList[8])
            disableClickRow(rowList)
            if (!cardsInPyramid[1].isFaceCard) activateThirdRow(currentPlayer) else lostGame(currentPlayer)

        }
    }

    fun activateThirdRow(player : Player) {
        var currentPlayer = player
        var rowList = mutableListOf<ImageView>(imageViewList[9], imageViewList[10], imageViewList[11])

        imageViewList[9].setOnClickListener { changeBackgroundHelperMethod(imageViewList[9])
            disableClickRow(rowList)
            if (!cardsInPyramid[2].isFaceCard) activateFourthRow(currentPlayer) else lostGame(currentPlayer)

        }
        imageViewList[10].setOnClickListener { changeBackgroundHelperMethod(imageViewList[10])
            if (!cardsInPyramid[2].isFaceCard){ activateFourthRow(currentPlayer)} else lostGame(currentPlayer)

        }
        imageViewList[11].setOnClickListener { changeBackgroundHelperMethod(imageViewList[11])
            disableClickRow( rowList)
            activateFourthRow(currentPlayer)
            if (!cardsInPyramid[2].isFaceCard) activateFourthRow(currentPlayer) else lostGame(currentPlayer)

        }
    }

    fun activateFourthRow(player : Player) {
        var currentPlayer = player
        var rowList = mutableListOf<ImageView>(imageViewList[12], imageViewList[13])

        imageViewList[12].setOnClickListener { changeBackgroundHelperMethod(imageViewList[12])
            disableClickRow(rowList)
            if (!cardsInPyramid[3].isFaceCard) activateFifthrow(currentPlayer) else lostGame(currentPlayer)
        }
        imageViewList[13].setOnClickListener { changeBackgroundHelperMethod(imageViewList[13])
            disableClickRow(rowList)
            if (!cardsInPyramid[3].isFaceCard) activateFifthrow(currentPlayer) else lostGame(currentPlayer)
        }

    }

    fun activateFifthrow(player : Player) {
        var currentPlayer = player
        var rowList = mutableListOf<ImageView>(imageViewList[14])

        imageViewList[14].setOnClickListener { changeBackgroundHelperMethod(imageViewList[14])
            disableClickRow(rowList)
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

        builder.setTitle("Vinst!")
        builder.setMessage("${player.name} tar hem 1 poäng!")
        builderHelper()
        player.score ++

        playerOneScore.text = "${player.name}: ${player.score}"
    }

    fun lostGame(player : Player) {
        builder.setTitle("Förlust!")
        builder.setMessage("${player.name} hamnade på ett klätt kort och får därför inga poäng denna omgången!")
        builder.setCancelable(false)
        builderHelper()


    }

    fun resetPictures() {
        for (image in imageViewList) {
            image.setImageResource(R.drawable.cardback)
        }
    }


    fun disableClickRow(imageViewList: List<ImageView>){
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




    fun changeBackgroundHelperMethod(imageView: ImageView){
        when (deckOfCards[0].name){
            "ace_of_spades" -> imageView.setImageResource(R.drawable.ace_of_spades)
            "two_of_spades" -> imageView.setImageResource(R.drawable.two_of_spades)
            "three_of_spades" -> imageView.setImageResource(R.drawable.three_of_spades)
            "four_of_spades" -> imageView.setImageResource(R.drawable.four_of_spades)
            "five_of_spades" -> imageView.setImageResource(R.drawable.five_of_spades)
            "six_of_spades" -> imageView.setImageResource(R.drawable.six_of_spades)
            "seven_of_spades" -> imageView.setImageResource(R.drawable.seven_of_spades)
            "eight_of_spades" -> imageView.setImageResource(R.drawable.eight_of_spades)
            "nine_of_spades" -> imageView.setImageResource(R.drawable.nine_of_spades)
            "ten_of_spades" -> imageView.setImageResource(R.drawable.ten_of_spades)
            "jack_of_spades" -> imageView.setImageResource(R.drawable.jack_of_spades)
            "queen_of_spades" -> imageView.setImageResource(R.drawable.queen_of_spades)
            "king_of_spades" -> imageView.setImageResource(R.drawable.king_of_spades)

            "ace_of_diamonds" -> imageView.setImageResource(R.drawable.ace_of_diamonds)
            "two_of_diamonds" -> imageView.setImageResource(R.drawable.two_of_diamonds)
            "three_of_diamonds" -> imageView.setImageResource(R.drawable.three_of_diamonds)
            "four_of_diamonds" -> imageView.setImageResource(R.drawable.four_of_diamonds)
            "five_of_diamonds" -> imageView.setImageResource(R.drawable.five_of_diamonds)
            "six_of_diamonds" -> imageView.setImageResource(R.drawable.six_of_diamonds)
            "seven_of_diamonds" -> imageView.setImageResource(R.drawable.seven_of_diamonds)
            "eight_of_diamonds" -> imageView.setImageResource(R.drawable.eight_of_diamonds)
            "nine_of_diamonds" -> imageView.setImageResource(R.drawable.nine_of_diamonds)
            "ten_of_diamonds" -> imageView.setImageResource(R.drawable.ten_of_diamonds)
            "jack_of_diamonds" -> imageView.setImageResource(R.drawable.jack_of_diamonds)
            "queen_of_diamonds" -> imageView.setImageResource(R.drawable.queen_of_diamonds)
            "king_of_diamonds" -> imageView.setImageResource(R.drawable.king_of_diamonds)

            "ace_of_hearts" -> imageView.setImageResource(R.drawable.ace_of_hearts)
            "two_of_hearts" -> imageView.setImageResource(R.drawable.two_of_hearts)
            "three_of_hearts" -> imageView.setImageResource(R.drawable.three_of_hearts)
            "four_of_hearts" -> imageView.setImageResource(R.drawable.four_of_hearts)
            "five_of_hearts" -> imageView.setImageResource(R.drawable.five_of_hearts)
            "six_of_hearts" -> imageView.setImageResource(R.drawable.six_of_hearts)
            "seven_of_hearts" -> imageView.setImageResource(R.drawable.seven_of_hearts)
            "eight_of_hearts" -> imageView.setImageResource(R.drawable.eight_of_hearts)
            "nine_of_hearts" -> imageView.setImageResource(R.drawable.nine_of_hearts)
            "ten_of_hearts" -> imageView.setImageResource(R.drawable.ten_of_hearts)
            "jack_of_hearts" -> imageView.setImageResource(R.drawable.jack_of_hearts)
            "queen_of_hearts" -> imageView.setImageResource(R.drawable.queen_of_hearts)
            "king_of_hearts" -> imageView.setImageResource(R.drawable.king_of_hearts)

            "ace_of_clubs" -> imageView.setImageResource(R.drawable.ace_of_clubs)
            "two_of_clubs" -> imageView.setImageResource(R.drawable.two_of_clubs)
            "three_of_clubs" -> imageView.setImageResource(R.drawable.three_of_clubs)
            "four_of_clubs" -> imageView.setImageResource(R.drawable.four_of_clubs)
            "five_of_clubs" -> imageView.setImageResource(R.drawable.five_of_clubs)
            "six_of_clubs" -> imageView.setImageResource(R.drawable.six_of_clubs)
            "seven_of_clubs" -> imageView.setImageResource(R.drawable.seven_of_clubs)
            "eight_of_clubs" -> imageView.setImageResource(R.drawable.eight_of_clubs)
            "nine_of_clubs" -> imageView.setImageResource(R.drawable.nine_of_clubs)
            "ten_of_clubs" -> imageView.setImageResource(R.drawable.ten_of_clubs)
            "jack_of_clubs" -> imageView.setImageResource(R.drawable.jack_of_clubs)
            "queen_of_clubs" -> imageView.setImageResource(R.drawable.queen_of_clubs)
            "king_of_clubs" -> imageView.setImageResource(R.drawable.king_of_clubs)
        }
        cardsInPyramid.add(deckOfCards[0])
        deckOfCards.removeAt(0)
    }

    fun initiateDeckOfCards () {

        //Spader

        deckOfCards.add(Card("ace_of_spades"))
        deckOfCards.add(Card("two_of_spades"))
        deckOfCards.add(Card("three_of_spades"))
        deckOfCards.add(Card("four_of_spades"))
        deckOfCards.add(Card("five_of_spades"))
        deckOfCards.add(Card("six_of_spades"))
        deckOfCards.add(Card("seven_of_spades"))
        deckOfCards.add(Card("eight_of_spades"))
        deckOfCards.add(Card("nine_of_spades"))
        deckOfCards.add(Card("ten_of_spades"))
        deckOfCards.add(Card("jack_of_spades", isFaceCard = true))
        deckOfCards.add(Card("queen_of_spades", isFaceCard = true))
        deckOfCards.add(Card("king_of_spades", isFaceCard = true))

        //Hjärter
        deckOfCards.add(Card("ace_of_hearts"))
        deckOfCards.add(Card("two_of_hearts"))
        deckOfCards.add(Card("three_of_hearts"))
        deckOfCards.add(Card("four_of_hearts"))
        deckOfCards.add(Card("five_of_hearts"))
        deckOfCards.add(Card("six_of_hearts"))
        deckOfCards.add(Card("seven_of_hearts"))
        deckOfCards.add(Card("eight_of_hearts"))
        deckOfCards.add(Card("nine_of_hearts"))
        deckOfCards.add(Card("ten_of_hearts"))
        deckOfCards.add(Card("jack_of_hearts", isFaceCard = true))
        deckOfCards.add(Card("queen_of_hearts", isFaceCard = true))
        deckOfCards.add(Card("king_of_hearts", isFaceCard = true))

        //Klöver

        deckOfCards.add(Card("ace_of_clubs"))
        deckOfCards.add(Card("two_of_clubs"))
        deckOfCards.add(Card("three_of_clubs"))
        deckOfCards.add(Card("four_of_clubs"))
        deckOfCards.add(Card("five_of_clubs"))
        deckOfCards.add(Card("six_of_clubs"))
        deckOfCards.add(Card("seven_of_clubs"))
        deckOfCards.add(Card("eight_of_clubs"))
        deckOfCards.add(Card("nine_of_clubs"))
        deckOfCards.add(Card("ten_of_clubs"))
        deckOfCards.add(Card("jack_of_clubs", isFaceCard = true))
        deckOfCards.add(Card("queen_of_clubs", isFaceCard = true))
        deckOfCards.add(Card("king_of_clubs", isFaceCard = true))

        //Ruter
        deckOfCards.add(Card("ace_of_diamonds"))
        deckOfCards.add(Card("two_of_diamonds"))
        deckOfCards.add(Card("three_of_diamonds"))
        deckOfCards.add(Card("four_of_diamonds"))
        deckOfCards.add(Card("five_of_diamonds"))
        deckOfCards.add(Card("six_of_diamonds"))
        deckOfCards.add(Card("seven_of_diamonds"))
        deckOfCards.add(Card("eight_of_diamonds"))
        deckOfCards.add(Card("nine_of_diamonds"))
        deckOfCards.add(Card("ten_of_diamonds"))
        deckOfCards.add(Card("jack_of_diamonds", isFaceCard = true))
        deckOfCards.add(Card("queen_of_diamonds", isFaceCard = true))
        deckOfCards.add(Card("king_of_diamonds", isFaceCard = true))

    }
}





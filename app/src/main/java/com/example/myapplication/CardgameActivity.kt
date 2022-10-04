package com.example.myapplication

import android.content.Intent
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

    var activePlayerIndex = 0
    var imageViewList = mutableListOf<ImageView>()
    var deckOfCards = mutableListOf<Card>()
    var cardsInPyramid = mutableListOf<Card>()
    var playerList = mutableListOf<Player>()


    lateinit var playerOneScore: TextView
    lateinit var playerTwoScore: TextView
    lateinit var playerThreeScore: TextView
    lateinit var playerFourScore: TextView
    lateinit var builder: AlertDialog.Builder


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cardgame)
        initiatePlayerScoreViews()
        amountOfPlayers()
        initiateDeckOfCards()
    }

    override fun onStart() {
        super.onStart()
        deckOfCards.shuffle()
        initiateCardsOnBoard() //Ta bort
        builder = AlertDialog.Builder(this)
        startGame()
    }


    // Kollar så att activeplayerindex inte är för stor för listan, att spelaren inte har 5 poäng, och sen aktiveras första raden kort.
    // Har spelaren 5 poäng avslutas spelet och användaren hamnar på startmenyn.

    fun startGame() {

        if (activePlayerIndex == playerList.size) {
            activePlayerIndex = 0
        }
        activateFirstRow()
}

    //Initierar de textviews som används för att visa poängställningen och knyter dem till
    //de olika objekten av spelare. Dessa sparas i listan playerList som nås över hela klassen.

    fun initiatePlayerScoreViews() {
        var player1Name = intent.getStringExtra("player1").toString()
        var player2Name = intent.getStringExtra("player2").toString()
        var player3Name = intent.getStringExtra("player3").toString()
        var player4Name = intent.getStringExtra("player4").toString()

        if (player1Name == ""){
            player1Name = getString(R.string.playerOneEmptyName)
        }
        if (player2Name == ""){
            player2Name = getString(R.string.playerTwoEmptyName)
        }
        if (player3Name == ""){
            player3Name = getString(R.string.playerThreeEmptyName)
        }
        if (player4Name == ""){
            player4Name = getString(R.string.playerFourEmptyName)
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
            4 -> {
                playerList.add(player1)
                playerList.add(player2)
                playerList.add(player3)
                playerList.add(player4)
            }
        }
    }

    // Tar intent från antal-spelare skärmen och gömmer de player-score textviews som inte ska spela.
    // Exempelvis om det är 2 spelare som ska spela så göms player 3 & 4
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

    // Skriver ut en text längst ned på skärmen som berättar vilken spelare ska spela (den aktiva spelaren i playerList)
    // rowList tar emot de fem första korten i kortlistan, som är hårdkodade att vara de första fem korten i den nedersta raden av pyramiden.
    // Aktiverar en clicklistener på samtliga kort, och på klick så körs metoden disableClickRow som innebär att alla click-listeners
    // på den raden stängs av, så att man bara kan välja ett kort per rad.

    fun activateFirstRow() {
        Toast.makeText(this,getString(R.string.newTurn, playerList[activePlayerIndex].name),Toast.LENGTH_SHORT).show()
        var rowList = mutableListOf<ImageView>(imageViewList[0], imageViewList[1], imageViewList[2], imageViewList[3], imageViewList[4])

            imageViewList[0].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[0])
                diableClickRow(rowList)
                if (!cardsInPyramid[0].isFaceCard) activateSecondRow() else lostRound()

            }
            imageViewList[1].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[1])
                diableClickRow(rowList)
                if (!cardsInPyramid[0].isFaceCard) activateSecondRow() else lostRound()
            }
            imageViewList[2].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[2])
                diableClickRow( rowList)
                if (!cardsInPyramid[0].isFaceCard) activateSecondRow() else lostRound()
            }
            imageViewList[3].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[3])
                diableClickRow(rowList)
                if (!cardsInPyramid[0].isFaceCard) activateSecondRow() else lostRound()
            }
            imageViewList[4].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[4])
                diableClickRow(rowList)
                if (!cardsInPyramid[0].isFaceCard) activateSecondRow() else lostRound()
            }
    }


    // Samma som activateFirstRow minus att skriva ut vem som spelar. Detta gäller för samtliga activate..Row
    // Denna kodupprepning blir det första som ändras i 2.0 versionen.

    fun activateSecondRow() {

        var rowList = mutableListOf<ImageView>(imageViewList[5], imageViewList[6], imageViewList[7], imageViewList[8])

        imageViewList[5].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[5])
            diableClickRow(rowList)
            if (!cardsInPyramid[1].isFaceCard) activateThirdRow()else lostRound()

        }
        imageViewList[6].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[6])
            diableClickRow(rowList)
            if (!cardsInPyramid[1].isFaceCard) activateThirdRow() else lostRound()
        }
        imageViewList[7].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[7])
            diableClickRow(rowList)
            if (!cardsInPyramid[1].isFaceCard) activateThirdRow() else lostRound()
        }
        imageViewList[8].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[8])
            diableClickRow(rowList)
            if (!cardsInPyramid[1].isFaceCard) activateThirdRow() else lostRound()

        }
    }


    // Samma som activateFirstRow minus att skriva ut vem som spelar. Detta gäller för samtliga activate..Row
    // Denna kodupprepning blir det första som ändras i 2.0 versionen.

    fun activateThirdRow() {

        var rowList = mutableListOf<ImageView>(imageViewList[9], imageViewList[10], imageViewList[11])

        imageViewList[9].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[9])
            diableClickRow(rowList)
            if (!cardsInPyramid[2].isFaceCard) activateFourthRow() else lostRound()

        }
        imageViewList[10].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[10])
            if (!cardsInPyramid[2].isFaceCard){ activateFourthRow()} else lostRound()

        }
        imageViewList[11].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[11])
            diableClickRow( rowList)
            activateFourthRow()
            if (!cardsInPyramid[2].isFaceCard) activateFourthRow() else lostRound()

        }
    }


    // Samma som activateFirstRow minus att skriva ut vem som spelar. Detta gäller för samtliga activate..Row
    // Denna kodupprepning blir det första som ändras i 2.0 versionen.

    fun activateFourthRow() {

        var rowList = mutableListOf<ImageView>(imageViewList[12], imageViewList[13])

        imageViewList[12].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[12])
            diableClickRow(rowList)
            if (!cardsInPyramid[3].isFaceCard) activateFifthrow(playerList[activePlayerIndex]) else lostRound()
        }
        imageViewList[13].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[13])
            diableClickRow(rowList)
            if (!cardsInPyramid[3].isFaceCard) activateFifthrow(playerList[activePlayerIndex]) else lostRound()
        }
    }

    // Samma som activateFirstRow minus att skriva ut vem som spelar. Detta gäller för samtliga activate..Row
    // Kör wonRound eller lostRound beroende på om sista kortet i pyramiden är ett klätt kort.
    // Denna kodupprepning blir det första som ändras i 2.0 versionen.


    fun activateFifthrow(player : Player) {
        var currentPlayer = player
        var rowList = mutableListOf<ImageView>(imageViewList[14])

        imageViewList[14].setOnClickListener { changeCardBackgroundHelperMethod(imageViewList[14])
            diableClickRow(rowList)
            if (!cardsInPyramid[4].isFaceCard) wonRound() else lostRound()
        }
    }

    //Flyttar två instanser av samma kod till en funktion, används för att öppna en messagebox som bara går att stänga
    //när användaren klickar på ok. På klick resetas korten i kortleken, i pyramiden,-
    // nya kort initieras & blandas och därefter knyts korten till de på bordet
    // If-sats som kollar att playerindex inte når längre än listans längd, och om -
    // den är på sista spelaren i listan går turen tillbaka till första spelaren.

    // Slutligen görs en check om spelet är slut genom en funktion som kollar den aktiva spelarens poäng.
    // Har spelaren 5 poäng avslutas spelet med en vinst-skärm annars körs ->

    // Kollar så att activeplayerindex inte är för stor för listan, att spelaren inte har 5 poäng, och sen aktiveras första raden kort.
    // Har spelaren 5 poäng avslutas spelet och användaren hamnar på startmenyn.

    fun builderHelper(){
        builder.setCancelable(false)
        builder.setPositiveButton("OK"){dialogInterface, it ->
            resetPictures()
            cardsInPyramid.removeAll(cardsInPyramid)
            deckOfCards.removeAll(deckOfCards)
            initiateDeckOfCards()
            deckOfCards.shuffle()
            initiateCardsOnBoard()
            if (playerList[activePlayerIndex].score != 5){
                activePlayerIndex++
                startGame()
            }
        }
        var isGameOver = wonGameCheck()
        if (!isGameOver) {
            builder.show()
        }
    }

    //Uppdaterar texten i poängtavlan
    fun updateScore() {
        playerList.size

        when  (playerList.size) {
            1 -> {
                playerOneScore.text = "${playerList[0].name}: ${playerList[0].score}"
            }
            2 -> {
                playerOneScore.text = "${playerList[0].name}: ${playerList[0].score}"
                playerTwoScore.text = "${playerList[1].name}: ${playerList[1].score}"
            }
            3 -> {
                playerOneScore.text = "${playerList[0].name}: ${playerList[0].score}"
                playerTwoScore.text = "${playerList[1].name}: ${playerList[1].score}"
                playerThreeScore.text = "${playerList[2].name}: ${playerList[2].score}"
            }
            4 -> {
                playerOneScore.text = "${playerList[0].name}: ${playerList[0].score}"
                playerTwoScore.text = "${playerList[1].name}: ${playerList[1].score}"
                playerThreeScore.text = "${playerList[2].name}: ${playerList[2].score}"
                playerFourScore.text = "${playerList[3].name}: ${playerList[3].score}"
            }
        }




    }

    //Kollar om en spelare har fem poäng och skickar ett vinst-meddelande vid true.
    //Returnerar en bool som används för att spelet ska köra på med ett annat meddelande-
    fun wonGameCheck() : Boolean{

        if (playerList[activePlayerIndex].score == 5){

            builder.setTitle(getString(R.string.gameWinnerTitle))
            builder.setMessage(getString(R.string.gameWinnerText,  playerList[activePlayerIndex].name))
            builder.setCancelable(false)

            builder.setPositiveButton("OK"){dialogInterface, it ->
               mainMenu()
            }
            builder.show()
        }
        return false
    }

    //tar bort alla activities och skickar tillbaka användaren till huvudmenyn
    fun mainMenu(){
        intent = Intent(this, MainActivity::class.java)
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP )
        startActivity(intent)
    }

    //Vinstmeddelande när spelaren klarar pyramiden, samt uppdaterar scoreboard
    fun wonRound() {
        builder.setTitle(getString(R.string.winTitle))
        builder.setMessage(getString(R.string.winText, playerList[activePlayerIndex].name))
        playerList[activePlayerIndex].score ++
        updateScore()
        builderHelper()
    }

    //Förlustmeddeladne när spelaren inte klarar pyramiden.
    fun lostRound() {
        builder.setTitle(getString(R.string.lossTitle))
        builder.setMessage(getString(R.string.lossText, playerList[activePlayerIndex].name))
        builder.setCancelable(false)
        builderHelper()
    }

    //Byter tillbaka bilden på alla kort till baksidan av ett kort.
    fun resetPictures() {
        for (image in imageViewList) {
            image.setImageResource(R.drawable.cardback)
        }
    }

    //Stänger av klickbarhet för klicklisteners
    fun diableClickRow(imageViewList: List<ImageView>){
           for (view in imageViewList){
               view.isClickable = false
           }
    }

    //Initierar de imageviews som avnänds för korten i spelet.
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

    //Hjälpmetod som vid klick på ett kort byter bilden till samma som det översta kortet i kortleken.
    //Därefter läggs kortet in i listan cardsInPyramid som håller koll på korten i pyramiden,
    //samt tar bort det översta kortet i kortleken från kortleken.
    fun changeCardBackgroundHelperMethod(imageView: ImageView){
        imageView.setImageResource(deckOfCards[0].image)
        cardsInPyramid.add(deckOfCards[0])
        deckOfCards.removeAt(0)
    }


    //Lägger till alla kort i en kortlista med namn och bilder, av klassen "Card"
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





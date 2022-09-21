package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView


class Player (var name : String){

}


class Card (var name : String, var isFaceCard : Boolean = false) {
}



class CardgameActivity : AppCompatActivity() {

    var imageViewList = mutableListOf<ImageView>()
    var deckOfCards = mutableListOf<Card>()

    lateinit var playerOneScore : TextView
    lateinit var playerTwoScore : TextView
    lateinit var playerThreeScore : TextView
    lateinit var playerFourScore : TextView
    var scoreP1 = 0
    var scoreP2 = 0
    var scoreP3 = 0
    var scoreP4 = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cardgame_activity)
        initiatePlayerScoreViews()
        amountOfPlayers()
        initiateDeckOfCards()
        deckOfCards.shuffle()
        initiateCardsOnBoard()
        activateFirstRow()


    }

    //Initierar de textviews som används för att visa poängställningen och knyter dem till
    //scoreP1-scoreP4 för att visa poängen
    fun initiatePlayerScoreViews() {

        playerOneScore = findViewById(R.id.counterOne)
        playerOneScore.text = scoreP1.toString()

        playerTwoScore = findViewById(R.id.counterTwo)
        playerTwoScore.text = scoreP2.toString()

        playerThreeScore = findViewById(R.id.counterThree)
        playerThreeScore.text = scoreP3.toString()

        playerFourScore = findViewById(R.id.counterFour)
        playerFourScore.text = scoreP4.toString()
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

    fun disableRemainingButtons() {

    }
    fun activateFirstRow() {
            imageViewList[0].setOnClickListener { changeBackgroundHelperMethod(imageViewList[0], deckOfCards) }
            imageViewList[1].setOnClickListener { changeBackgroundHelperMethod(imageViewList[1], deckOfCards) }
            imageViewList[2].setOnClickListener { changeBackgroundHelperMethod(imageViewList[2], deckOfCards) }
            imageViewList[3].setOnClickListener { changeBackgroundHelperMethod(imageViewList[3], deckOfCards) }
            imageViewList[4].setOnClickListener { changeBackgroundHelperMethod(imageViewList[4], deckOfCards) }
    }


    fun activateSecondRow() {

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




    fun changeBackgroundHelperMethod(imageView: ImageView, cardList: MutableList<Card>){
        when (cardList[0].name){
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
        cardList.removeAt(0)
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
        deckOfCards.add(Card("knight_of_spades", isFaceCard = true))
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
        deckOfCards.add(Card("knight_of_hearts", isFaceCard = true))
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
        deckOfCards.add(Card("knight_of_clubs", isFaceCard = true))
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
        deckOfCards.add(Card("knight_of_diamonds", isFaceCard = true))
        deckOfCards.add(Card("queen_of_diamonds", isFaceCard = true))
        deckOfCards.add(Card("king_of_diamonds", isFaceCard = true))


    }
}





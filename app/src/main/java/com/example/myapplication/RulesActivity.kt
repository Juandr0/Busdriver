package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class RulesActivity : AppCompatActivity() {

    lateinit var rulesTitleView : TextView
    lateinit var backButton : Button
    lateinit var rulesText : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rules_activity)

        rulesTitleView = findViewById(R.id.rulesTitleView)
        rulesText = findViewById(R.id.rulesText)
            /*
                "-Fem rader med kort läggs ut i en pyramidformad " +
                "uppställning med kortets baksida uppåt, och spelaren " +
                "ska därefter välja ett kort i den nedersta raden av " +
                "pyramiden. Om kortet inte är ett klätt kort (Knekt, " +
                "dam eller kung) väljer spelaren ett nytt kort på raden " +
                "ovanför, och fortsätter därefter tills dess att spelaren " +
                "stöter på ett klätt kort eller har tagit sig till toppen av " +
                "pyramiden.\n\n" +

                "-Om spelaren tar sig förbi det översta kortet i " +
                "pyramiden utan att stöta på ett klätt kort belönas " +
                "denne med ett poäng. Först till 5 poäng vinner " +
                "matchen ifall det är flera spelare.\n\n" +

                "-En spelare spelar åt gången"*/

        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()


        }
    }


}
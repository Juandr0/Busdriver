package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class RulesActivity : AppCompatActivity() {

    private lateinit var rulesTitleView: TextView
    private lateinit var backButton: Button
    private lateinit var rulesText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)

        rulesTitleView = findViewById(R.id.rulesTitleView)
        rulesText = findViewById(R.id.rulesText)
        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()


        }
    }


}
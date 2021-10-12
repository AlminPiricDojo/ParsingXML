package com.example.parsingxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var tvMain: TextView
    private lateinit var games: List<Game>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMain = findViewById(R.id.tvMain)

        try{
            val parser = MyXmlPullParserHandler()
            val iStream = assets.open("games.xml")
            games = parser.parse(iStream)

            var text = ""
            for(game in games){
                text += "${game.name} - ${game.rating}\n"
            }
            tvMain.text = text
        }catch (e: IOException) {
            println("ISSUE: $e")
        }
    }
}
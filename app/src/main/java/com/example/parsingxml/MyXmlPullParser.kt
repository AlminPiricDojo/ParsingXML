package com.example.parsingxml

import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class MyXmlPullParserHandler {
    private val games = ArrayList<Game>()
    private var text: String? = null

    private var gameName = ""
    private var gameRating = 0

    fun parse(inputStream: InputStream): List<Game> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("name", ignoreCase = true) -> {
                            gameName = text.toString()
                        }
                        tagName.equals("rating", ignoreCase = true) -> {
                            gameRating = text!!.toInt()
                        }
                        else -> games.add(Game(gameName, gameRating))
                    }

                    else -> {
                    }
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return games
    }
}
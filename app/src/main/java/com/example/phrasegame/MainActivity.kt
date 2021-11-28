package com.example.phrasegame

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var rvMain : RecyclerView
    private lateinit var btnGuess : Button
    private lateinit var userGuess : EditText
    private lateinit var tvMain : TextView
    private val words =
        arrayOf("Angry Elephant","Elephant Angry","Pinch Baby","Baby Pinch","Fish Reach","Reach Fish","Ball Flick","Flick Ball"
        ,"Remote Baseball","Baseball Remote","Football Roll","Roll Football","Basketball Fork","Fork Basketball","Sad Bounce","Bounce Sad","Giggle Scissors","Scissors Giggle")
    private var theWinningWord =""
    private var guesses = ArrayList<String>()
    private var guesses2 = ArrayList<Int>()
    private var tries = 0
    private var char = ArrayList<Char>()
    var  changedWord = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tries = 4
        char = arrayListOf()
        setContentView(R.layout.activity_main)
        tvMain = findViewById(R.id.tvMain)
        rvMain = findViewById(R.id.rvMain)
        btnGuess = findViewById(R.id.btnGuess)
        userGuess = findViewById(R.id.etUserInput)
        rvMain.adapter = RVadapter(guesses,guesses2)
        rvMain.layoutManager = LinearLayoutManager(this)

        theWinningWord = words[Random.nextInt(0,words.size)]
       val replace = replaceString(theWinningWord)
         changedWord = replace

        tvMain.text =  replace
        btnGuess.setOnClickListener {
            checkTheUserInput(userGuess.text.toString())
            guesses.add("$tries guesses remaining")
            guesses2.add(2)
            rvMain.adapter?.notifyDataSetChanged()
            userGuess.hint = "Guess a letter"
            userGuess.text.clear()
        }
    }

    private fun replaceString(word: String): String {
        println(word)
        val re = Regex("[^\\s.]")
        return re.replace(word, "★")

    }
     private fun checkTheUserInput(input: String) {
         var count = 0
         if (input.equals(theWinningWord, true)) {
             // win / end game
             changedWord = theWinningWord
             tvMain.text = changedWord


             println("jff")
         } else if (theWinningWord.contains(input, true)) {
             val inputChar = input.toCharArray()[0].lowercaseChar()
             val index = theWinningWord.lowercase().withIndex().filter{ it.value == inputChar }.map{ it.index }
             char.add(inputChar.lowercaseChar())

             for (j in index) {
                 changedWord = changedWord.replaceRange(j , j+1 ,theWinningWord[j].toString())
             }

             tvMain.text = changedWord
             guesses.add("Found ${index.size} ${input.uppercase()}(s)")
             guesses2.add(1)
         }else {
             guesses.add("Wrong Guess $input")
             guesses2.add(0)
         }
         tries--
     }
    fun checkPhraseComplete(){
        if (!changedWord.contains("★", true)){
            //the phrase is completed
        }
    }
}
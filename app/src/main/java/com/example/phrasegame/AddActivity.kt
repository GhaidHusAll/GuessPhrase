package com.example.phrasegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    private lateinit var btnAdd : Button
    private lateinit var userAdd : EditText
    private val helper by lazy { HelperDB(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        btnAdd = findViewById(R.id.btnAdd)
        userAdd = findViewById(R.id.etPhrase)
        btnAdd.setOnClickListener {
            if (userAdd.text.isNotEmpty()){
               val isSave = helper.add(userAdd.text.toString())
                if (isSave >= 0){
                    Toast.makeText(this,"Successfully Added",Toast.LENGTH_LONG).show()
                    etPhrase.text.clear()
                }else{
                    Toast.makeText(this,"something went wrong while Adding",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"please fill the field",Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        val item = menu?.findItem(R.id.item)
        item?.title = "To Play"
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item -> {
                val toPlay = Intent(this,MainActivity::class.java)
                startActivity(toPlay)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
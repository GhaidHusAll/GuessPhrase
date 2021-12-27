package com.example.phrasegame

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class HelperDB(context: Context):SQLiteOpenHelper(context,"Phrase.db",null,1) {
    private val sqlDB: SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
      db?.execSQL("create table phrase(textPhrase Text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun add(text:String):Long{
        val values = ContentValues()
        values.put("textPhrase",text)
        return sqlDB.insert("phrase",null,values)
    }
    fun read(): ArrayList<String>{
        val list = arrayListOf<String>()
        val dataFromDB: Cursor = sqlDB.rawQuery("SELECT * FROM phrase",null)
        if (dataFromDB.count < 1){
            Log.d("HelperDB","no data")
        }else{
            while (dataFromDB.moveToNext()){
                list.add(dataFromDB.getString(0))
            }
        }
        return list
    }
}
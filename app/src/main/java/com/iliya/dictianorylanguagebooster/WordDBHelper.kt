package com.iliya.dictianorylanguagebooster

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class WordDBHelper(context: Context) :
    SQLiteOpenHelper(context, database_name, null, database_version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_QUERY)
        onCreate(db)
    }

    fun insertWord(word: Word): Boolean {
        return try {
            val db = writableDatabase
            val valuse = ContentValues()
            valuse.put(DBContract.WordEntity.COLUMN_KEYWORD, word.keyword)
            valuse.put(DBContract.WordEntity.COLUMN_MEANING, word.meaning)
            valuse.put(DBContract.WordEntity.COLUMN_STAR, if (word.star) 1 else 0)
            db.insert(DBContract.WordEntity.TABLE_NAME, null, valuse)
            true
        } catch (e: Exception) {
            false
        }
    }

    @SuppressLint("Range")
    fun getAllWords(): ArrayList<Word> {
        val words = ArrayList<Word>()
        val db = readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM ${DBContract.WordEntity.TABLE_NAME}", null)
        } catch (e: Exception) {
            db.execSQL(SQL_CREATE_QUERY)
            return ArrayList()
        }
        var keyword: String
        var meaning: String
        var stared: Boolean
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                keyword =
                    cursor.getString(cursor.getColumnIndex(DBContract.WordEntity.COLUMN_KEYWORD))
                meaning =
                    cursor.getString(cursor.getColumnIndex(DBContract.WordEntity.COLUMN_MEANING))
                stared =
                    cursor.getInt(cursor.getColumnIndex(DBContract.WordEntity.COLUMN_STAR)) == 1
                words.add(Word(keyword, meaning, stared))
                cursor.moveToNext()
            }
        }
        return words
    }

    fun updateWord(word: Word): Boolean {
        return try {
            val db = writableDatabase
            val valuse = ContentValues()
            valuse.put(DBContract.WordEntity.COLUMN_MEANING, word.meaning)
            valuse.put(DBContract.WordEntity.COLUMN_STAR, if (word.star) 1 else 0)
            val where = "${DBContract.WordEntity.COLUMN_KEYWORD} = ?"
            val args = arrayOf(word.keyword)
            db.update(DBContract.WordEntity.TABLE_NAME, valuse, where, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    companion object {
        const val database_version = 1
        const val database_name = "LanguageBooster.db"
        private val SQL_CREATE_QUERY = "CREATE TABLE ${DBContract.WordEntity.TABLE_NAME}" +
                " (${DBContract.WordEntity.COLUMN_KEYWORD} TEXT PRIMARY KEY," +
                " ${DBContract.WordEntity.COLUMN_MEANING} TEXT ," +
                " ${DBContract.WordEntity.COLUMN_STAR} NUMERIC)"
        private val SQL_DELETE_QUERY = "DROP TABLE IF EXISTS ${DBContract.WordEntity.TABLE_NAME}"
    }

}
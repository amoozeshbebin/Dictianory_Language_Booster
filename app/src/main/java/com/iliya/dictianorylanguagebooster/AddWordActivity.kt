package com.iliya.dictianorylanguagebooster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddWordActivity : AppCompatActivity() {
    private lateinit var keyword:EditText
    private lateinit var meaning:EditText
    private lateinit var btn_add_word:Button
    private lateinit var wordDBHelper:WordDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)
        init()
    }
    private fun init(){
        bindViews()
        wordDBHelper = WordDBHelper(this)
        btn_add_word.setOnClickListener {
            if(keyword.text.isEmpty()){
                Toast.makeText(this,"Please Enter Word",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(meaning.text.isEmpty()){
                Toast.makeText(this,"Please Enter Meaning",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val word = Word(keyword.text.toString(),meaning.text.toString(),false)
            val result = wordDBHelper.insertWord(word)
            if(result){
                Toast.makeText(this,"New Word Added",Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"Error on adding New Word!!",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun bindViews(){
        keyword = findViewById(R.id.edittextword)
        meaning = findViewById(R.id.edittextmeaning)
        btn_add_word = findViewById(R.id.btn_add_word)
    }
}
package com.iliya.dictianorylanguagebooster

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import soup.neumorphism.NeumorphFloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: NeumorphFloatingActionButton
    private lateinit var adapter: RecyclerAdapter
    private lateinit var wordDBHelper: WordDBHelper
    private val listItems = ArrayList<Word>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        bindViews()
        wordDBHelper = WordDBHelper(this)
        adapter = RecyclerAdapter(this, listItems,wordDBHelper)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        fab.setOnClickListener {
            startActivity(Intent(this, AddWordActivity::class.java))
        }
    }

    fun bindViews() {
        recyclerView = findViewById(R.id.main_list)
        fab = findViewById(R.id.fab)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        listItems.clear()
        listItems.addAll(wordDBHelper.getAllWords())
        adapter.notifyDataSetChanged()
    }
}

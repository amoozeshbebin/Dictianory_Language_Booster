package com.iliya.dictianorylanguagebooster

object MockData {

    private val List = ArrayList<Word>()

    fun addWord(word: Word) {
        List.add(word)
    }

    fun getList(): ArrayList<Word> {
        return List
    }

}
package com.iliya.dictianorylanguagebooster

import android.provider.BaseColumns

object DBContract {
    class WordEntity : BaseColumns {
        companion object{
            val TABLE_NAME = "words"
            val COLUMN_KEYWORD = "keyword"
            val COLUMN_MEANING = "meaning"
            val COLUMN_STAR = "star"
        }
    }
}
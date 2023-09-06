package com.iliya.dictianorylanguagebooster

import android.content.Context
import android.provider.UserDictionary.Words
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import soup.neumorphism.NeumorphImageButton
import soup.neumorphism.NeumorphImageView

class RecyclerAdapter(private val contex:Context,private val words: ArrayList<Word>,private val wordDBHelper: WordDBHelper) :
    RecyclerView.Adapter<RecyclerAdapter.wordHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): wordHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_layout, parent, false)
        return wordHolder(inflater)
    }

    override fun onBindViewHolder(holder: wordHolder, position: Int) {
        val item =  words[position]
        holder.keyword.text = "${item.keyword}:${item.meaning}"
        changestar(holder, item)
        if(position == words.size -1 ){
            holder.border.visibility =View.GONE
        }
        holder.star.setOnClickListener{
            item.star = !item.star
            wordDBHelper.updateWord(item)
            changestar(holder, item)
        }

        Glide
            .with(contex)
            .load("https://picsum.photos/70?rand="+System.currentTimeMillis())
            .centerCrop()
            .placeholder(R.drawable.rwerwe)
            .into(holder.img);
    }

    private fun changestar(
        holder: wordHolder,
        item: Word
    ) {
        holder.star.setImageResource(
            if (item.star) R.drawable.baseline_star_24
            else R.drawable.baseline_star_border_24
        )
    }

    override fun getItemCount(): Int {
        return words.size
    }

    class wordHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        var keyword: TextView = v.findViewById(R.id.text_view)
        var star: NeumorphImageButton = v.findViewById(R.id.starbtn)
        var img: NeumorphImageView = v.findViewById(R.id.img)
        var border: View = v.findViewById(R.id.border)

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }

    }
}
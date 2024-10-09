package com.example.platformexcel.recycler

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.platformexcel.MainActivity
import com.example.platformexcel.R
import com.example.platformexcel.update.Update
import com.example.platformexcel.viewmodel.MainViewModel

class MainRecycler(private val a: Activity, private val list: List<String>) :
    RecyclerView.Adapter<MainRecycler.MainViewHolder>() {
    class MainViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val textView: TextView = v.findViewById(R.id.text_list)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = a.layoutInflater.inflate(R.layout.list_item, parent, false)
        return MainViewHolder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.textView.text = list[position].toString()
        val viwModel = ViewModelProvider(a as MainActivity)[MainViewModel::class.java]
        viwModel.getAll().observe(a as MainActivity) { users ->
           users.map { it.name }
            holder.itemView.setOnClickListener {
                val i = Intent(a, Update::class.java)
                i.putExtra("user",users[position])
                    a.startActivity(i)
            }
        }
    }
}
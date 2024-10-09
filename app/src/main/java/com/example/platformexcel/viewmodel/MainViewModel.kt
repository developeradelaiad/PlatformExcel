package com.example.platformexcel.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.platformexcel.dp.PlatFormDB
import com.example.platformexcel.dp.Platform

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val dp = PlatFormDB.getDatabase(app)

    fun upsert(name: Platform) = dp.dao().upsert(name)
    fun delete(name: Platform) = dp.dao().delete(name)
    fun getAll() = dp.dao().getAll()
    fun deleteAll() = dp.dao().deleteAll()
}
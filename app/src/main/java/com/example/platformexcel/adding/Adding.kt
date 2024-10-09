package com.example.platformexcel.adding

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.platformexcel.MainActivity
import com.example.platformexcel.R
import com.example.platformexcel.databinding.ActivityAddingBinding
import com.example.platformexcel.dp.Platform
import com.example.platformexcel.viewmodel.MainViewModel

class Adding : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddingBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.addButton.setOnClickListener {
            val details = binding.addTxet.text.toString()
            viewModel.upsert(Platform(name =details))
            binding.addTxet.text?.clear()
            Toast.makeText(this, "saved $details", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
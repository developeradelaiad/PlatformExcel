package com.example.platformexcel

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.platformexcel.adding.Adding
import com.example.platformexcel.databinding.ActivityMainBinding
import com.example.platformexcel.language.LanguageActivity
import com.example.platformexcel.recycler.MainRecycler
import com.example.platformexcel.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getAll().observe(this) { user ->
            val details = user.map { it.name }

            val adapter = MainRecycler(this, details)
            binding.viewRecy.adapter = adapter
        }
        binding.addButton.setOnClickListener {
            startActivity(Intent(this, Adding::class.java))
        }
        binding.topAppBar.setOnMenuItemClickListener {

            when (it.itemId) {
                R.id.delete_all -> {
                    onCreateDialog()
                    true
                }

                R.id.language_item -> {
                    startActivity(Intent(this, LanguageActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }

    private fun onCreateDialog() {
        AlertDialog.Builder(this)
            .setTitle("DeleteAll")
            .setMessage("Do You Want To Delete All!")
            .setPositiveButton("Yes") { _, _ -> viewModel.deleteAll() }
            .setNegativeButton("no") { dialog, _ -> dialog.cancel() }
            .show()
    }
}
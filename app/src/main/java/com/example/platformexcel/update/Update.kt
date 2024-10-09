package com.example.platformexcel.update

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.platformexcel.R
import com.example.platformexcel.databinding.ActivityUpdateBinding
import com.example.platformexcel.dp.Platform
import com.example.platformexcel.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class Update : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityUpdateBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val recivedUser =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                intent.getParcelableExtra("user", Platform::class.java)!!
            else
                intent.getParcelableExtra("user")!!

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.updateText.setText(recivedUser.name)


        binding.updateButton.setOnClickListener {
            val newDetails = binding.updateText.text.toString()
            val u = Platform(recivedUser.idUsers, newDetails)
            viewModel.upsert(u)
            Toast.makeText(this, "Updated $newDetails", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.deleteButton.setOnClickListener {
            Snackbar.make(binding.root, "Are You Sure", Snackbar.LENGTH_LONG)
                .setAction("yes") {
                    viewModel.delete(recivedUser)
                    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                    binding.deleteButton.isEnabled = false
                    binding.updateButton.isEnabled = false
                    binding.updateText.isEnabled = false
                    finish()
                }
                .setText("Do you Want To Delete")
                .setBackgroundTint(getColor(android.R.color.darker_gray))
                .setActionTextColor(getColor(android.R.color.holo_red_light))
                .show()

        }
    }
}
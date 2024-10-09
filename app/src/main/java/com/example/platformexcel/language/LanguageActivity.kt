package com.example.platformexcel.language

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.platformexcel.R
import com.example.platformexcel.databinding.ActivityLanguageBinding
import java.util.Locale

class LanguageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLanguageBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.languageGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.eng_btn -> local("en")
                R.id.arb_btn -> local("ar")
                R.id.fr_btn -> local("fr")
            }
            finish()
        }
    }
    private fun local(code:String){
        val local = Locale(code)
        val resource:Resources=resources
        val config :Configuration = resource.configuration
        config.setLocale(local)
        resource.updateConfiguration(config,resource.displayMetrics)

    }
}
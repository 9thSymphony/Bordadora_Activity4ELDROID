package com.bordadoraactivity4

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bordadoraactivity4.databinding.Activity4Binding

class Activity4 : AppCompatActivity() {
        private lateinit var sharedPreferences: SharedPreferences
        private lateinit var binding: Activity4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        loadStateFromPreferences ()

        binding.save.setOnClickListener {
            saveStatePreferences ()
            Toast.makeText(this, "Progress SAVED!", Toast.LENGTH_LONG).show()
        }
    }

    private fun saveStatePreferences () {
        val editor = sharedPreferences.edit()
        editor.putString("Email", binding.email.text.toString())
        editor.putString("Nickname", binding.nickname.text.toString())
        editor.putBoolean("Allow Notifiications",binding.pushnotif.isChecked)
        editor.putInt("SelectedTheme", binding.themes.checkedRadioButtonId)
        editor.apply()
    }

    private fun loadStateFromPreferences () {
        binding.email.setText(sharedPreferences.getString("Email", " "))
        binding.nickname.setText(sharedPreferences.getString("Nickname", " "))
        binding.pushnotif.isChecked = sharedPreferences.getBoolean("Allow Notifications", false)

        val themes = sharedPreferences.getInt("Selected Theme", -1)
        if (themes != -1) {
            binding.themes.check(themes)
        }
    }
}
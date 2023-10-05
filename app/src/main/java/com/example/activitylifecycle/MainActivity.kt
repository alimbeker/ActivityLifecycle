package com.example.activitylifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.activitylifecycle.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendBtn.setOnClickListener {
            val inputText = binding.timeForTimer.text.toString()
            if (!inputText.isNullOrBlank()) {
                val time = inputText.toInt()
                val intent = Intent(this, TimerActivity::class.java)
                intent.putExtra(Action.TIME.name, time)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Put time to timer", Toast.LENGTH_SHORT).show()
            }
        }


    }


    enum class Action {
        TIME
    }
}
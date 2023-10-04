package com.example.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.activitylifecycle.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    var running = false
    var seconds = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            pause.setOnClickListener {
                pauseClick()
            }
            start.setOnClickListener {
                startClick()
            }
            reset.setOnClickListener {
                resetClick()
            }
        }
        runTimer()
    }

    private fun runTimer() {
        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                val minutes = (seconds % 3600) / 60
                val time = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
                binding.timeView.text = time

                if (running) seconds++
                handler.postDelayed(this, 1000) // отправляет с задержкой
            }
        })
    }

    private fun pauseClick() {
        running = false
        Toast.makeText(this, "Pause Clicked", Toast.LENGTH_SHORT).show()
    }

    private fun startClick() {
        running = true
        Toast.makeText(this, "Start Clicked", Toast.LENGTH_SHORT).show()
    }

    private fun resetClick() {
        running = false
        seconds = 0
        Toast.makeText(this, "Reset Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
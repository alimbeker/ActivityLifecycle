package com.example.activitylifecycle

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.activitylifecycle.databinding.ActivityTimerBinding


class TimerActivity : AppCompatActivity() {
    private var countdownTimer: CountDownTimer? = null
    private lateinit var binding: ActivityTimerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val time = intent.getIntExtra(Argument.TIME.name, 0)
        // Timer settings
        countdownTimer = object : CountDownTimer((time * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val remainingSeconds = millisUntilFinished / 1000
                binding.textView.text = remainingSeconds.toString()
            }

            override fun onFinish() {
                binding.textView.text = "00:00"
            }
        }
        with(binding) {
            startButton.setOnClickListener {
                countdownTimer?.start()
            }

            pauseButton.setOnClickListener {
                countdownTimer?.cancel()
            }

            resetButton.setOnClickListener {
                countdownTimer?.cancel()
                textView.text = "00:00"
            }

        }

    }
}
enum class Argument {
    TIME
}

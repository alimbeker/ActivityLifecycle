package com.example.activitylifecycle

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
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
        timer(time)



        with(binding) {
            startButton.setOnClickListener {
                startTimer()
            }

            pauseButton.setOnClickListener {
                pauseTimer()
            }

            resetButton.setOnClickListener {
                resetTimer()
            }

        }



        // ACTION_SEND
        if (Intent.ACTION_SEND == intent.action) {
            val time = intent.getStringExtra(Intent.EXTRA_TEXT)
                timer(time!!.toInt())

        }

    }


    private fun startTimer() {
        countdownTimer?.start()
    }

    private fun pauseTimer() {
        countdownTimer?.cancel()
    }

    private fun resetTimer() {
        countdownTimer?.cancel()
        binding.textView.text = "00:00"
    }




    private fun timer(time : Int) {
        countdownTimer = object : CountDownTimer((time * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val totalSeconds = millisUntilFinished / 1000
                val minutes = totalSeconds / 60
                val seconds = totalSeconds % 60

                val formattedTime = String.format("%02d:%02d", minutes, seconds)
                binding.textView.text = formattedTime
            }

            override fun onFinish() {
                binding.textView.text = "00:00"
            }
        }
    }
}
enum class Argument {
    TIME
}

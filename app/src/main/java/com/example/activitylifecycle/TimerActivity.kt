package com.example.activitylifecycle

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.activitylifecycle.databinding.ActivityTimerBinding
import javax.sql.StatementEvent


class TimerActivity : AppCompatActivity() {
    private var countdownTimer: CountDownTimer? = null
    private lateinit var binding: ActivityTimerBinding
    private var seconds: Long = 0
    private var running: Boolean = false
    private var wasRunning: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)



        seconds = intent.getLongExtra(Argument.TIME.name, 0)
        binding.textView.text = toMinutes(seconds)




        // ACTION_SEND
        if (Intent.ACTION_SEND == intent.action) {
            val time = intent.getStringExtra(Intent.EXTRA_TEXT)
            binding.textView.text = toMinutes(time!!.toLong())


        }


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


                //if no null code is working
        savedInstanceState?.let {
            seconds = it.getLong(State.SECONDS.name)
            running = it.getBoolean(State.RUNNING.name)
            wasRunning = it.getBoolean(State.WASRUNNING.name)
            binding.textView.text = toMinutes(seconds)
        }

        if(running) {
            runTimer(seconds)
        }



    }



    private fun runTimer(time : Long) {

        countdownTimer = object : CountDownTimer((time * 1000), 1000) {

            override fun onTick(millisUntilFinished: Long) {

                binding.textView.text = toMinutes(millisUntilFinished / 1000)
                seconds--

            }

            override fun onFinish() {
                running = false
                seconds = 0
            }
        }.start()
    }


    private fun toMinutes(totalSeconds: Long) : String {
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60

        val formattedTime = String.format("%02d:%02d", minutes, seconds)

        return formattedTime
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(State.SECONDS.name,seconds)
        outState.putBoolean(State.RUNNING.name, running)
        outState.putBoolean(State.WASRUNNING.name, running)
        super.onSaveInstanceState(outState)
    }


    private fun startTimer() {
        running = true
        runTimer(seconds)
    }

    private fun pauseTimer() {
        running = false
        countdownTimer?.cancel()
    }

    private fun resetTimer() {
        running = false
        countdownTimer?.cancel()
        seconds = 0
        binding.textView.text = "00:00"
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        running = wasRunning
        super.onResume()
    }

    override fun onPause() {
        running = false
        super.onPause()
    }

    override fun onStop() {
        running = false
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    //When we come back from background, onRestart() called
    override fun onRestart() {
        super.onRestart()
    }


}

enum class State {
    SECONDS, RUNNING, WASRUNNING
}
enum class Argument {
    TIME
}

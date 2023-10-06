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

        binding.sayHelloBtn.setOnClickListener {
            val inputTime = binding.timeForTimer.text.toString()
            if (inputTime.isNotEmpty()) {
                val time = inputTime.toLong()
                val intent = Intent(this, TimerActivity::class.java)
                intent.putExtra(Argument.TIME.name, time)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Put time to the timer", Toast.LENGTH_SHORT).show()
            }
        }


        binding.sendBtn.setOnClickListener {
            val inputTime = binding.timeForTimer.text.toString()
            if (!inputTime.isNullOrEmpty()) {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(Intent.EXTRA_TEXT, inputTime)
                sendIntent.type = "text/plain"

                startActivity(Intent.createChooser(sendIntent, "Send Number"))
            } else {
                Toast.makeText(this, "Put time to the timer", Toast.LENGTH_SHORT).show()
            }
        }


    }


    enum class Argument {
        TIME
    }
}
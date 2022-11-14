package com.catata.timerswithcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.catata.timerswithcoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var timer1: Timer
    private lateinit var timer2: Timer

    private val btnNames : Map<String,String> by lazy { mapOf("start" to getString(R.string.start),"pause" to getString(R.string.pause), "resume" to getString(R.string.resume))}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        initTimers()

        setButtonsListeners()

    }

    private fun setButtonsListeners() {
        binding.btnTimer1.setOnClickListener {
            btnHandler(it, timer1)
        }

        binding.btnTimer2.setOnClickListener {
            btnHandler(it, timer2)
        }

        binding.btnStop1.setOnClickListener {
            timer1.stopTimer()
            binding.btnTimer1.text = btnNames["start"]
        }

        binding.btnStop2.setOnClickListener {
            timer2.stopTimer()
            binding.btnTimer2.text = btnNames["start"]
        }

        binding.btnResetAll.setOnClickListener {
            timer1.resetTimer()
            timer2.resetTimer()
        }
    }

    private fun btnHandler(btn: View, timer: Timer) {
        if(btn is Button){
            when(btn.text.toString().lowercase()) {
                btnNames["start"]?.lowercase() -> {
                    btn.text = btnNames["pause"]
                    timer.startTimer()
                }
                btnNames["pause"]?.lowercase() -> {
                    btn.text = btnNames["resume"]
                    timer.pauseTimer()
                }
                btnNames["resume"]?.lowercase() -> {
                    btn.text = btnNames["pause"]
                    timer.resumeTimer()
                }
            }

        }
    }

    private fun initTimers() {
        timer1 = Timer(){ counter1 ->
                binding.tvTimer1.text = counter1.toString()
        }

        timer2 = Timer(){ counter2 ->
            binding.tvTimer2.text = counter2.toString()
        }
    }
}
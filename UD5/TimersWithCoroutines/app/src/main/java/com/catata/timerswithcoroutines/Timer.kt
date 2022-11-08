package com.catata.timerswithcoroutines

import kotlinx.coroutines.*

class Timer(val callback: (Int)->Unit) {

    private var timer = 0

    private var _job: Job? = null

    private var status = Status.STOPPED

    fun startTimer(){
        status = Status.RUNNING
        _job = CoroutineScope(Dispatchers.Default).launch {
            runTimer()
        }
    }

    fun resetTimer(){
        timer = 0
        _job?.cancel()
        CoroutineScope(Dispatchers.IO).launch{
            sendData()
        }
    }

    fun pauseTimer(){
        status = Status.PAUSED
    }

    fun stopTimer(){
        _job?.cancel()
        timer = 0
        CoroutineScope(Dispatchers.IO).launch{
            sendData()
        }
    }

    fun resumeTimer(){
        when(status){
            Status.PAUSED -> { startTimer() }
            Status.STOPPED -> {}
            Status.RUNNING -> {}
        }

    }

    private suspend fun runTimer()= withContext(Dispatchers.Default){
        while(status == Status.RUNNING){
            delay(1000)
            timer++
            sendData()
        }
    }

    private suspend fun sendData()= withContext(Dispatchers.Main){
        callback(timer)
    }

    enum class Status{
        RUNNING, PAUSED, STOPPED
    }

}
package com.catata.gymnasticstrainer.model
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.catata.gymnasticstrainer.R
import kotlinx.coroutines.*

import java.util.*


typealias  OnOrder = (order:String) -> Unit

class Trainer {
    val orderLiveData:LiveData<String> = object: LiveData<String>(){
        override fun onActive() {
            super.onActive()
            startTraining {
                    order -> postValue(order)
            }
        }

        override fun onInactive() {
            super.onInactive()
            stopTraining()
        }
    }

    private val random:Random = Random()

    var training:Job? = null

    var exercise = 0
    var repetitions = -1

    fun startTraining(onOrder: OnOrder) {
        if (training == null || training!!.isCancelled || training!!.isCompleted) {
            training = CoroutineScope(Dispatchers.IO).launch {
                var ex: Exercise = Exercise.EXERCISE1
                while (true) {

                    if (repetitions < 0) { //when <0 change exercise
                        repetitions = random.nextInt(3) + 3 //between 3 and 5, both included
                        exercise = random.nextInt(Exercise.values().size -1) + 1  //between 1 and 4, both included
                        ex = Exercise.values()[exercise - 1]
                    }
                    Log.d("XXXX","${ex.name}:" + if (repetitions == 0) "CHANGE" else repetitions)
                    onOrder("${ex.name}:" + if (repetitions == 0) "CHANGE" else repetitions)
                    repetitions--

                    delay(1000)
                }

            }
        }
    }

    fun stopTraining() {
        training?.let {
            if(it.isActive)
                it.cancel()
        }
        exercise = 0
        repetitions = -1

    }


}


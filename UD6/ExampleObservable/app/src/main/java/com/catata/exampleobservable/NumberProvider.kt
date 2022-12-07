package com.catata.exampleobservable

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

object NumberProvider {

    val observable:Observable<List<String>> = Observable(emptyList())

    private var values = emptyList<String>()
    private var random = Random(System.currentTimeMillis())


    fun startEmitting(){
        CoroutineScope(Dispatchers.Main).launch {
            while(true){
                delay(1000)
                values = values + random.nextInt().toString()

                Log.d("EMITTING", values.toString())
                observable.update(values)
            }
        }
    }
}
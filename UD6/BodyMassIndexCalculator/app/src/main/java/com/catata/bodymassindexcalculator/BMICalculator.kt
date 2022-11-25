package com.catata.bodymassindexcalculator

import kotlin.math.pow

class BMICalculator{

    data class Request(
        val weight:Double,
        val height:Double
    )

    private fun calcMBI(weight: Double, height: Double):Double = weight/ (height/100).pow(2)

    fun calculate(request:Request):Double{

        Thread.sleep(5000)
        println(Thread.currentThread().name)

        return calcMBI(request.weight, request.height)

    }
}
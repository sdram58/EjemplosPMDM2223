package com.catata.bodymassindexcalculatorflow.ui.main

sealed class UIMainState(val isLoading:Boolean)
class WeightError(val error:String, private val loading:Boolean = false):UIMainState(loading)
class HeightError(val error:String, private val loading:Boolean = false):UIMainState(loading)
class ResultOk(val bmi:Double, private val loading:Boolean = false):UIMainState(loading)
object Loading:UIMainState(true)
object Loaded:UIMainState(false)
package com.catata.bodymassindexcalculatorflow.ui.main

sealed class UIMainState(val isLoading:Boolean)
class WeightError(val error:String, private val loading:Boolean = false):UIMainState(loading)
class HeightError(val error:String, private val loading:Boolean = false):UIMainState(loading)
class ResultOk(val bmi:Double? = null, private val loading:Boolean = false):UIMainState(loading)
class Loading(private val loading:Boolean):UIMainState(loading)
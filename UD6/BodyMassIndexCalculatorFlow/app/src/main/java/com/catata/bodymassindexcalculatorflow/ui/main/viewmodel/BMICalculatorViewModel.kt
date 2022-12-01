package com.catata.bodymassindexcalculatorflow.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.catata.bodymassindexcalculatorflow.model.BMICalculator
import com.catata.bodymassindexcalculatorflow.model.Request
import com.catata.bodymassindexcalculatorflow.ui.main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BMICalculatorViewModel: ViewModel() {

    private val bmiCalculator: BMICalculator = BMICalculator()


    private val _state:MutableStateFlow<UIMainState> = MutableStateFlow(Loaded)
    val state = _state.asStateFlow()



    //Choose which of the three methods you want to use
    fun calculateBMI(weight:Double,height:Double){

        //calculateBMIFunctions(weight,height)
        //calculateBMICallBack(weight,height)
        calculateBMISealed(weight,height)

    }

    /**********WITH FUNCTIONS *************************/
    private fun calculateBMIFunctions(weight: Double, height: Double) {
        //Using coroutine
        CoroutineScope(Dispatchers.IO).launch {
            //Using callbacks
            _state.value = Loading
            bmiCalculator.calculateWithFunctions(
                Request(weight, height),
                onSuccess = { mBMI->
                    _state.value = ResultOk(mBMI)
                },
                onWrongWeight = {
                    _state.value = WeightError(it)
                },
                onWrongHeight = {
                    _state.value = HeightError(it)
                }
            )

        }
    }

    /******************WITH CALLBACK*************************************/
    private fun calculateBMICallBack(weight: Double, height: Double) {


        CoroutineScope(Dispatchers.IO).launch {
            _state.value = Loading
            bmiCalculator.calculateWithCallback(
                Request(weight,height),
                object: BMICalculator.BMIResponse {
                    override fun onSuccess(result: Double) {
                        _state.value = ResultOk(bmi = result)
                    }

                    override fun onHeightError(error: String) {
                        _state.value = HeightError(error)
                    }

                    override fun onWeightError(error: String) {
                        _state.value = WeightError(error)
                    }

                    override fun onError(mError: String) {

                    }

                })


        }
    }


    /******************WITH SEALED CLASS***************************************/

    private fun calculateBMISealed(weight: Double, height: Double) {

        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            _state.value = Loading
            bmiCalculator.calculateWithSealed(Request(weight, height),null).also{ res ->
                when(res){
                    is BMICalculator.Response.OKResult -> {
                        _state.value = ResultOk(res.result)
                    }
                    is BMICalculator.Response.WrongHeight ->{
                        _state.value = HeightError(res.error)
                    }
                    is BMICalculator.Response.WrongWeight ->{
                        _state.value = WeightError(res.error)
                    }
                }
            }

        }


    }




}
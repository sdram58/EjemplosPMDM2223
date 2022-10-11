package com.catata.senderappoperation.model

data class Model(val num1:Double,val num2:Double,val operation: Operation) {
    enum class Operation(symbol:String){
        SUM("+"), SUBS("-"), MULT("x"), DIV("%")
    }
}
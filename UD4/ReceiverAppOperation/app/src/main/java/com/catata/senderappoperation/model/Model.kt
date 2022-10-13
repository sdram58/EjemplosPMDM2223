package com.catata.senderappoperation.model

import android.os.Parcel
import android.os.Parcelable

const val EXTRA_NUM1_KEY = "EXTRA_NUM1_KEY"
const val EXTRA_NUM2_KEY = "EXTRA_NUM2_KEY"
const val EXTRA_OP_KEY = "EXTRA_OP_KEY"
const val EXTRA_RESULT_KEY = "EXTRA_RESULT_KEY"
const val EXTRA_PARCELABLE_KEY = "EXTRA_PARCELABLE_KEY"

data class Model(val num1:Double,val num2:Double,val operation: Operation): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        when(parcel.readString()){
            Operation.ADD.symbol -> Operation.ADD
            Operation.SUBS.symbol -> Operation.SUBS
            Operation.MULT.symbol -> Operation.MULT
            Operation.DIV.symbol -> Operation.DIV
            else -> Operation.ADD
        }) {
    }

    enum class Operation(val symbol:String){
        ADD("+"), SUBS("-"), MULT("x"), DIV("%")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(num1)
        parcel.writeDouble(num2)
        parcel.writeString(operation.symbol)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Model> {
        override fun createFromParcel(parcel: Parcel): Model {
            return Model(parcel)
        }

        override fun newArray(size: Int): Array<Model?> {
            return arrayOfNulls(size)
        }
    }
}
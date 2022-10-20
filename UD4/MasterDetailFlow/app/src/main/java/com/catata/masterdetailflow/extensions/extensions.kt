package com.catata.masterdetailflow.extensions

import android.content.Context
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.catata.masterdetailflow.R

fun ImageView.setImageFromString(imageName:String){
    val imageStr = imageName.split(".")[0]
    val id = context.resources.getIdentifier(
        imageStr, //Something like "batman"
        "raw",
        context.packageName
    )

    val inputStream =context.resources.openRawResource(id)
    val image = BitmapFactory.decodeStream(inputStream)

    if(imageName==""){
        //this.setImageBitmap(R.raw.)
        //TODO set not found image
    }else{
        this.setImageBitmap(image)
    }

}
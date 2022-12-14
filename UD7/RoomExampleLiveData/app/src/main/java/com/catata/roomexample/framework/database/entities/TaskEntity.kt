package com.catata.roomexample.framework.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_entity")
class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var name:String = "",
    var isDone:Boolean = false
)
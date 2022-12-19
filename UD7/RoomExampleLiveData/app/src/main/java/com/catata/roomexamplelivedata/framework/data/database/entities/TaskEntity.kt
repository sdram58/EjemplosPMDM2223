package com.catata.roomexamplelivedata.framework.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_entity")
class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int = 0,

    @ColumnInfo(name="name")
    val name:String = "",

    @ColumnInfo(name="isDone")
    val isDone:Boolean = false
)
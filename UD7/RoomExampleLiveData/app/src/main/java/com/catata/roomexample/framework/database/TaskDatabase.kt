package com.catata.roomexample.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.catata.roomexample.framework.database.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object{ //Singleton Pattern
        private var instance: TaskDao? = null

        fun getInstance(context: Context): TaskDao {
            return instance ?: Room.databaseBuilder(context, TasksDatabase::class.java, "tasks-db").build().taskDao().also { instance = it }
        }
    }
}
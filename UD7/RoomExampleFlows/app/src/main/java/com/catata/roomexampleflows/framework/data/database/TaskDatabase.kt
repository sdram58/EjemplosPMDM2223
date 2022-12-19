package com.catata.roomexampleflows.framework.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.catata.roomexampleflows.framework.data.database.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object{ //Singleton Pattern
        private var instance: TaskDao? = null

        private const val DB_NAME="tasks-db"

        fun getInstance(context: Context): TaskDao {
            return instance ?: synchronized(this){
                Room.databaseBuilder(context,
                    TasksDatabase::class.java,
                    DB_NAME
                ).build().taskDao().also { instance = it }
            }
        }
    }
}
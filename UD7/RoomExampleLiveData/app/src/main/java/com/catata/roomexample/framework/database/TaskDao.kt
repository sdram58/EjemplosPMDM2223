package com.catata.roomexample.framework.database

import androidx.room.*
import com.catata.roomexample.data.datasources.LocalDataSource
import com.catata.roomexample.framework.database.entities.TaskEntity

@Dao
interface TaskDao: LocalDataSource {
    @Query("SELECT * FROM task_entity")
    override fun getAllTasks(): MutableList<TaskEntity>

    @Insert
    override fun addTask(taskEntity : TaskEntity):Long

    @Query("SELECT * FROM task_entity WHERE id LIKE :id")
    override fun getTaskById(id: Long): TaskEntity

    @Update
    override fun updateTask(taskEntity: TaskEntity):Int

    @Delete
    override fun deleteTask(taskEntity: TaskEntity):Int
}
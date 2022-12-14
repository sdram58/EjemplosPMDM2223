package com.catata.roomexample.data.datasources

import com.catata.roomexample.framework.database.entities.TaskEntity

interface LocalDataSource {
    fun getAllTasks(): MutableList<TaskEntity>

    fun addTask(taskEntity : TaskEntity):Long //Id of the new  task

    fun getTaskById(id: Long): TaskEntity

    fun updateTask(taskEntity: TaskEntity):Int //Number of affected rows

    fun deleteTask(taskEntity: TaskEntity):Int //Number of affected rows
}
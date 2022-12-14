package com.catata.roomexample.database

import com.catata.roomexample.database.entities.TaskEntity

interface MyDao {
    fun getAllTasks(): MutableList<TaskEntity>

    fun addTask(taskEntity : TaskEntity):Long //Id of the new  task

    fun getTaskById(id: Long): TaskEntity

    fun updateTask(taskEntity: TaskEntity):Int //Number of affected rows

    fun deleteTask(taskEntity: TaskEntity):Int //Number of affected rows
}
package com.catata.roomexampleflows.data.datasources

import com.catata.roomexampleflows.domain.Task
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getAllTasks(): Flow<List<Task>>

    suspend fun addTask(task : Task):Int//Id of the new  task

    suspend fun getTaskById(id: Int): Task

    suspend fun updateTask(task: Task):Int //Number of affected rows

    suspend fun deleteTask(task: Task):Int //Number of affected rows

    suspend fun deleteAll()
}
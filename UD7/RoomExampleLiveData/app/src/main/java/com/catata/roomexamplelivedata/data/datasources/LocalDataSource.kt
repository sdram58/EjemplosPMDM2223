package com.catata.roomexamplelivedata.data.datasources

import androidx.lifecycle.LiveData
import com.catata.roomexamplelivedata.domain.Task

interface LocalDataSource {
    suspend fun getAllTasks(): LiveData<List<Task>>

    suspend fun addTask(task : Task):Int//Id of the new  task

    suspend fun getTaskById(id: Int): Task

    suspend fun updateTask(task: Task):Int //Number of affected rows

    suspend fun deleteTask(task: Task):Int //Number of affected rows

    suspend fun deleteAll()
}
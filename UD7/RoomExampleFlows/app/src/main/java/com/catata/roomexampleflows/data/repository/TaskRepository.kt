package com.catata.roomexampleflows.data.repository

import androidx.lifecycle.LiveData
import com.catata.roomexampleflows.data.datasources.LocalDataSource
import com.catata.roomexampleflows.domain.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val localDataSource: LocalDataSource) {

    suspend fun getTasks(): Flow<List<Task>> {
        return localDataSource.getAllTasks()
    }

    suspend fun deleteTask(task: Task): Int{
        return localDataSource.deleteTask(task)
    }

    suspend fun addTask(task: Task): Int{
        return localDataSource.addTask(task)
    }

    suspend fun update(task: Task): Int{
        return localDataSource.updateTask(task)
    }

    suspend fun getTaskById(id: Int):Task{
        return localDataSource.getTaskById(id)
    }

    suspend fun deleteAll():Unit = localDataSource.deleteAll()
}
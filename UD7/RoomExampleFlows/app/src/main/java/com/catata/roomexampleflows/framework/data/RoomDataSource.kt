package com.catata.roomexampleflows.framework.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.catata.roomexampleflows.data.datasources.LocalDataSource
import com.catata.roomexampleflows.domain.Task
import com.catata.roomexampleflows.framework.data.database.TaskDao
import com.catata.roomexampleflows.framework.data.database.entities.TaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform

class RoomDataSource(private val taskDao: TaskDao):LocalDataSource {
    override suspend fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks().map { list->
            list?.map { it.mapperTask() } ?: emptyList()
        }
    }

    override suspend fun addTask(task: Task):Int {
        return taskDao.addTask(task.mapperTaskEntity()).toInt()
    }

    override suspend fun getTaskById(id: Int): Task {
        return taskDao.getTaskById(id).mapperTask()

    }

    override suspend fun updateTask(task: Task): Int {
        return taskDao.updateTask(task.mapperTaskEntity())
    }

    override suspend fun deleteTask(task: Task): Int{
        return taskDao.deleteTask(task.mapperTaskEntity())
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }


    private fun TaskEntity.mapperTask():Task{
        return Task(
            id = id,
            name = name,
            isDone = isDone
        )
    }

    private fun Task.mapperTaskEntity():TaskEntity{
        return TaskEntity(
            id = id,
            name = name,
            isDone = isDone
        )
    }



}
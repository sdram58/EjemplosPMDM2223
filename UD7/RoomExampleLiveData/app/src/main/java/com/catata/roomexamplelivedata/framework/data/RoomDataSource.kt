package com.catata.roomexamplelivedata.framework.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.catata.roomexamplelivedata.data.datasources.LocalDataSource
import com.catata.roomexamplelivedata.domain.Task
import com.catata.roomexamplelivedata.framework.data.database.TaskDao
import com.catata.roomexamplelivedata.framework.data.database.entities.TaskEntity

class RoomDataSource(private val taskDao: TaskDao):LocalDataSource {
    override suspend fun getAllTasks(): LiveData<List<Task>> {
        return Transformations.switchMap(taskDao.getAllTasks()){ taskEntityList ->
            var taskList = taskEntityList.map(){
                 it.mapperTask()
            }

            return@switchMap MutableLiveData(taskList)
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
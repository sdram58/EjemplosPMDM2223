package com.catata.roomexamplelivedata.framework.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.catata.roomexamplelivedata.data.repository.TaskRepository
import com.catata.roomexamplelivedata.domain.Task
import com.catata.roomexamplelivedata.framework.data.RoomDataSource
import com.catata.roomexamplelivedata.framework.data.database.TasksDatabase
import com.catata.roomexamplelivedata.usecases.*
import kotlinx.coroutines.launch


class TaskViewModel(application: Application): AndroidViewModel(application) {
    private val context = application

    //Initializing UseCases
    private val repository = TaskRepository(
        RoomDataSource(
            TasksDatabase.getInstance(context)
        )
    )
    val addTaskUseCase = AddTask(repository)
    val deleteTaskUseCase = DeleteTask(repository)
    val updateTaskUseCase = UpdateTask(repository)
    val getAllTaskUseCase = GetAllTask(repository)
    val getTaskByIdUse = GetTaskById(repository)
    val deleteAllTaskUseCase = DeleteAllTask(repository)

    //LIVE DATA
    val updateTaskLD:MutableLiveData<Task?> = MutableLiveData()
    val deleteTaskLD:MutableLiveData<Int> = MutableLiveData()




    suspend fun getAllTasks():LiveData<List<Task>> = getAllTaskUseCase()

    fun add(task:String) {
        viewModelScope.launch {
            addTaskUseCase(Task(name=task, isDone = false))
        }
    }

    fun delete(task: Task){
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            deleteAllTaskUseCase()
        }
    }

    fun update(task: Task){
        viewModelScope.launch {
            task.isDone = !task.isDone
            val res = updateTaskUseCase(task)
            if(res>0)
                updateTaskLD.postValue(task)
            else
                updateTaskLD.postValue(null)
        }
    }
}




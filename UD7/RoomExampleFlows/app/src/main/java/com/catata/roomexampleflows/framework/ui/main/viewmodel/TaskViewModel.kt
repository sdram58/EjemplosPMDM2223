package com.catata.roomexampleflows.framework.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.catata.roomexampleflows.data.repository.TaskRepository
import com.catata.roomexampleflows.domain.Task
import com.catata.roomexampleflows.framework.data.RoomDataSource
import com.catata.roomexampleflows.framework.data.database.TasksDatabase
import com.catata.roomexampleflows.usecases.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    val deleteAllTaskUseCase = DeleteAllTask(repository)

    //LIVE DATA
    private var _updateTaskStateFlow: MutableStateFlow<Task?> = MutableStateFlow(Task(name = "", isDone = false))
    val updateTaskStateFlow:StateFlow<Task?> = _updateTaskStateFlow.asStateFlow()

    private var _deleteTaskStateFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    val deleteTaskStateFlow:StateFlow<Int> = _deleteTaskStateFlow.asStateFlow()





    suspend fun getAllTasks(): Flow<List<Task>> = getAllTaskUseCase()

    fun add(task:String) {
        viewModelScope.launch {
            addTaskUseCase(Task(name=task, isDone = false))
        }
    }

    fun delete(task: Task){
        viewModelScope.launch {
            _deleteTaskStateFlow.value = deleteTaskUseCase(task).let {
                if (it > 0)
                    it
                else
                    -1
            }
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
                _updateTaskStateFlow.value = task
            else
                _updateTaskStateFlow.value = null
        }
    }
}




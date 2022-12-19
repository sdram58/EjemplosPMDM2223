package com.catata.roomexamplelivedata.usecases

import com.catata.roomexamplelivedata.data.repository.TaskRepository
import com.catata.roomexamplelivedata.domain.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteTask(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(task:Task): Int = withContext(Dispatchers.IO){
        taskRepository.deleteTask(task)
    }
}


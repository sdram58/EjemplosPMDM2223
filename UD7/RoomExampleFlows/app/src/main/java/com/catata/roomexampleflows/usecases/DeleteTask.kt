package com.catata.roomexampleflows.usecases

import com.catata.roomexampleflows.data.repository.TaskRepository
import com.catata.roomexampleflows.domain.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteTask(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(task:Task): Int = withContext(Dispatchers.IO){
        taskRepository.deleteTask(task)
    }
}


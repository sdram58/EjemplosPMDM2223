package com.catata.roomexampleflows.usecases

import com.catata.roomexampleflows.data.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteAllTask(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(): Unit = withContext(Dispatchers.IO){
        taskRepository.deleteAll()
    }
}
package com.catata.roomexamplelivedata.usecases

import com.catata.roomexamplelivedata.data.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteAllTask(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(): Unit = withContext(Dispatchers.IO){
        taskRepository.deleteAll()
    }
}
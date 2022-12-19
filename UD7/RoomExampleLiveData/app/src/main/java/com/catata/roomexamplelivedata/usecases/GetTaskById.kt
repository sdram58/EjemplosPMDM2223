package com.catata.roomexamplelivedata.usecases

import com.catata.roomexamplelivedata.data.repository.TaskRepository
import com.catata.roomexamplelivedata.domain.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetTaskById(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(id:Int): Task = withContext(Dispatchers.IO){
        taskRepository.getTaskById(id)
    }
}
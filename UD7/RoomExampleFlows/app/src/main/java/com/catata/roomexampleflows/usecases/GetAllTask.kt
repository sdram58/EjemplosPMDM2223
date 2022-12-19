package com.catata.roomexampleflows.usecases

import androidx.lifecycle.LiveData
import com.catata.roomexampleflows.data.repository.TaskRepository
import com.catata.roomexampleflows.domain.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetAllTask(private val taskRepository: TaskRepository) {
    suspend operator fun invoke():Flow<List<Task>> = withContext(Dispatchers.IO){
        taskRepository.getTasks()
    }
}


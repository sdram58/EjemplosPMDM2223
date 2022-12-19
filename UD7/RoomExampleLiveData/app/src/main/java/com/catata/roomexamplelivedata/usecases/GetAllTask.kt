package com.catata.roomexamplelivedata.usecases

import androidx.lifecycle.LiveData
import com.catata.roomexamplelivedata.data.repository.TaskRepository
import com.catata.roomexamplelivedata.domain.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllTask(private val taskRepository: TaskRepository) {
    suspend operator fun invoke():LiveData<List<Task>> = withContext(Dispatchers.IO){
        taskRepository.getTasks()
    }
}


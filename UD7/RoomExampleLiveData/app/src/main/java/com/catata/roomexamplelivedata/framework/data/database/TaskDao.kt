package com.catata.roomexamplelivedata.framework.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.catata.roomexamplelivedata.framework.data.database.entities.TaskEntity

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_entity")
    fun getAllTasks(): LiveData<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(taskEntity: TaskEntity):Long

    @Query("SELECT * FROM task_entity WHERE id LIKE :id")
    fun getTaskById(id: Int): TaskEntity

    @Update
    fun updateTask(taskEntity: TaskEntity):Int

    @Delete
    fun deleteTask(taskEntity: TaskEntity):Int

    @Query("DELETE FROM task_entity")
    fun deleteAllTask()
}
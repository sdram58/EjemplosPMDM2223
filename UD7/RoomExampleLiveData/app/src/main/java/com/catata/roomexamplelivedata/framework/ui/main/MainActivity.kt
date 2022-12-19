package com.catata.roomexamplelivedata.framework.ui.main

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catata.roomexamplelivedata.framework.ui.adapters.TasksAdapter
import com.catata.roomexamplelivedata.databinding.ActivityMainBinding
import com.catata.roomexamplelivedata.domain.Task
import com.catata.roomexamplelivedata.framework.ui.main.viewmodel.TaskViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val taskViewModel: TaskViewModel by viewModels()

    lateinit var recyclerView: RecyclerView
    var tasks: MutableList<Task> = mutableListOf()

    lateinit var adapter: TasksAdapter
        
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView( ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        MainScope().launch {
            taskViewModel.getAllTasks().observe(this@MainActivity){
                tasks.clear()
                tasks.addAll(it)
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }

        taskViewModel.updateTaskLD.observe(this){ taskUpdated ->
            if(taskUpdated == null){
                showMessage("Error updating task")
            }
        }

        taskViewModel.deleteTaskLD.observe(this){ id ->
            if(id != -1){
                val task = tasks.filter {
                    it.id == id
                }[0]
                val pos = tasks.indexOf(task)
                tasks.removeAt(pos)
                recyclerView.adapter?.notifyItemRemoved(pos)
            }else{
                showMessage("Error deleting task")
            }
        }


        binding.btnAddTask.setOnClickListener {
            addTask()
        }

        setUpRecyclerView()
    }

    private fun showMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }


    private fun addTask() {
        taskViewModel.add(binding.etTask.text.toString())
        clearFocus()
        hideKeyboard()

    }

    fun setUpRecyclerView() {
        adapter = TasksAdapter(tasks, ::updateTask, ::deleteTask)
        recyclerView = binding.rvTask
        //recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun updateTask(taskEntity: Task) {
        taskViewModel.update(taskEntity)
    }

    private fun deleteTask(taskEntity: Task) {
        taskViewModel.delete(taskEntity)
    }

    private fun clearFocus(){
        binding.etTask.setText("")
    }

    private fun Context.hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}
        
        
        

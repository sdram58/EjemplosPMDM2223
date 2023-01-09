package com.catata.retrofit2example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.catata.retrofit2example.adapter.DogAdapter
import com.catata.retrofit2example.databinding.ActivityMainBinding
import com.catata.retrofit2example.model.DogsResponse
import com.catata.retrofit2example.provider.APIService
import com.catata.retrofit2example.provider.DogProvider
import kotlinx.coroutines.*
import retrofit2.Response


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        initRecyclerView()

        binding.svDogs.setOnQueryTextListener(this)
        
    }

    private fun initRecyclerView() {
        adapter = DogAdapter(dogImages)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter
    }



    private fun searchByName(query:String){

        MainScope().launch {
            val dogList = DogProvider.getDogsByBreed(query)

            if(dogList.isNotEmpty()){
                dogImages.clear()
                dogImages.addAll(dogList)
                adapter.notifyDataSetChanged()
            }else{
                showError()
            }

        }
    }

    private fun showError() {
        Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT) .show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchByName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}
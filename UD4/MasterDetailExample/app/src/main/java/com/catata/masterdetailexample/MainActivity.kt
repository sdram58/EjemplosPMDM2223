package com.catata.masterdetailexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catata.masterdetailexample.databinding.ActivityMainBinding

import com.catata.masterdetailexample.interfaces.OnItemClick
import com.catata.masterdetailexample.model.Superhero
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnItemClick  {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        //Load ListFragment on its container. (binding.containerList)
        loadRecyclerView()

        //In the case we were in landscape we load also the DetailFragment in its container. (binding.containerDetail)
        if (isLandScape()){
            //We load first SuperHero
            loadDetailFragment(true, Superhero.getFirstID())
        }
    }


    private fun loadRecyclerView() {
        supportFragmentManager.beginTransaction()
            .replace(binding.containerList.id, ListFragment.newInstance(1))
            .addToBackStack(null)
            .commit()
    }


    /*Work around that let  us to know if we're un landscape mode*/
    private fun isLandScape():Boolean {
        return binding.containerDetail != null
    }


    private fun loadDetailFragment(twoPane:Boolean, heroID:Int){
        //If binding.containerDetail is null (we're in portrait mode) then we load DetailFragment in binding.containerList
        val id = binding.containerDetail?.id?:binding.containerList.id

        supportFragmentManager.beginTransaction()
            .replace(id, DetailFragment.newInstance(heroID))
            .addToBackStack(null)
            .commit()
    }

    //Callback implementation
    override fun onItemClick(hero: Superhero) {
        //We don't nee to use snackbar, which is such as an enhanced Toast where you can implement actions.
        //But I wanted to introduce it to you
        Snackbar.make(binding.containerList, "You have clicked on ${hero.superhero}", Snackbar.LENGTH_LONG)
            .setAction("Open"){

                loadDetailFragment(isLandScape(), hero.id)

            }
            .show()
    }
}
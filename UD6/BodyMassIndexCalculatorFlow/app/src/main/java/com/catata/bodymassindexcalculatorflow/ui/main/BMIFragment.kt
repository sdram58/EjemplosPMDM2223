package com.catata.bodymassindexcalculatorflow.ui.main

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.catata.bodymassindexcalculatorflow.databinding.FragmentBMIBinding
import com.catata.bodymassindexcalculatorflow.ui.main.viewmodel.BMICalculatorViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.DecimalFormat


class BMIFragment : Fragment() {
    private lateinit var binding: FragmentBMIBinding

    private var job: Job? = null

    /*
    * you can also get the reference to the viewModel through a
    * delegate (in older versions you may need to add some dependency)
    * See below
    */
    private val bmiCalculatorViewModel: BMICalculatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentBMIBinding
            .inflate(inflater, container, false)
            .also { binding = it }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCalculate.setOnClickListener {

            closeKeyBoard(it) //Closing soft keyboard

            var error = false
            var mHeight = 0.0
            var mWeight = 0.0

            binding.etHeight.error = null
            binding.etWeight.error = null
            try {
                mHeight = binding.etHeight.text.toString().toDouble()
            }catch (e : Exception){
                binding.etHeight.error = "You should insert a number"
                error = true
            }

            try {
                mWeight = binding.etWeight.text.toString().toDouble()
            }catch (e : Exception){
                binding.etWeight.error = "You should insert a number"
                error = true
            }

            if(!error){
                //Call to calculator viewModel
                bmiCalculatorViewModel.calculateBMI(mWeight,mHeight)
            }


        }

        /*lifecycleScope.launchWhenCreated {
            updateUI()
        }*/

        job = CoroutineScope(Dispatchers.Main).launch {
            updateUI()
        }

    }

    private suspend fun updateUI() {
        bmiCalculatorViewModel.state.collect(){ state ->

            Log.d("DEBUG_FLOW", "Loading -> ${state.isLoading}")

            val visible = if(state.isLoading) View.VISIBLE else View.INVISIBLE
            binding.progressCircular.visibility = visible

            when(state){
                is ResultOk ->{
                    binding.etWeight.error = null
                    binding.etHeight.error = null
                    state.bmi?.let {
                        val formatter = DecimalFormat("#,###.00")
                        binding.tvBMI.text = formatter.format(it)
                    }

                }
                is HeightError ->{
                    binding.etWeight.error = null
                    binding.etHeight.error = state.error
                }
                is WeightError ->{
                    binding.etWeight.error = state.error
                    binding.etHeight.error = null
                }
                else ->{ //Loading
                    binding.tvBMI.text = ""
                    binding.etWeight.error = null
                    binding.etHeight.error = null
                }
            }
        }
    }

    private fun closeKeyBoard(view:View){
        (activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onDetach() {
        job?.let {
            if(it.isActive) it.cancel()
        }

        job = null
        super.onDetach()
    }




}


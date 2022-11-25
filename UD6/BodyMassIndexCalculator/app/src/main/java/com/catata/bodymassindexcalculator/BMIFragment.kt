package com.catata.bodymassindexcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.catata.bodymassindexcalculator.databinding.FragmentBMIBinding
import java.text.DecimalFormat


class BMIFragment : Fragment() {
    private lateinit var binding:FragmentBMIBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b_m_i, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCalculate.setOnClickListener {


            binding.progressCircular.visibility = View.VISIBLE

            val bmiCalculator = BMICalculator()
            val mHeight = binding.etHeight.text.toString().toDouble()
            val mWeight = binding.etWeight.text.toString().toDouble()

            val bmi = bmiCalculator.calculate(BMICalculator.Request(mWeight, mHeight))
            val dec = DecimalFormat("#,###.00")

            binding.tvBMI.text = dec.format(bmi).toString()

            binding.progressCircular.visibility = View.GONE
        }
    }


}
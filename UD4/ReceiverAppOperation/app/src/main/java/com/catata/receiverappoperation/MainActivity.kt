package com.catata.receiverappoperation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catata.receiverappoperation.databinding.ActivityMainBinding
import com.catata.senderappoperation.model.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
        
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView( ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)
        
        
        
        //getRawData()
        getParcelableData()

        binding.btnCalculate.setOnClickListener {
            val intent = Intent().apply { putExtra(EXTRA_RESULT_KEY, binding.tvResult.text) }
            setResult(RESULT_OK, intent)
            finish()
        }
        
    }

    private fun getParcelableData() {
        val model:Model? = intent.getParcelableExtra<Model>(Intent.EXTRA_STREAM)

        model?.let {
            binding.tvNum1.text = it.num1.toString()
            binding.tvNum2.text = it.num2.toString()
            binding.tvOp.text = it.operation.symbol
            binding.tvResult.text = calculate(it).toString()
        }
    }

    private fun getRawData() {
        val num1 = intent.getDoubleExtra(EXTRA_NUM1_KEY, 0.0)

        val num2 = intent.getDoubleExtra(EXTRA_NUM2_KEY, 1.0)
        val op = when(intent.getStringExtra(EXTRA_OP_KEY) ?: Model.Operation.ADD.symbol){
            Model.Operation.ADD.symbol -> Model.Operation.ADD
            Model.Operation.SUBS.symbol -> Model.Operation.SUBS
            Model.Operation.MULT.symbol -> Model.Operation.MULT
            Model.Operation.DIV.symbol -> Model.Operation.DIV
            else -> Model.Operation.ADD
        }

        val model = Model(num1,num2, op)

        binding.tvNum1.text = num1.toString()
        binding.tvNum2.text = num2.toString()
        binding.tvOp.text = op.symbol
        binding.tvResult.text = calculate(model).toString()


    }

    private fun calculate(model:Model):Double{
        return when(model.operation){
            Model.Operation.ADD -> model.num1 + model.num2
            Model.Operation.SUBS -> model.num1 - model.num2
            Model.Operation.MULT -> model.num1 * model.num2
            Model.Operation.DIV -> model.num1 / model.num2
        }
    }
}
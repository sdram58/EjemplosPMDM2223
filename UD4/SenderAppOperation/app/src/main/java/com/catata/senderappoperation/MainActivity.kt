package com.catata.senderappoperation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.catata.senderappoperation.databinding.ActivityMainBinding
import com.catata.senderappoperation.model.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private var resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            //Here we have the result of Activity2
            val data: Intent? = result.data

            binding.tvResult.text = data?.let { i -> i.getStringExtra(EXTRA_RESULT_KEY) }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView( ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)


        binding.btnSendOp.setOnClickListener{
            //sendRawOp()
            sendParcelableModel()
        }


    }

    private fun sendParcelableModel() {
        with(binding) {
            val op = when (rbGroup.checkedRadioButtonId) {
                R.id.rbAddOp -> Model.Operation.ADD
                R.id.rbSubsOp -> Model.Operation.SUBS
                R.id.rbMultOp -> Model.Operation.MULT
                R.id.rbDivOp -> Model.Operation.DIV
                else -> {
                    Toast.makeText(this@MainActivity,
                        getString(R.string.no_op_selected),
                        Toast.LENGTH_SHORT).show()
                    return
                }

            }
            val num1 = etNum1.text.toString().toDoubleOrNull() ?: 0.0
            val num2 = etNum2.text.toString().toDoubleOrNull() ?: 1.0
            val model = Model(num1, num2, op)
            val intent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_STREAM,model)
            }



            val sharedIntent = Intent.createChooser(
                intent,
                //getString(R.string.select_app,2.5)
                getString(R.string.select_app,model.num1,model.operation.symbol,model.num2)
            )

            resultLauncher.launch(sharedIntent)


        }
    }


    private fun sendRawOp() {

        with(binding){
            val op = when(rbGroup.checkedRadioButtonId){
                R.id.rbAddOp -> Model.Operation.ADD
                R.id.rbSubsOp -> Model.Operation.SUBS
                R.id.rbMultOp -> Model.Operation.MULT
                R.id.rbDivOp -> Model.Operation.DIV
                else -> {
                    Toast.makeText(this@MainActivity, getString(R.string.no_op_selected), Toast.LENGTH_SHORT).show()
                    return
                }

            }

            val num1 = etNum1.text.toString().toDoubleOrNull() ?: 0.0
            val num2 = etNum2.text.toString().toDoubleOrNull() ?: 1.0

            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(EXTRA_NUM1_KEY,num1)
                putExtra(EXTRA_NUM2_KEY,num2)
                putExtra(EXTRA_OP_KEY,op.symbol)

                type = "text/plain"

            }

            val model = Model(num1, num2, op)

            val sharedIntent = Intent.createChooser(
                intent,
                //getString(R.string.select_app,2.5)
                getString(R.string.select_app,model.num1,model.operation.symbol,model.num2)
            )

            resultLauncher.launch(sharedIntent)
        }
    }
}
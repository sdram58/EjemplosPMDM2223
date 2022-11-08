package com.catata.loginbackground

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.catata.loginbackground.databinding.ActivityMainBinding
import com.catata.loginbackground.provider.LoginProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView( ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)


        binding.btnLogin.setOnClickListener{
            val loginProvider = LoginProvider()
            MainScope().launch {
                binding.pbLogin.visibility = View.VISIBLE
                val user = loginProvider.doLogin(
                    binding.etUserName.text.toString(),
                    binding.etPassword.text.toString()
                )
                binding.pbLogin.visibility = View.GONE
                user?.let {

                    showMessage(
                        """
                            Hi ${user.name}
                            You're ${user.age} old
                        """.trimIndent()
                    )
                } ?: showMessage(getString(R.string.invalid_login))
            }
        }


    }

    fun showMessage(text:String){
        val builder = AlertDialog.Builder(this)

        val view = layoutInflater.inflate(R.layout.dialog,null)
        builder.setView(view)

        view.findViewById<TextView>(R.id.tvTitle).text = "Login Result"
        view.findViewById<TextView>(R.id.tvText).text = text
        /*view.findViewById<Button>(R.id.btnLogin).setOnClickListener {
            Toast.makeText(this, text,Toast.LENGTH_SHORT).show()
        }*/


        builder.create().show()


        /*alertDialog.setTitle("Login result")
            .setMessage(text)
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
            })

        alertDialog.show()


         */


    }
}
package com.example.broadcastreceiverexample

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcastreceiverexample.MyBroadcastReceiver.Companion.ACTION_POWER_CONNECTED
import com.example.broadcastreceiverexample.MyBroadcastReceiver.Companion.ACTION_POWER_DISCONNECTED
import com.example.broadcastreceiverexample.MyBroadcastReceiver.Companion.MY_ACTION_RECEIVER_ACTION
import com.example.broadcastreceiverexample.MyBroadcastReceiver.Companion.MY_ACTION_RECEIVER_EXTRA
import com.example.broadcastreceiverexample.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var broadcastReceiver: MyBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        createBroadcast()

        binding.btnSend.setOnClickListener {
            //sendMessage()
            sendDelayedMessage()
        }

    }

    private fun sendDelayedMessage() {
        MainScope().launch {
            delay(2000)
            sendMessage()
        }
    }


    private fun sendMessage() {
        sendBroadcast(makeIntent())
    }

    private fun makeIntent(): Intent {
        val intent = Intent(MY_ACTION_RECEIVER_ACTION)
        intent.putExtra(MY_ACTION_RECEIVER_EXTRA, "Message to Broadcast Receiver")

        return intent
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onDestroy() {
        super.onDestroy()
        //We unregister our broadcast receiver. Our app no longer listens to those actions
        unregisterReceiver(broadcastReceiver)
    }

    private fun createBroadcast(){
        //new MyBroadcastReceiver
        broadcastReceiver = MyBroadcastReceiver()

        //We make an intent filter with the actions that we want to manage.
        val filter = IntentFilter(MY_ACTION_RECEIVER_ACTION)
        filter.addAction(ACTION_POWER_CONNECTED)
        filter.addAction(ACTION_POWER_DISCONNECTED)

        //We register our broadcast receiver
        registerReceiver(broadcastReceiver,filter)
    }

}
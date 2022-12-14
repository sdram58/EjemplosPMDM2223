package com.catata.commonintentexamples

import android.Manifest
import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CalendarContract
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.catata.commonintentexamples.databinding.ActivityMainBinding
import com.google.android.gms.actions.NoteIntents


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            val thumbnail: Bitmap?= data?.getParcelableExtra("data")
            with(binding.ivCameraResult){
                visibility = View.VISIBLE
                setImageBitmap(thumbnail)
            }
        }
    }

    companion object{
        val MY_CAMERA_PERMISSION_CODE = 10
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        with(binding){
            btnDial.setOnClickListener {
                dialPhoneNumber("655555555")
            }

            btnAlarm.setOnClickListener {
                createAlarm("New alarm", 16, 40)
            }

            btnEvent.setOnClickListener {
                addEvent("New Event", "Torrent", 1665483318974L, 1665683318974L)
            }

            btnCamera.setOnClickListener {
                capturePhoto("my_pic")
            }

            btnEmail.setOnClickListener {
                composeEmail(arrayOf("email1@gmail.com","emai2@gmail.com"), "New Email", Uri.parse(""))
            }

            btnNewNote.setOnClickListener {
                createNote("New Note", "This is the body of the note")
            }

            btnWebSearch.setOnClickListener {
                searchWeb("IES SERRA PERENXISA")
            }

            btnShowLocation.setOnClickListener {
                showMap(Uri.parse("geo:0,0?q=39.4295152,-0.4660814,15z(Treasure)"))
            }

            btnWifiSettings.setOnClickListener {
                openWifiSettings()
            }
            btnTextMessage.setOnClickListener {
                composeSmsMessage("This is the text", Uri.parse(""))
            }

            btnWebBrowser.setOnClickListener {
                openWebPage("https://google.es")
            }
        }
    }

    fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }


    }

    fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun addEvent(title: String, location: String, begin: Long, end: Long) {
        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, title)
            putExtra(CalendarContract.Events.EVENT_LOCATION, location)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun capturePhoto(targetFilename: String) {
        //Check permission
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
        } else{

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager) != null) {


                resultLauncher.launch(intent)
            }
        }



    }

    fun composeEmail(addresses: Array<String>, subject: String, attachment: Uri) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "*/*"
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_STREAM, attachment)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun createNote(subject: String, text: String) {
        val intent = Intent(NoteIntents.ACTION_CREATE_NOTE).apply {
            putExtra(NoteIntents.EXTRA_NAME, subject)
            putExtra(NoteIntents.EXTRA_TEXT, text)
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            val i = Intent.createChooser(intent, "Select App")
            startActivity(i)
        }else if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }else{
            Toast.makeText(this, "No apps for notes found", Toast.LENGTH_SHORT).show()
        }
    }

    fun searchWeb(query: String) {
        val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
            putExtra(SearchManager.QUERY, query)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun showMap(geoLocation: Uri) { //example "geo:0,0?q=39.4295152,-0.4660814(Treasure)"
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = geoLocation
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun openWifiSettings() {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun composeSmsMessage(message: String, attachment: Uri) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            //type = PLAIN_TEXT_TYPE
            putExtra("sms_body", message)
            putExtra(Intent.EXTRA_STREAM, attachment)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }


}
package com.catata.filesexample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.storage.StorageManager
import android.os.storage.StorageManager.ACTION_CLEAR_APP_CACHE
import android.os.storage.StorageManager.ACTION_MANAGE_STORAGE
import androidx.core.content.ContextCompat
import com.catata.filesexample.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileReader
import java.io.Reader
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var cacheFileName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)



        hasInternalStorageEnoughSpace()


        MainScope().launch {
            /*writeFileInternal()
            delay(1000)
            readFileInternal()
            delay(2000)
            viewListOfFilesInternal()
            delay(2000)
            writeCacheFile()
            delay(1000)
            readCacheFile()
            delay(2000)*/
            /*val a = getExternalStorageVolumes()
            println(a.absolutePath)
            val b = getExternalStorageVolumes2()
            println(b.absolutePath)*/
            mgetExternalFileDirs()
            delay(2000)
        }


    }

    private fun mgetExternalFileDirs() {
        val appSpecificExternalDir=File(getExternalFilesDir(null), FILENAME)

        println(appSpecificExternalDir.absolutePath)
    }



    private fun getExternalStorageVolumes():File {
        val externalStorageVolumes: Array<File> =
            ContextCompat.getExternalFilesDirs(applicationContext, null)
        return externalStorageVolumes[0]
    }
    private fun getExternalStorageVolumes2():File {
        val externalStorageVolumes: Array<File> =
            ContextCompat.getExternalFilesDirs(applicationContext, null)
        return if(externalStorageVolumes.size>1) externalStorageVolumes[1] else externalStorageVolumes[1]
    }

    private fun readCacheFile() {

        File(this.cacheDir, cacheFileName).bufferedReader().useLines { lines ->
            binding.tvText.text = lines.fold("") { prev, cur ->
                "$prev\n$cur"
            }
        }

    }

    private fun writeCacheFile() {
        val fileContents="Hello Cache File!\nBye bye Cache File"
        cacheFileName = File.createTempFile(CACHE_FILENAME, null, this.cacheDir).let {
            val fos = FileOutputStream(it)
            fos.write(fileContents.toByteArray())
            it.name
        }

        binding.tvText.text = "Cache File has been written"
    }

    private fun viewListOfFilesInternal() {
        binding.tvText.text = fileList().fold(""){ previous, current ->
            "$previous\n$current"
        }
    }

    private fun readFileInternal() {
        openFileInput(FILENAME).bufferedReader().useLines { lines ->
            binding.tvText.text = lines.fold("") { some, text ->
                "$some\n$text"
            }
        }
    }

    private fun writeFileInternal() {

        val fileContents="Hello world!\nGood bye world!!"
        openFileOutput(FILENAME, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }

        binding.tvText.text = "Internal file has been written"
    }

    fun hasInternalStorageEnoughSpace(){
        // App needs 10 MB within internal storage.
        val NUM_BYTES_NEEDED_FOR_MY_APP=1024 * 1024 * 10L;

        val storageManager:StorageManager = getSystemService(Context.STORAGE_SERVICE) as StorageManager

        val appSpecificInternalDirUuid: UUID =storageManager.getUuidForPath(filesDir)

        val availableBytes: Long = storageManager.getAllocatableBytes(appSpecificInternalDirUuid)

        if (availableBytes >= NUM_BYTES_NEEDED_FOR_MY_APP) {
            storageManager.allocateBytes(appSpecificInternalDirUuid, NUM_BYTES_NEEDED_FOR_MY_APP)
        } else { //We don't have enough space we ask for removing something
            val storageIntent= Intent().apply {
                // To request that the user remove all app cache files instead, set
                // "action" to ACTION_CLEAR_APP_CACHE.
                //action=ACTION_MANAGE_STORAGE
                action=ACTION_CLEAR_APP_CACHE
            }
            startActivity(storageIntent)
        }
    }

    companion object{
        const val FILENAME="my_file"
        const val CACHE_FILENAME="cache_file"
    }
}
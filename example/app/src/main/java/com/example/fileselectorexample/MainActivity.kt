package com.example.fileselectorexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import com.filolo.fileselector.FileSelector

class MainActivity : AppCompatActivity() {

    private val fileSelector =  FileSelector()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // init the button
        btn_select_files.setOnClickListener {
           fileSelector.getFiles("*/*", this as Activity)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        val fileUris = fileSelector.processFilesResult(requestCode, resultCode, resultData)

        Log.i("SELECTED FILES", fileUris.toString())
    }

}

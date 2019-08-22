package com.filolo.fileselector

import android.app.Activity
import android.content.Intent
import android.net.Uri


class FileSelector {

    private lateinit var activity: Activity

    fun getFiles(fileTypeFilter: String, activity: Activity) {
        this.activity = activity

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {

            addCategory(Intent.CATEGORY_OPENABLE)
            type = fileTypeFilter
            this.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }

        activity.startActivityForResult(intent, READ_REQUEST_CODE)
    }

    fun processFilesResult(requestCode: Int, resultCode: Int, resultData: Intent?): ArrayList<Uri> {

        val fileUri =  ArrayList<Uri>()
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            // handle single selections
            resultData?.data?.also { fileUri.add(it) }

            // handle multiple document selections
            resultData?.clipData?.also {
                var count = 0

                while (count < it.itemCount){
                    val uri = it.getItemAt(count).uri
                    fileUri.add(uri)
                    count ++
                }
            }
        }

        return fileUri
    }


    companion object {
        // the file selector read request code
        private const val READ_REQUEST_CODE: Int = 42
    }
}
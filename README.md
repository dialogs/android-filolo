# Android File Selector Widget

This library uses the [Android Storage Access Framework](https://developer.android.com/guide/topics/providers/document-provider) to allow a developer to quickly implement access to the android file system  so that end users can be able to share their files in the app.


## Installation

To install the library simply do the following,

1. Open this project in Android Studio and then build it. Then navigate to the folders `app -> build -> outputs -> aar` and select the generated `.aar` file 
2. Then modify your project's `build.gradle` file found in the app folder and add the dependency 
	`implementation fileTree(dir: 'libs', include: ['*.jar','*.aar'])`
	or modify the dependency to include `.aar` files if it already exists.
	and copy it to the `libs` folder of your project.
3. Alternatively install it as a gradle dependency from `jcenter()` by adding it to the dependencies section of your project i.e
```
dependencies {
    implementation 'com.filolo.fileselector:filolo:0.1.0'
    ...
}
```
4. Finally clean and build your project to now use the library.

## Usage

To use the library simply do the following,

1. Import it into your activity or fragment i.e. 

```kt
import com.filolo.fileselector.FileSelector
```
2. Initialize the selector in your activity or fragment i.e.
```kt
private val fileSelector =  FileSelector()
```
3. Then call the `getFiles` method in your button, recyclerview e.t.c and pass the [mime/type](https://www.sitepoint.com/mime-types-complete-list/) i.e. `audio/mp3` of the files to be filtered and also your current activity as params i.e.

```kt
btn_select_files.setOnClickListener {
   fileSelector.getFiles("audio/mp3", this as Activity)
}
```
4. Finally override the activity's `onActivityResult` function and pass the results by the user selection to the library for processing i.e.
```kt
override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
    val fileUris = fileSelector.processFilesResult(requestCode, resultCode, resultData)

    Log.i("SELECTED FILES", fileUris.toString())
}
```
5. The `processFilesResult` function returns an array of `uri's` of the selected file(s) which you can now [process in your app](https://stackoverflow.com/questions/2975197/convert-file-uri-to-file-in-android) to get the actual file


## API

The library methods as explained above are the following,

getFiles
========

This method opens the filesystem , via the native selector, for the user to select the necessary files

params/args
-----------
- fileType `String` - The mime/type of the files you want to filter
- activity `Activity` - The current activity the widget is running on. For fragmenets you can use the method `getActivity()` instead

returns
-------
This function returns nothing

processFilesResult
==================

It process the result of the user selection and returns the uri's of the selected files by the user if any.

params/args
-----------
- requestCode `Int` - The requestCode received from overriding the activity's `onActivityResult` function
- resultCode `Int` - The resultCode received from overriding the activity's `onActivityResult` function
- resultData `Intent` - The resultData received from overriding the activity's `onActivityResult` function

returns
-------
This function returns an `ArrayList` of the uri's of selected files if any, which can then be used to get the [specific files](https://stackoverflow.com/questions/2975197/convert-file-uri-to-file-in-android)

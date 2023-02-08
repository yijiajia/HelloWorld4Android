package com.example.helloworld.persistent

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.helloworld.R
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * 文件存储
 */
class FilePersistentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_persistent)

        // 恢复输入框的内容
        val inputText = loadFile()
        if(inputText.isNotEmpty()) {
            val editText : EditText = findViewById(R.id.edit_text)
            editText.setText(inputText)
            editText.setSelection(inputText.length)
            Toast.makeText(this,"ReLoad Success",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        // 销毁前记录输入框的文案
        val editText : EditText = findViewById(R.id.edit_text)
        val inputText = editText.text.toString()
        if(inputText.isNotEmpty()) {
            Log.d("","输入框的内容：$inputText")
            saveToFile(inputText)
        }
    }

    private fun saveToFile(inputText: String) {
        val outputStream = openFileOutput("data",Context.MODE_PRIVATE)
        val writer = BufferedWriter(OutputStreamWriter(outputStream))
        writer.use {
            it.write(inputText)
        }
    }

    private fun loadFile() : String{
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        }catch (e : IOException) {
            e.printStackTrace()
        }
        return content.toString()
    }

}
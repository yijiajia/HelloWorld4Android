package com.example.helloworld.contentProvider

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.helloworld.R

class ContentProviderMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider_main)

        val btn_openContracts : Button = findViewById(R.id.btn_openContracts)
        btn_openContracts.setOnClickListener {
            startActivity(Intent(this,ContentProviderContactsActivity::class.java))
        }

        val btn_openOtherApp : Button = findViewById(R.id.btn_openOtherApp)
        btn_openOtherApp.setOnClickListener {
            val intent = initIntent("provider://demo")
            startActivity(intent)
        }
    }

    fun initIntent(uri : String) : Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.data = Uri.parse(uri)
        return intent
    }
}
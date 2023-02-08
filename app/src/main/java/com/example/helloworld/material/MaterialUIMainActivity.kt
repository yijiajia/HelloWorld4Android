package com.example.helloworld.material

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.helloworld.R

class MaterialUIMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_uimain)

        val open_toolbar : Button = findViewById(R.id.open_toolbar)
        open_toolbar.setOnClickListener {
            startActivity(Intent(this, ToolBarActivity::class.java))
        }

        val open_drawerLayout : Button = findViewById(R.id.open_drawerLayout)
        open_drawerLayout.setOnClickListener {
            startActivity(Intent(this, DrawerLayoutActivity::class.java))
        }

        val open_cardView : Button = findViewById(R.id.open_cardView)
        open_cardView.setOnClickListener{
            startActivity(Intent(this, MaterialCardViewActivity::class.java))
        }
    }


}
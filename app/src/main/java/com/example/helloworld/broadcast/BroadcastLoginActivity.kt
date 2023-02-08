package com.example.helloworld.broadcast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.helloworld.R

class BroadcastLoginActivity : BroadcastBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_login2)

        val editAccount : EditText = findViewById(R.id.edit_account)
        val editPwd : EditText = findViewById(R.id.edit_pwd)
        val rememberPwd : CheckBox = findViewById(R.id.checkbox_rememberPwd)

        val prefs = getPreferences(MODE_PRIVATE)
        val isRemember = prefs.getBoolean("remember_pwd",false)
        if(isRemember) {
            val account = prefs.getString("account","")
            val pwd = prefs.getString("pwd","")
            editAccount.setText(account)
            editPwd.setText(pwd)
            rememberPwd.isChecked = true
        }

        val btnLogin : Button = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener {
            val account = editAccount.text.toString()
            val pwd = editPwd.text.toString()
            if(account.isEmpty() || pwd.isEmpty()) {
                Toast.makeText(this,"帐号或密码不能为空",Toast.LENGTH_SHORT).show()
            }else {
                Log.d("BroadcastLoginActivity","account = $account, pwd = $pwd")
                if("admin".equals(account) && "123456".equals(pwd)) {
                    val edit = prefs.edit()
                    if(rememberPwd.isChecked) {
                        edit.putString("account",account)
                        edit.putString("pwd",pwd)
                        edit.putBoolean("remember_pwd",true)
                    }else {
                        edit.clear() // 未勾选，需要清除
                    }
                    edit.apply()     // 提交
                    Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,BroadcastLoginCompleteActivity::class.java))
                    finish()
                }else {
                    Toast.makeText(this,"帐号或密码错误",Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
}
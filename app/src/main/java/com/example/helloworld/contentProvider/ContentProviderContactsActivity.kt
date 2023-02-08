package com.example.helloworld.contentProvider

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.helloworld.R

/**
 * 读取联系人
 */
class ContentProviderContactsActivity : AppCompatActivity() {

    private val contactsList = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>
    private val CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider_contacts)

        val contactsListView : ListView = findViewById(R.id.listview_contacts)
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,contactsList)
        contactsListView.adapter = adapter

        /**
         * 打开界面时即去请求权限，先查看是否有授权读取联系人的权限，没有的话则通过 ActivityCompat.requestPermissions 去申请
         */
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),CODE)
        } else {
            readContacts()
        }

    }

    /**
     * 权限请求的结果返回
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,   // 请求码
        permissions: Array<out String>, // 权限
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // 根据请求码区分权限的处理逻辑
        when (requestCode) {
            CODE -> {
                // 当获取到授权时，读取联系人（此时才不会报错）
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts()
                }else {
                    Toast.makeText(this,"权限被禁止",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun readContacts() {
//       val contentResolver = ContentResolver()
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)
            ?.apply {
                while(moveToNext()) {
                    val displayName = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    val number = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    contactsList.add("$displayName\n$number")
                }
                adapter.notifyDataSetChanged()  // 刷新界面
                close()
            }

    }
}
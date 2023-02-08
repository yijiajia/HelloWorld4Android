package com.example.helloworld.fileSystem

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.FileProvider
import com.example.helloworld.R
import java.io.File

/**
 * 调用系统相册拍摄
 */
class CameraAlbumActivity : AppCompatActivity() {

    object RequestCode {
        const val takePhoto = 0
        const val album = 1
    }

    lateinit var imageUri : Uri
    lateinit var outputImage : File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        Log.d("","externalCacheDir=$externalCacheDir")
        val takePhoto : Button = findViewById(R.id.btn_takePhoto)
        takePhoto.setOnClickListener {
            // 创建File对象，用于存储拍照后的照片
            outputImage = File(externalCacheDir,"output_image.jpg")
            if(outputImage.exists()) {
                outputImage.delete()
            }
            outputImage.createNewFile()
            imageUri = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                FileProvider.getUriForFile(this,"com.example.helloworld.fileprovider",outputImage)
            }else {
                Uri.fromFile(outputImage)
            }
            // 启动相机程序
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
            startActivityForResult(intent,RequestCode.takePhoto)
        }

        val openAlbum : Button = findViewById(R.id.btn_openAlbum)
        openAlbum.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            // 指定只显示图片
            intent.type = "image/*"
            startActivityForResult(intent,RequestCode.album)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            RequestCode.takePhoto -> {
                if(resultCode == Activity.RESULT_OK) {
                    // 将拍摄的照片显示出来
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                    val imageView : ImageView = findViewById(R.id.img_photo)
                    imageView.setImageBitmap(bitmap)
                }
            }
            RequestCode.album -> {
                if(resultCode == Activity.RESULT_OK && data != null) {
                    data.data?.let {
                        uri ->
                        val bitmap = getBitmapFromUri(uri)
                        val imageView : ImageView = findViewById(R.id.img_photo)
                        imageView.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }

    /**
     * 图片的旋转代码
     */
    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(outputImage.path)
        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270)
            else -> bitmap
        } }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedBitmap =
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        bitmap.recycle() // 将不再需要的Bitmap对象回收
        return rotatedBitmap
    }

    private fun getBitmapFromUri(uri : Uri) = contentResolver.openFileDescriptor(uri,"r")?.use {
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
    }
}
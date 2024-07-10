package com.sungkyul.synergy.training_space.default_app

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.sungkyul.synergy.R
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date


class RealCameraActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val REQUEST_PERMISSION = 1000
    }

    private lateinit var imageFilePath: String
    private lateinit var photoUri: Uri

    private lateinit var capturedImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_real_camera)

        capturedImageView = findViewById(R.id.captured_image)

        findViewById<View>(R.id.btn_capture).setOnClickListener {
            val cameraPermissionCheck = ContextCompat.checkSelfPermission(
                this@RealCameraActivity,
                android.Manifest.permission.CAMERA
            )
            if (cameraPermissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    REQUEST_PERMISSION
                )
            } else {
                dispatchTakePictureIntent()
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photoFile?.also {
                    photoUri = FileProvider.getUriForFile(
                        this,
                        "com.sungkyul.synergy.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            imageFilePath = absolutePath
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent()
            } else {
                Toast.makeText(this, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val file = File(imageFilePath)
            if (file.exists()) {
                val imageBitmap = MediaStore.Images.Media.getBitmap(contentResolver, Uri.fromFile(file))
                capturedImageView.setImageBitmap(imageBitmap)
                capturedImageView.visibility = View.VISIBLE
               // galleryAddPic()
                saveFile(Uri.fromFile(file))
            }
        }
    }

    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(imageFilePath)
            mediaScanIntent.data = Uri.fromFile(f)
            sendBroadcast(mediaScanIntent)
        }
    }  // 파일 저장

    private fun saveFile(image_uri: Uri) {
        val values = ContentValues()
        val fileName = "woongs" + System.currentTimeMillis() + ".png" // 저장할 파일 이름을 지정합니다.
        values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName) // 파일 이름을 ContentValues에 추가합니다.
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/*") // MIME 타입을 지정합니다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.Images.Media.IS_PENDING, 1) // Android Q 이상에서는 IS_PENDING 값을 1로 설정하여 임시 파일로 표시합니다.
        }
        val contentResolver = contentResolver
        val item = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values) // 이미지를 저장할 Uri를 가져옵니다.
        try {
            val pdf = contentResolver.openFileDescriptor(item!!, "w", null) // 이미지 파일을 엽니다.
            if (pdf == null) {
                Log.d("Woongs", "null")
            } else {
                val inputData: ByteArray = getBytes(image_uri) // 이미지 파일을 ByteArray로 변환합니다.
                val fos = FileOutputStream(pdf.fileDescriptor) // 파일 출력 스트림을 생성합니다.
                fos.write(inputData) // 이미지 데이터를 파일에 씁니다.
                fos.close() // 파일 출력 스트림을 닫습니다.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    values.clear()
                    values.put(MediaStore.Images.Media.IS_PENDING, 0) // Android Q 이상에서는 IS_PENDING 값을 0으로 설정하여 파일을 저장 상태로 변경합니다.
                    contentResolver.update(item, values, null, null) // ContentValues를 사용하여 파일 상태를 업데이트합니다.
                }

                // 갱신
                galleryAddPic(fileName)
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            Log.d("Woongs", "FileNotFoundException  : " + e.localizedMessage)
        } catch (e: Exception) {
            Log.d("Woongs", "FileOutputStream = : " + e.message)
        }
    }

    // Uri에서 이미지 데이터를 읽어와 ByteArray로 변환하는 함수
    private fun getBytes(uri: Uri): ByteArray {
        val inputStream = contentResolver.openInputStream(uri) // Uri에서 InputStream을 엽니다.
        return inputStream?.readBytes() ?: byteArrayOf() // InputStream에서 데이터를 읽어와 ByteArray로 변환합니다.
    }

    // 저장된 파일을 갤러리에 추가하는 함수
    private fun galleryAddPic(fileName: String) {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), fileName) // 저장된 파일의 경로를 가져옵니다.
            mediaScanIntent.data = Uri.fromFile(f) // Intent에 저장된 파일의 Uri를 설정합니다.
            sendBroadcast(mediaScanIntent) // 미디어 스캐닝을 통해 갤러리에 저장된 파일을 추가합니다.
        }
    }
}

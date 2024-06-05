package com.sungkyul.synergy.learning_space.default_app.default_app_bar

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultAppBarBinding

class DefaultAppBar : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultAppBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultAppBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnClickListener {
            showDialog("문제", "와이파이를 키세요.")
        }

        binding.wifiImage.setOnClickListener {
            binding.wifiImage.setImageResource(R.drawable.ic_screenedu_wifi_up)
           // Toast.makeText(this, "잘 했습니다!", Toast.LENGTH_SHORT).show()
            showDialog("", "잘 했습니다!")

        }
//        binding.bluetoothImage.setOnClickListener {
//            Toast.makeText(this, "틀렸습니다!", Toast.LENGTH_SHORT).show()
//        }
//        binding.flightImage.setOnClickListener {
//            Toast.makeText(this, "틀렸습니다!", Toast.LENGTH_SHORT).show()
//        }
//        binding.moblieImage.setOnClickListener {
//            Toast.makeText(this, "틀렸습니다!", Toast.LENGTH_SHORT).show()
//        }
//        binding.powerSavingImage.setOnClickListener {
//            Toast.makeText(this, "틀렸습니다!", Toast.LENGTH_SHORT).show()
//        }
    }
    private fun showDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("확인") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}
package com.sungkyul.synergy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import com.sungkyul.synergy.databinding.ActivityMain2Binding

/** 허수진 - 파이어베이스 클라우드 스토어 데이터 넣기 연습 중.. 지우지 마세요ㅜㅜ*/
class MainActivity2 : AppCompatActivity() {

    private var _binding: ActivityMain2Binding? = null
    private val binding get() = _binding!!

    private var sampleNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setDocument(
            FirebaseData(
                sampleName = "퀴즈",
                sampleNumber = sampleNumber,
                sampleBoolean = false

            )
        )
        binding.buttonSet.setOnClickListener {
            sampleNumber++
            setDocument(
                FirebaseData(
                    sampleName = "퀴즈$sampleNumber",
                    sampleNumber = sampleNumber,
                    sampleBoolean = true
                )
            )
        }
    }

    private fun setDocument(data: FirebaseData) {
        FirebaseFirestore.getInstance()
            .collection("sampleCollection")
            .document(data.sampleName)
            .set(data)
            .addOnSuccessListener {
                binding.textResult.text = "success!"
            }
            .addOnFailureListener {
                binding.textResult.text = "fail!"
            }
    }
}

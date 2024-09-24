package com.sungkyul.synergy.training_space.default_app.camera

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeResultGalleryBinding
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DOWN_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DURATION_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_UP_ALPHA
import com.sungkyul.synergy.training_space.default_app.camera.problem.ExamCameraProblem4Activity
import com.sungkyul.synergy.utils.AnimUtils

class PracticeResultGalleryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeResultGalleryBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeResultGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.dotButton.setOnTouchListener(onTouchDeleteListener)

        // RecyclerView 설정
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_photos)

        // 가로 방향으로 스크롤 설정
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // 사진 데이터 (예시로 몇 개의 drawable을 사용)
        val photoList = listOf(
            R.drawable.sample_screenshot1,
            R.drawable.sample_screenshot2,
            R.drawable.screenedu_background,
            R.drawable.defaultback1,
            R.drawable.ic_appinstall_synergylogo_storke,
            R.drawable.korean_flag,
            R.drawable.sample_screenshot2,
            R.drawable.screenedu_background,
            R.drawable.gallery_apple,

            R.drawable.gallery_apple
        )

        // 어댑터 설정
        recyclerView.adapter = GalleryPhotoAdapter(this, photoList)
    }

    private val onTouchDeleteListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)

                showDialog()


                view.performClick()
            }
        }
        true
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("")
        builder.setMessage("이 이미지를 휴지통으로 이동할까요?")

        builder.setPositiveButton("휴지통으로 이동") { dialog, _ ->
            // 확인 버튼 클릭 시 작업
            dialog.dismiss()
             //   val intent = Intent(this, ExamCameraProblem5Activity::class.java)
                //  startActivity(intent)
        }

        builder.setNegativeButton("취소") { dialog, _ ->
            // 취소 버튼 클릭 시 작업
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}

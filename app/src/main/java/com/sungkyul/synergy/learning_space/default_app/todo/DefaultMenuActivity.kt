package com.sungkyul.synergy.learning_space.default_app.todo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultMenu2Binding
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GestureDetectorCompat

class DefaultMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDefaultMenu2Binding
    private lateinit var gestureDetector: GestureDetectorCompat
    private val handler = Handler(Looper.getMainLooper())
    private var touchCount = 0
    private var isLongPressed = false
    private var dX = 0f
    private var dY = 0f
    private val messages = listOf(
        "메뉴화면입니다.",
        "휴대폰에 설치되어 있는\n" +
                "앱을 볼 수 있는\n" +
                "화면입니다.",
        "메뉴화면에 있는 앱을\n" +
                "홈 화면으로 꺼내볼까요?",
        "“시너지” 앱을\n" +
                "표시된 곳에\n" +
                "이동시켜보세요."
    )

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultMenu2Binding.inflate(layoutInflater)
        setContentView(binding.root)
            gestureDetector = GestureDetectorCompat(this, object : GestureDetector.SimpleOnGestureListener() {
                override fun onLongPress(e: MotionEvent) {
                    super.onLongPress(e)
                    isLongPressed = true
                    handler.postDelayed({
                        if (isLongPressed) {
                            val intent = Intent(this@DefaultMenuActivity, DefaultAppAddActivity::class.java)
                            startActivity(intent)
                        }
                    }, 1000)
                }
            })
        binding.settingIcon.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(view: View, event: MotionEvent): Boolean {
                gestureDetector.onTouchEvent(event)
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        dX = view.x - event.rawX
                        dY = view.y - event.rawY
                        return true
                    }

                    MotionEvent.ACTION_MOVE -> {
                        view.animate()
                            .x(event.rawX + dX)
                            .y(event.rawY + dY)
                            .setDuration(0)
                            .start()
                        return true
                    }
                }
                return false
            }
        })
    }

    // 다이얼로그를 표시하는 메서드
    private fun showDialog(title: String, message: String) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)

        val alertDialog = dialogBuilder.create()

        // 둥근 모서리 배경 적용
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // 텍스트 뷰의 내용을 동적으로 설정
        val titleTextView = dialogView.findViewById<TextView>(R.id.dialogTitle)
        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        titleTextView.text = title
        messageTextView.text = message

        alertDialog.show()

        // 다이얼로그가 닫힐 때 다음 메시지 설정
        alertDialog.setOnDismissListener {
            incrementAndShowDialog()
        }
    }

    private fun incrementAndShowDialog() {
        touchCount++
        if (touchCount < messages.size) {
            showDialog("", messages[touchCount])
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN && touchCount < messages.size) {
            incrementAndShowDialog()
        }
        return super.onTouchEvent(event)
    }
}

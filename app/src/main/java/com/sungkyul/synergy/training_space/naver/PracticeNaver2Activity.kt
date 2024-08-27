package com.sungkyul.synergy.training_space.naver

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityNaverSearchBinding
import com.sungkyul.synergy.databinding.ActivityPracticeNaver2Binding
import com.sungkyul.synergy.databinding.ActivityPracticeNaverBinding
import com.sungkyul.synergy.learning_space.naver.activity.NaverSearchInfoActivity
import com.sungkyul.synergy.learning_space.naver.activity.NaverSearchResultActivity
import com.sungkyul.synergy.learning_space.naver.adapter.NaverAutocompleteAdapter
import com.sungkyul.synergy.learning_space.naver.adapter.NaverAutocompleteData
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity
import com.sungkyul.synergy.utils.TextUtils

class PracticeNaver2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeNaver2Binding

    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 300000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeNaver2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        remainingTimeInMillis = intent.getLongExtra("remainingTimeInMillis", 30000)
        startTimer()


        val naverAutocompleteArray = ArrayList<NaverAutocompleteData>()

        val naverAutocompleteList = binding.naverAutocompleteList
        naverAutocompleteList.layoutManager = LinearLayoutManager(binding.root.context)
        naverAutocompleteList.adapter = NaverAutocompleteAdapter(naverAutocompleteArray, this)

        binding.searchEditText.requestFocus()

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val adapter = naverAutocompleteList.adapter as NaverAutocompleteAdapter

                // 모든 아이템들을 삭제한다.
                adapter.notifyItemRangeRemoved(0, naverAutocompleteArray.size)

                // 자동 완성
                naverAutocompleteArray.clear()
                if (s.toString().isNotEmpty()) {
                    for (i in TextUtils.searchAll(s.toString())) {
                        naverAutocompleteArray.add(NaverAutocompleteData(i))
                    }
                }

                // 아이템들을 추가한다.
                adapter.notifyItemRangeInserted(0, naverAutocompleteArray.size)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.previousButton.setOnClickListener {
            finish()
        }
        binding.clearButton.setOnClickListener {
            binding.searchEditText.setText("")
        }
        binding.searchButton.setOnClickListener {
            val searchQuery = binding.searchEditText.text.toString()
            if (searchQuery.contains("된장찌개") || searchQuery.contains("된장 찌개") || searchQuery.contains(
                    "된장"
                )
            ) {
                val intent = Intent(this, PracticeNaver3Activity::class.java)
                intent.putExtra("remainingTimeInMillis", remainingTimeInMillis) // 남은 시간 전달
                startActivity(intent)
            } else {
            }
        }
        binding.cancelButton.setOnClickListener {
            finish()
        }
        // 타이머 초기화
        timer = object : CountDownTimer(remainingTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                binding.timerTextView.text = secondsLeft.toString() // 초를 텍스트뷰에 표시
            }

            override fun onFinish() {
                binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
            }
        }

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 액티비티가 생성되면 타이머 시작
        isTimerRunning = true
    }

    override fun onPause() {
        super.onPause()
        pausedTimeInMillis = remainingTimeInMillis
        timer.cancel() // 타이머를 취소하여 불필요한 시간 감소를 막음
        isTimerRunning = false
    }

    override fun onResume() {
        super.onResume()
        if (!isTimerRunning) {
            startTimer(pausedTimeInMillis)
        }

    }

    private fun startTimer(startTimeInMillis: Long = remainingTimeInMillis) {
        timer = object : CountDownTimer(startTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                findViewById<TextView>(R.id.timerTextView).text = secondsLeft.toString()
            }

            override fun onFinish() {
                if (!success) { // 성공하지 않았을 때만 실패로 저장
                    findViewById<TextView>(R.id.timerTextView).text = "0"
                    // saveResult(false) // 실패 결과 저장
                }
                // 타이머가 종료되면 자동으로 실패 처리됨
                //  returnToHomeScreen()
            }
        }

        // 문제보기 클릭 시 다이얼로그 띄우기
        findViewById<TextView>(R.id.problemText).setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 액티비티가 생성되면 타이머 시작
        isTimerRunning = true
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showProblemDialog() {
        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)

        // 커스텀 레이아웃을 설정하기 위한 레이아웃 인플레이터
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialoglayout, null)

        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()

        // 다이얼로그 메시지 텍스트뷰 설정
        val numberTextView = dialogView.findViewById<TextView>(R.id.dialogNumber)
        numberTextView.text = "문제 1."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "된장찌개 만드는 방법을 검색창에 입력하시오."
        messageTextView.textSize = 20f

        // 확인 버튼 설정
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기

            // ///////saveResult(true) // 문제 풀이 성공으로 표시
            // returnToHomeScreen() // 홈 화면으로 이동
        }

        alertDialog.show()

        // 다이얼로그가 나타나면 타이머 멈춤
        timer.cancel()
        isTimerRunning = false
    }

    private fun returnToHomeScreen() {
        val intent = Intent(this, ExamCallProblem2Activity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)

    }
}

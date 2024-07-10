package com.sungkyul.synergy.learning_space.accountedu

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityGoogleDefaultinfoBinding
import com.sungkyul.synergy.courses.accountedu.GoogleDefaultInfoCourse

class GoogleDefaultInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGoogleDefaultinfoBinding

    private var selectedGender: String = ""
    private var selectedMonth: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleDefaultinfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = GoogleDefaultInfoCourse(binding.eduScreen)

            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.

                // MainActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
            // 교육을 시작한다.
            binding.eduScreen.start(this)
        }

        // 뒤로 가기 키를 눌렀을 때의 이벤트를 처리한다.
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // MainActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })

        // "다음" 버튼 클릭 리스너 설정
        val defaultinfoNextButton = findViewById<Button>(R.id.defaultinfo_next_button)
        defaultinfoNextButton.setOnClickListener {
            val nextIntent = Intent(this, GoogleMailActivity::class.java)

            // 값을 전달한다.
            nextIntent.putExtras(intent)
            nextIntent.putExtra("year", binding.yearEdittext.text.toString())
            nextIntent.putExtra("month", binding.monthEdittext.text.toString())
            nextIntent.putExtra("day", binding.dayEdittext.text.toString())
            nextIntent.putExtra("gender", binding.genderEdittext.text.toString())

            // activity_google_mail 화면으로 이동
            startActivity(nextIntent)
        }

        // 다이얼로그를 표시하기 위한 에딧텍스트 가져오기
        val showMonthSelectionDialogEditText = findViewById<EditText>(R.id.month_edittext)
        val showGenderSelectionDialogEditText = findViewById<EditText>(R.id.gender_edittext)

        // 에딧텍스트 클릭 리스너 설정
        showMonthSelectionDialogEditText.setOnClickListener {
            // 월 선택 다이얼로그 표시
            showMonthSelectionDialog()
        }

        showGenderSelectionDialogEditText.setOnClickListener {
            // 성별 선택 다이얼로그 표시
            showGenderSelectionDialog()
        }

        // 성별 입력 칸에 포커스를 주지 않음
        showGenderSelectionDialogEditText.isFocusable = false
        showGenderSelectionDialogEditText.isClickable = true

        // yearEdittext의 텍스트 변경 감지
        binding.yearEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 필요시 구현
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("test", "onTextChanged")
                if (s.toString().isNotEmpty()) {
                    // 사용자가 텍스트를 입력한 경우
                    binding.eduScreen.onAction("year_input")
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // 필요시 구현
            }
        })

        // monthEdittext의 텍스트 변경 감지
        binding.monthEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 필요시 구현
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) {
                    // 사용자가 텍스트를 입력한 경우
                    binding.eduScreen.onAction("month_input")
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // 필요시 구현
            }
        })

        // dayEdittext의 텍스트 변경 감지
        binding.dayEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 필요시 구현
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("test", "onTextChanged")
                if (s.toString().isNotEmpty()) {
                    // 사용자가 텍스트를 입력한 경우
                    binding.eduScreen.onAction("day_input")
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // 필요시 구현
            }
        })

        // genderEdittext의 텍스트 변경 감지
        binding.genderEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 필요시 구현
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("test", "onTextChanged")
                if (s.toString().isNotEmpty()) {
                    // 사용자가 텍스트를 입력한 경우
                    binding.eduScreen.onAction("gender_input")
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // 필요시 구현
            }
        })

    }

    // 새로운 함수 - 월 선택 다이얼로그 표시
    private fun showMonthSelectionDialog() {
        val builder = AlertDialog.Builder(this)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.activity_google_month_selection_dialog, null)
        builder.setView(dialogView)

        // 다이얼로그 내의 라디오 그룹 가져오기
        val monthRadioGroup: RadioGroup = dialogView.findViewById(R.id.month_radio_group)

        // 1월부터 12월까지 반복하여 라디오 버튼 추가
        for (i in 1..12) {
            val radioButton = RadioButton(this)
            radioButton.text = "${i}월"
            radioButton.id = View.generateViewId()
            monthRadioGroup.addView(radioButton)
        }

        // 다이얼로그 생성 및 표시
        val alertDialog = builder.create()
        alertDialog.show()

        // 라디오 그룹 선택 리스너 설정
        monthRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            selectedMonth = radioButton.text.toString()
        }

        // 완료 버튼 클릭 리스너 설정
        val completeButton = dialogView.findViewById<Button>(R.id.m_di_complete_button)
        completeButton.setOnClickListener {

            // 선택된 월을 에딧 텍스트에 적용
            val monthEditText = findViewById<EditText>(R.id.month_edittext)
            monthEditText.setText(selectedMonth)
            // 키보드를 자동으로 내리기
            hideKeyboard(monthEditText)
            // 팝업 창 닫기
            alertDialog.dismiss()
        }

        // 다음 버튼 클릭 리스너 설정
        val nextButton = dialogView.findViewById<Button>(R.id.m_di_next_button)
        nextButton.setOnClickListener {
            // 일 에딧텍스트에 포커스 주기
            val dayEditText = findViewById<EditText>(R.id.day_edittext)
            dayEditText.requestFocus()
            // 키보드를 자동으로 내리기
            hideKeyboard(dayEditText)
            // 선택된 월을 에딧 텍스트에 적용
            val monthEditText = findViewById<EditText>(R.id.month_edittext)
            monthEditText.setText(selectedMonth)
            // 팝업 창 닫기
            alertDialog.dismiss()
        }
        // 이전 버튼 클릭 리스너 설정
        val previousButton = dialogView.findViewById<Button>(R.id.m_di_previous_button)
        previousButton.setOnClickListener {
            // 연 에딧텍스트에 포커스 주기
            val yearEditText = findViewById<EditText>(R.id.year_edittext)
            yearEditText.requestFocus()
            // 키보드를 자동으로 내리기
            hideKeyboard(yearEditText)
            // 선택된 월을 에딧 텍스트에 적용
            val monthEditText = findViewById<EditText>(R.id.month_edittext)
            monthEditText.setText(selectedMonth)
            // 팝업 창 닫기
            alertDialog.dismiss()
        }
    }

    private fun showGenderSelectionDialog() {
        val builder = AlertDialog.Builder(this)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.activity_google_gender_selection_dialog, null)
        builder.setView(dialogView)

        // 팝업창 배경 설정
        val background = ContextCompat.getDrawable(this, R.drawable.google_dialog_background)
        dialogView.background = background

        // 다이얼로그 생성
        val alertDialog = builder.create()
        alertDialog.show()

        // 라디오 그룹 가져오기
        val genderRadioGroup = dialogView.findViewById<RadioGroup>(R.id.gender_radio_group)

        // 라디오 그룹 선택 리스너 설정
        genderRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            selectedGender = radioButton.text.toString()
        }

        // 완료 버튼 클릭 리스너 설정
        val completeButton = dialogView.findViewById<Button>(R.id.g_di_complete_button)
        completeButton.setOnClickListener {
            // 선택된 성별을 에딧 텍스트에 적용
            val genderEditText = findViewById<EditText>(R.id.gender_edittext)
            genderEditText.setText(selectedGender)
            // 키보드를 자동으로 내리기
            hideKeyboard(genderEditText)
            // 팝업 창 닫기
            alertDialog.dismiss()
        }
        // 다음 버튼 클릭 리스너 설정
        val gnextButton = dialogView.findViewById<Button>(R.id.g_di_next_button)
        gnextButton.setOnClickListener {
            // 선택된 성별을 에딧 텍스트에 적용
            val genderEditText = findViewById<EditText>(R.id.gender_edittext)
            genderEditText.setText(selectedGender)
            // 키보드를 자동으로 내리기
            hideKeyboard(genderEditText)
            // 팝업 창 닫기
            alertDialog.dismiss()
        }

        // 이전 버튼 클릭 리스너 설정
        val previousButton = dialogView.findViewById<Button>(R.id.g_di_previous_button)
        previousButton.setOnClickListener {
            // 일 에딧텍스트에 포커스 주기
            val dayEditText = findViewById<EditText>(R.id.day_edittext)
            dayEditText.requestFocus()
            // 키보드를 자동으로 내리기
            hideKeyboard(dayEditText)
            // 선택된 성별을 에딧 텍스트에 적용
            val genderEditText = findViewById<EditText>(R.id.gender_edittext)
            genderEditText.setText(selectedGender)
            // 팝업 창 닫기
            alertDialog.dismiss()
        }
    }

    // 키보드를 자동으로 열어주는 함수
    private fun hideKeyboard(editText: EditText) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }

}

package com.sungkyul.synergy.edu_space.accountedu

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sungkyul.synergy.R

class GoogleDefaultInfoActivity : AppCompatActivity() {

    private var selectedGender: String = ""
    private var selectedMonth: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_defaultinfo)

        // "다음" 버튼 클릭 리스너 설정
        val defaultinfoNextButton = findViewById<Button>(R.id.defaultinfo_next_button)
        defaultinfoNextButton.setOnClickListener {
            // activity_google_mail 화면으로 이동
            startActivity(Intent(this@GoogleDefaultInfoActivity, GoogleMailActivity::class.java))
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
    }

    // 새로운 함수 - 월 선택 다이얼로그 표시
    private fun showMonthSelectionDialog() {
        val builder = AlertDialog.Builder(this)
        val dialogView =
            LayoutInflater.from(this).inflate(R.layout.activity_google_month_selection_dialog, null)
        builder.setView(dialogView)

        // 다이얼로그 내의 라디오 그룹 가져오기
        val monthRadioGroup: RadioGroup = dialogView.findViewById(R.id.month_radio_group)

        // 1월부터 12월까지 반복하여 라디오 버튼 추가
        for (i in 1..12) {
            val radioButton = RadioButton(this)
            radioButton.text = "${i}월" // 문자열 템플릿 사용
            radioButton.id = View.generateViewId() // View 클래스의 메소드를 사용하기 위해 import 추가해야 함
            monthRadioGroup.addView(radioButton) // 라디오 그룹에 라디오 버튼 추가
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
            // 키보드를 자동으로 올리기
            showKeyboard(monthEditText)
            // 팝업 창 닫기
            alertDialog.dismiss()
        }

        // 다음 버튼 클릭 리스너 설정
        val nextButton = dialogView.findViewById<Button>(R.id.m_di_next_button)
        nextButton.setOnClickListener {
            // 일 에딧텍스트에 포커스 주기
            val dayEditText = findViewById<EditText>(R.id.day_edittext)
            dayEditText.requestFocus()
            // 키보드를 자동으로 올리기
            showKeyboard(dayEditText)
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
            // 키보드를 자동으로 올리기
            showKeyboard(yearEditText)
            // 선택된 월을 에딧 텍스트에 적용
            val monthEditText = findViewById<EditText>(R.id.month_edittext)
            monthEditText.setText(selectedMonth)
            // 팝업 창 닫기
            alertDialog.dismiss()
        }
    }

    // 새로운 함수 - 성별 선택 다이얼로그 표시
    private fun showGenderSelectionDialog() {
        val builder = AlertDialog.Builder(this)
        val dialogView =
            LayoutInflater.from(this).inflate(R.layout.activity_google_gender_selection_dialog, null)
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
            // 키보드를 자동으로 올리기
            showKeyboard(genderEditText)
            // 팝업 창 닫기
            alertDialog.dismiss()
        }

        // 이전 버튼 클릭 리스너 설정
        val previousButton = dialogView.findViewById<Button>(R.id.g_di_previous_button)
        previousButton.setOnClickListener {
            // 일 에딧텍스트에 포커스 주기
            val dayEditText = findViewById<EditText>(R.id.day_edittext)
            dayEditText.requestFocus()
            // 키보드를 자동으로 올리기
            showKeyboard(dayEditText)
            // 선택된 성별을 에딧 텍스트에 적용
            val genderEditText = findViewById<EditText>(R.id.gender_edittext)
            genderEditText.setText(selectedGender)
            // 팝업 창 닫기
            alertDialog.dismiss()
        }
    }

    // 키보드를 자동으로 열어주는 함수
    private fun showKeyboard(editText: EditText) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }
}

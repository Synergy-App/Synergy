package com.sungkyul.synergy.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class TextUtil {
    companion object {
        // editText의 맨 끝에 str을 추가하는 함수
        fun extendText(editText: EditText, str: String) {
            editText.setText(editText.text.toString() + str)
        }

        // editText의 맨 끝에서 한 글자를 삭제하는 함수
        fun popText(editText: EditText) {
            val text = editText.text.toString()
            if(text.isNotEmpty()) {
                editText.setText(text.substring(0, text.length - 1))
            }
        }

        // editText를 비우는 함수
        fun clearText(editText: EditText) {
            editText.setText("")
        }

        // 키보드를 숨기는 함수
        fun hideKeyboard(activity: Activity, view: View) {
            val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}

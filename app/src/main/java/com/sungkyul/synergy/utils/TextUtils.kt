package com.sungkyul.synergy.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class TextUtils {
    companion object {
        val searchDict = listOf(
            "가방", "가습기", "강릉", "강릉날씨", "감자탕", "강아지",
            "갸우뚱",
            "거제 날씨", "거란", "건조기", "거미", "건강보험",
            "경주날씨", "경복궁", "경주", "경매",
            "광주날씨",
            "공모전",
            "교차로", "교통상황", "교육부", "교통사고",
            "구글",
            "기상청", "기상청 동네예보", "길찾기",
            "계산기",
            "날씨", "나무위키",
            "냠냠", "냥아지",
            "너 t야?", "너구리", "넘버링",
            "년살", "년도", "녘", "년 한자",
            "네이버", "네이버지도", "네이버웹툰",
            "노래방", "노트북",
            "누룽지", "눈", "눈썰매장", "눈썹문신",
            "뉴스", "뉴욕",
            "달력", "다음", "달러",
            "대구날씨"
        )

        // 관련된 키워드를 모두 검색한다.
        fun searchAll(s: String): List<String> {
            return searchDict.filter {
                it.contains(s)
            }
        }

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

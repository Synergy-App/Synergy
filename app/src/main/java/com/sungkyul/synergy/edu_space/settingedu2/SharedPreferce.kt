package com.sungkyul.synergy.edu_space.settingedu2

import android.content.Context
import android.preference.PreferenceManager

// DefaultPreferenceManager.kt
class DefaultPreferenceManager(private val context: Context) {
    companion object {
        private const val WORD_TEXT_SIZE = "text_size"
    }
    private val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
    private val editor = sharedPreference.edit()

    fun setTextSize(size: Int) {
        // 기본값이 움직이지 않도록 수정
        val adjustedSize = if (size in 0..7) size else 3
        editor.putInt(WORD_TEXT_SIZE, adjustedSize).apply()
    }

    fun getTextSize() = sharedPreference.getInt(WORD_TEXT_SIZE, 3) // 기본값은 3
}